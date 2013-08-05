package com.sbar.volumewaker;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

public class BootService extends Service {
    private final String LOGTAG = "volumewaker BootService";

    @Override
    public IBinder onBind(final Intent intent) {
        Log.i(LOGTAG, LOGTAG+" onBind");
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        //TODO do something useful
        Log.i(LOGTAG, LOGTAG+" onStartCommand");
        return Service.START_NOT_STICKY;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.i(LOGTAG, LOGTAG+" created");
    }

    @Override
    public void onStart(final Intent intent, final int startId) {
        super.onStart(intent, startId);
        Log.i(LOGTAG, LOGTAG+" started");
    }
}
