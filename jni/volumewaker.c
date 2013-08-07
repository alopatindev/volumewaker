#include <stdio.h>
#include <stdlib.h>
#include <signal.h>
#include <unistd.h>

#define BUF_MAX 256
#define VOLUME_UP 24
#define VOLUME_DOWN 25
#define POWER 25
#define MIN_INTERVAL_SECS 2

FILE* f;
time_t last_time;
time_t last_time_unlocking;

void quit_handler(int signum)
{
    (void) pclose(f);
}

void parse_line(char* buffer, size_t len)
{
    // for locking logic
    static int volume_up_pressed = 0;
    static int volume_down_pressed = 0;

    time_t t = time(NULL);
    if (t - last_time < MIN_INTERVAL_SECS)
    {
        return;
    }

    char* p = strstr(buffer, "interceptKeyTq");
    if (p != NULL)
    {
        char sio[6];
        char pr[6];
        char dumb[9];
        int keycode;
        sscanf(p, "interceptKeyTq keycode=%d screenIsOn=%s keyguardActive=%s policyFlags = %s down =%s", &keycode, &sio, dumb, dumb, &pr);
        int screenIsOn = strncmp(sio, "true", 6) == 0;
        int pressed = strncmp(pr, "true", 6) == 0;
        //printf("keycode is %d, screenIsOn is %d\n", keycode, screenIsOn);

        if (screenIsOn)
        {
            if (keycode == VOLUME_UP)
                volume_up_pressed = 1;
            else if (keycode == VOLUME_DOWN)
                volume_down_pressed = 1;
            last_time_unlocking = t;
        }

        int volume_pressed = keycode == VOLUME_UP || keycode == VOLUME_DOWN;
        if (volume_pressed)
        {
            if ( screenIsOn && !pressed && \
                 (volume_up_pressed || volume_down_pressed) && \
                !(volume_up_pressed && volume_down_pressed) && \
                last_time_unlocking - t < 1 )
            {
                volume_up_pressed = 0;
                volume_down_pressed = 0;
            }
            if ( (!screenIsOn && pressed) || \
                 (screenIsOn && !pressed && \
                  volume_up_pressed && volume_down_pressed) )
            {
                system("input keyevent KEYCODE_POWER");
                last_time = t;
                volume_up_pressed = 0;
                volume_down_pressed = 0;
            }
        }
    }
}

int child_process()
{
    system("logcat -c");
    f = popen("logcat *:d", "r");
    if (f == NULL)
    {
        printf("error: cannot run logcat\n");
        return 1;
    }

    signal(SIGINT, quit_handler);

    last_time = time(NULL);

    char buf[BUF_MAX];

    while (fgets(buf, BUF_MAX, f) != NULL)
    {
        parse_line(buf, BUF_MAX);
    }
    
    quit_handler(0);
    return 0;
}

int main(int argc, const char *argv[])
{
    pid_t pid = fork();
    if (pid >= 0)
    {
        printf("forking ok\n");
        if (pid == 0)
        {
            printf("running child process\n");
            return child_process();
        }
    } else {
        printf("forking failed\n");
    }
    return 1;
}
