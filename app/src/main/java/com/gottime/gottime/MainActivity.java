package com.gottime.gottime;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        System.out.println("App oncreate called.");
        setContentView(R.layout.activity_main);
        Button button = (Button)findViewById(R.id.new_task_button);
        button.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                System.out.println("Clicked! " + v);
                Intent nextIntent = new Intent(MainActivity.this, AddTask.class);
                startActivityForResult(nextIntent, 1);
            }
        });

        TaskStorage ts = new TaskStorage();
        List<Task> loadedTasks = ts.loadTasks(getApplicationContext());
        if (loadedTasks == null) {
            Log.w("GotTime", "Warning: Tasks could not be loaded.  Is this the first run?");
            userTasks = loadedTasks;
        }

        // persistence test
        /*List<Task> sometasks = new LinkedList<Task>();
        sometasks.add(new Task("Test", 10, 0));
        sometasks.add(new Task("Do laundry", 1, 20));
        try {
            ts.storeTask(getApplicationContext(), sometasks);
        } catch (IOException ioe) {
            Log.e("GotTime", ioe.toString());
        }*/
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK && requestCode == 1) {
            if (data.hasExtra("hours")) {
                Log.i("GotTime", "h" + data.getIntExtra("hours", -1) + "m" + data.getIntExtra("minutes", -1) + "+d:" + data.getStringExtra("task_desc"));
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
