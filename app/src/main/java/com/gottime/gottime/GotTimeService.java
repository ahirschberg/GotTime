package com.gottime.gottime;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.support.v7.app.NotificationCompat;
import android.util.Log;

public class GotTimeService extends Service {
    public GotTimeService() {
        super();

    }

    @Override
    public void onCreate() {
        Log.i("StartupTest", "Service instantiated!");
        Log.i("StartupTest", "Notification sent?");
        sendNotification();
    }

    protected void sendNotification() {
        NotificationCompat.Builder mBuilder =
                new NotificationCompat.Builder(this);
        mBuilder
                .setSmallIcon(R.drawable.ic_face_white_24dp)
                .setContentTitle("Got Time?")
                .setContentText("This notification from a service!");

        Intent resultIntent = new Intent(this, MainActivity.class);
        PendingIntent resultPendingIntent;
        Log.i("GotTime", String.format("%s %d %s %d", this.toString(), 0, resultIntent.toString(), PendingIntent.FLAG_UPDATE_CURRENT));
        resultPendingIntent = PendingIntent.getActivity(
                this,
                0,
                resultIntent,
                PendingIntent.FLAG_UPDATE_CURRENT
        );
        mBuilder.setContentIntent(resultPendingIntent);

        NotificationManager mNotifyMgr =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        mNotifyMgr.notify(001, mBuilder.build());
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
