package com.gottime.gottime;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.content.Intent;
import java.util.ArrayList;

public class FindTaskActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_task);
        Intent intent = getIntent();
        ArrayList<Task> userTasks= intent.getParcelableArrayListExtra("taskList");
        int minutesAvailable = intent.getIntExtra("minutes", 1);
        Task todoTask = new Task();
        todoTask = FindTask.findTask(userTasks, minutesAvailable);
        if (todoTask != null) {
            TextView textView2 = (TextView) findViewById(R.id.textView2);
            textView2.setText(todoTask.getTaskName());
            //TextView textView4 = (TextView) findViewById(R.id.textView4);
            //textView4.setText(todoTask.toTime());
            //onClick()
            //userTasks.remove(todoTask);
        } else {
            TextView textView2 = (TextView) findViewById(R.id.textView2);
            textView2.setText("No Task Found!");
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_find_task, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
