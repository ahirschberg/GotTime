package com.gottime.gottime;

import java.util.Random;

public class Task implements java.io.Serializable {
    private String taskName;
    private int minutes;

    public Task(String name, int minutes) {
        this.taskName = name;
        this.minutes = minutes;
    }

    public int getMinutes() {
        return this.minutes;
    }

    public String toString() {
        return String.format("%s %d hours %d minutes", taskName, minutes / 60, minutes % 60);
    }
}
