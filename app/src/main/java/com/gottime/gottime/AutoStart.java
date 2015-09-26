package com.gottime.gottime;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * Created by kkolu_000 on 9/26/2015.
 */
public class AutoStart extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent)
    {
        Intent startServiceIntent = new Intent(context, GotTime.class);
        context.startService(startServiceIntent);
    }
}
