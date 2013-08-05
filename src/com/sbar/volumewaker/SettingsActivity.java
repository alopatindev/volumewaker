package com.sbar.volumewaker;

import android.app.Activity;
import android.app.ActivityManager;
import android.os.Bundle;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.sbar.volumewaker.BootService;

public class SettingsActivity extends Activity {
    private final String LOGTAG = "volumewaker SettingsActivity";

    private boolean isServiceRunning() {
        ActivityManager manager = (ActivityManager) getSystemService(
            Context.ACTIVITY_SERVICE
        );
        for (ActivityManager.RunningServiceInfo service :
             manager.getRunningServices(Integer.MAX_VALUE)) {
            if (BootService.class.getName().equals(
                    service.service.getClassName())) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void onCreate(Bundle s) {
        super.onCreate(s);
        setContentView(R.layout.main);

        if (!isServiceRunning())
        {
            Intent serviceIntent = new Intent(this, BootService.class);
            startService(serviceIntent);
        }
        Log.i(LOGTAG, LOGTAG+" onCreate");
    }
}
