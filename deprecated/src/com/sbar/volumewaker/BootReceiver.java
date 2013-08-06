package com.sbar.volumewaker;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.sbar.volumewaker.BootService;

public class BootReceiver extends BroadcastReceiver {
    private final String LOGTAG = "volumewaker BootReceiver";

    @Override
    public void onReceive(final Context context, final Intent bootintent) {
        Log.i(LOGTAG, "onReceive");
        /*Intent mServiceIntent = new Intent();
        mServiceIntent.setAction("com.sbar.volumewaker.BootService");*/
        Intent mServiceIntent = new Intent(context, BootService.class);
        context.startService(mServiceIntent);
    }
}
