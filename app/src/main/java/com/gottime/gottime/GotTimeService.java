package com.gottime.gottime;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.support.v7.app.NotificationCompat;
import android.util.Log;
import android.widget.Toast;

public class GotTimeService extends Service {

    private Handler mHandler;
    private int timesCalled = 0;

    @Override
    public void onCreate() {
        Log.i("GotTimeService", "Service instantiated!");
        //Toast.makeText(this, "My Service Started", Toast.LENGTH_LONG).show();

        mHandler = new Handler();
        startRepeatingTask();
    }

    Runnable mStatusChecker = new Runnable() {
        @Override
        public void run() {
            if (timesCalled++ > 0) {
                sendNotification();
            }
            mHandler.postDelayed(mStatusChecker, 3 * 60 * 1000);
        }
    };

    void startRepeatingTask() {
        mStatusChecker.run();
    }

    void stopRepeatingTask() {
        mHandler.removeCallbacks(mStatusChecker);
    }

    protected void sendNotification() {
        NotificationCompat.Builder mBuilder =
                new NotificationCompat.Builder(this);
        mBuilder
                .setSmallIcon(R.drawable.ic_face_white_24dp)
                .setContentTitle("Got Time?")
                .setContentText("Tap here and do something cool!" );

        Intent resultIntent = new Intent(this, MainActivity.class);
        PendingIntent resultPendingIntent;
        //Log.i("GotTimeService", String.format("%s %d %s %d", this.toString(), 0, resultIntent.toString(), PendingIntent.FLAG_UPDATE_CURRENT));
        resultPendingIntent = PendingIntent.getActivity(
                this,
                0,
                resultIntent,
                PendingIntent.FLAG_UPDATE_CURRENT
        );
        mBuilder.setContentIntent(resultPendingIntent);

        NotificationManager mNotifyMgr =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        mNotifyMgr.notify(2, mBuilder.build());
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    public void onDestroy() {
        Toast.makeText(this, "My Service Stopped", Toast.LENGTH_LONG).show();
        stopRepeatingTask();
        Log.d("GotTimeService", "onDestroy");
    }
}
