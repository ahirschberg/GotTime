package com.gottime.gottime;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.NumberPicker;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        System.out.println("test.");
        setContentView(R.layout.activity_main);
        Button button = (Button)findViewById(R.id.new_task_button);
        button.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                System.out.println("Clicked! " + v);
                Intent nextIntent = new Intent(MainActivity.this, AddTask.class);
                startActivity(nextIntent);
            }
        });
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
