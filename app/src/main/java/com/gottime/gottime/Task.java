package com.gottime.gottime;

import java.util.Random;

public class Task implements java.io.Serializable {
    private String taskName;
    private int hours;
    private int minutes;

    public Task(String name, int h, int m) {
        taskName = name;
        hours = h;
        minutes = m;
    }

    public int getHours() {
        return this.hours;
    }

    public int getMinutes() {
        return minutes;
    }

    public String toString() {
        return String.format("%s %d hours %d minutes", taskName, hours, minutes);
    }
}
