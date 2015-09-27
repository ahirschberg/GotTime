package com.gottime.gottime;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

public class GotTimeService extends Service {
    public GotTimeService() {
        Log.i("StartupTest", "Service instantiated!");
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
