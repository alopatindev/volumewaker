package com.sbar.volumewaker;

import android.app.Activity;
import android.os.Bundle;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.sbar.volumewaker.BootService;

public class SettingsActivity extends Activity {
    private final String LOGTAG = "volumewaker SettingsActivity";
    @Override
    public void onCreate(Bundle s) {
        super.onCreate(s);
        setContentView(R.layout.main);

        Intent mServiceIntent = new Intent(this, BootService.class);
        startService(mServiceIntent);
        Log.i(LOGTAG, LOGTAG+" onCreate");
    }
}
