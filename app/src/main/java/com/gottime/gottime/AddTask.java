package com.gottime.gottime;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.TimePicker;

public class AddTask extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);
        View v = getWindow().getDecorView().getRootView();

        final EditText taskDesc = (EditText) v.findViewById(R.id.task_desc);
        final NumberPicker hours = (NumberPicker) v.findViewById(R.id.npHours);
        final NumberPicker minutes = (NumberPicker) v.findViewById(R.id.npMinutes);

        makePicker(hours, 5);
        makePicker(minutes, 60);

        Button button = (Button)findViewById(R.id.save_task);
        button.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                Intent data = new Intent();
                data.putExtra("hours", hours.getValue());
                data.putExtra("minutes", minutes.getValue());
                data.putExtra("task_desc", taskDesc.getText().toString());
                setResult(RESULT_OK, data);
                finish();
            }
        });
    }

    void makePicker(NumberPicker np, int max) {
        String[] values = new String[max];
        for(int i=0; i < values.length; i++) {
            values[i] = Integer.toString(i);
        }

        System.out.println("Number picker: " + np);
        np.setMaxValue(values.length - 1);
        np.setMinValue(0);
        np.setDisplayedValues(values);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_add_task, menu);
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
