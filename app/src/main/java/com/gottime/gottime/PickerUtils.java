package com.gottime.gottime;

import android.util.Log;
import android.widget.NumberPicker;

/**
 * Created by Alex on 9/27/2015.
 */
public class PickerUtils {
    private int[] pickerValues;

    public PickerUtils () {
        pickerValues = new int[13];
    }
    private void setupValue(String[] values, int index, int minutes) {
        setupValue(values, index, minutes, (minutes + " minutes"));
    }
    private void setupValue(String[] values, int index, int minutes, String dispString) {
        Log.i("PickerUtils", String.format("i: %d, str: %s, val: %d", index, dispString, minutes));
        pickerValues[index] = minutes;
        values[index] = dispString;

    }
    public void makePicker(NumberPicker np) {
        String[] values = new String[13];
        for (int i = 0; i <= 3; i++) {
            setupValue(values, i, (i+1) * 5);
        }
        setupValue(values, 4, 30);
        setupValue(values, 5, 45);

        for(int i = 6; i < values.length; i++) {
            int totalMinutes = 60 + (i - 6) * 30;
            int displayHours = totalMinutes / 60;
            int displayMinutes = totalMinutes % 60;

            String displayString = displayHours + " hour" + (displayHours == 1 ? "" : "s");

            if (displayMinutes != 0) {
                displayString += (" " + displayMinutes + " min" + (displayMinutes == 1 ? "" : "s"));
            }
            setupValue(values, i, totalMinutes, displayString);
        }
        np.setMaxValue(values.length - 1);
        np.setMinValue(0);
        np.setDisplayedValues(values);
        np.setWrapSelectorWheel(false);
    }
    public int getMinutesFromIndex(int index) {
        return pickerValues[index];
    }

}
