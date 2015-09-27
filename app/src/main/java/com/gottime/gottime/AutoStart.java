package com.gottime.gottime;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

/**
 * Created by kkolu_000 on 9/26/2015.
 */
public class AutoStart extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent)
    {
        if (Intent.ACTION_BOOT_COMPLETED.equals(intent.getAction())) {
            Log.i("StartupTest", "AutoStart BOOT_COMPLETED");
            Intent startServiceIntent = new Intent(context, GotTimeService.class);
            context.startService(startServiceIntent);
        }
    }
}
