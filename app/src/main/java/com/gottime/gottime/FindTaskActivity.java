package com.gottime.gottime;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.content.Intent;
import java.util.ArrayList;

public class FindTaskActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_task);
        Intent intent = getIntent();
        Task todoTask = MainActivity.foundTaskForDisplay;
        if (todoTask != null) {
            TextView tvTaskName = (TextView) findViewById(R.id.task_name);
            tvTaskName.setText(todoTask.getTaskName());
            TextView textView4 = (TextView) findViewById(R.id.textView4);
            textView4.setText(todoTask.toTime());
        } else {
            Log.e("GotTime", "No task found");
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
