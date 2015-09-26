package com.gottime.gottime;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.NotificationCompat;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.NumberPicker;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    List<Task> userTasks;
    TaskStorage taskStorage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        System.out.println("App oncreate called.");
        setContentView(R.layout.activity_main);

        final NumberPicker desiredTime = (NumberPicker) findViewById(R.id.desiredTime);
        String[] values = new String[61];
        for(int i=0; i < values.length; i++) {
            if (i < 12) {
                values[i] = Integer.toString(i * 5) + " minutes";
            } else if (i < 24) {
                values[i] = "1 hour " + Integer.toString((i * 5) % 60) + " minutes";
            } else {
                values[i] = Integer.toString((i / 12)) + " hours " + Integer.toString((i * 5) % 60) + " minutes";
            }
        }

        System.out.println("Number picker: " + desiredTime);
        desiredTime.setMaxValue(values.length - 1);
        desiredTime.setMinValue(0);
        desiredTime.setDisplayedValues(values);

        Button button = (Button)findViewById(R.id.new_task_button);
        button.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                System.out.println("Clicked! " + v);
                Intent nextIntent = new Intent(MainActivity.this, AddTask.class);
                startActivityForResult(nextIntent, 1);
            }
        });

        taskStorage = new TaskStorage();
        userTasks = taskStorage.loadTasks(getApplicationContext());
        if (userTasks == null) {
            Log.w("GotTime", "Warning: Tasks could not be loaded.  Is this the first run?");
            userTasks = new LinkedList<>();
        }

        // print all tasks to console
        Log.i("GotTime", "------All Tasks------");
        for (Task t : userTasks) {
            Log.i("GotTime", t.toString());
        }

        sendNotification();
    }

    protected void sendNotification() {
        NotificationCompat.Builder mBuilder =
            new NotificationCompat.Builder(this);
        mBuilder
            .setSmallIcon(R.drawable.ic_face_white_24dp)
            .setContentTitle("Got Time?")
            .setContentText("Tap to get something done!");

        Intent resultIntent = new Intent(this, MainActivity.class);
        PendingIntent resultPendingIntent =
                PendingIntent.getActivity(
                        this,
                        0,
                        resultIntent,
                        PendingIntent.FLAG_UPDATE_CURRENT
                );
        mBuilder.setContentIntent(resultPendingIntent);

        NotificationManager mNotifyMgr =
                (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        mNotifyMgr.notify(001, mBuilder.build());
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK && requestCode == 1) {
            Task t = new Task(
                    data.getStringExtra("task_desc"),
                    data.getIntExtra("hours", -1),
                    data.getIntExtra("minutes", -1));
            Log.i("GotTime", "New task: " + t.toString());
            userTasks.add(t);
            try {
                taskStorage.storeTask(getApplicationContext(), userTasks);
            } catch (IOException ioe) {
                Log.e("GotTime", ioe.toString());
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    public boolean onClickNewTaskButton(Object o) {
        System.out.println("Clicked! " + o);
        return true;
    }
}
