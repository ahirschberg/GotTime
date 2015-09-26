package com.gottime.gottime;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
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

    private Button submitButton;
    private EditText taskDesc;
    private NumberPicker npTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);
        View v = getWindow().getDecorView().getRootView();

        taskDesc = (EditText) v.findViewById(R.id.task_desc);
        npTime = (NumberPicker) v.findViewById(R.id.user_time);

        makePicker(npTime, 48);

        submitButton = (Button)findViewById(R.id.save_task);
        submitButton.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                Intent data = new Intent();
                data.putExtra("minutes", npTime.getValue() * 5);
                data.putExtra("task_desc", taskDesc.getText().toString());
                setResult(RESULT_OK, data);
                finish();
            }
        });
        NumberPicker.OnValueChangeListener npChangeListener =
                new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                updateButtonStatus();
            }
        };
        npTime.setOnValueChangedListener(npChangeListener);
        taskDesc.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            public void afterTextChanged(Editable s) {
                Log.i("GotTime", "afterTextChanged called");
                updateButtonStatus();
            }
        });
    }

    void updateButtonStatus() {
        Log.i("GotTime", "TaskDesc toS: '" + taskDesc.getText().toString().trim() + "'");
        boolean buttonEnabled = taskDesc.getText().toString().trim().length() != 0;
        submitButton.setEnabled(buttonEnabled);
    }

    void makePicker(NumberPicker np, int max) {
        String[] values = new String[max];
        for(int i = 0; i < values.length; i++) {
            int minute = i * 5;
            String disp = String.format("%d:%02d", minute / 60, minute % 60);
            values[i] = disp;
        }

        System.out.println("Number picker: " + np);
        np.setMaxValue((values.length - 1));
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
