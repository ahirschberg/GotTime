package com.gottime.gottime;

import android.app.AlarmManager;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
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
import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ArrayList<Task> userTasks;
    TaskStorage taskStorage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        System.out.println("App oncreate called.");
        setContentView(R.layout.activity_main);

        final NumberPicker desiredTime = (NumberPicker) findViewById(R.id.desiredTime);
        final PickerUtils pu = new PickerUtils();
        pu.makePicker(desiredTime);

        Button getInputs = (Button) findViewById(R.id.new_task_button);
        getInputs.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                Intent nextIntent = new Intent(MainActivity.this, AddTask.class);
                startActivityForResult(nextIntent, 1);
            }
        });

        Button showResults = (Button) findViewById(R.id.show_task_button);
        showResults.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, FindTaskActivity.class);
                NumberPicker np = (NumberPicker) findViewById(R.id.desiredTime);
                intent.putParcelableArrayListExtra("taskList", userTasks);
                int totalMinutes = pu.getMinutesFromIndex(np.getValue());
                intent.putExtra("minutes", totalMinutes);
                startActivity(intent);
            }
        });

        taskStorage = new TaskStorage();
        userTasks = taskStorage.loadTasks(getApplicationContext());
        if (userTasks == null) {
            Log.w("GotTime", "Warning: Tasks could not be loaded.  Is this the first run?");
            userTasks = new ArrayList<>();

        }

        // print all tasks to console
        Log.i("GotTime", "------All Tasks------");
        for (Task t : userTasks) {
            Log.i("GotTime", t.toString());
        }

        startService(new Intent(this, GotTimeService.class));
    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK && requestCode == 1) {
            Task t = new Task(
                    data.getStringExtra("task_desc"),
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
}
