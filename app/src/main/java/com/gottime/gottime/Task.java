package com.gottime.gottime;

import android.os.Parcel;
import android.os.Parcelable;

public class Task implements Parcelable, java.io.Serializable {
    private String taskName;
    private int minutes;

    public Task() {
    }

    public Task(String name, int minutes) {
        this.taskName = name;
        this.minutes = minutes;
    }

    public String getTaskName() {
        return this.taskName;
    }

    public int getMinutes() {
        return this.minutes;
    }

    public String toTime() {
        if (minutes < 60) {
            return String.format("%d minutes", minutes);
        } else if (minutes < 120) {
            return String.format("1 hour %d minutes", minutes % 60);
        } else {
            return String.format("%d hours %d minutes", minutes / 60, minutes % 60);
        }
    }

    public String toString() {
        return String.format("%s %d hours %d minutes", taskName, minutes / 60, minutes % 60);
    }

    public void writeToParcel(Parcel out, int flags) {
        out.writeString(taskName);
        out.writeInt(minutes);
    }

    private Task(Parcel in) {
        taskName = in.readString();
        minutes = in.readInt();
    }

    public static final Parcelable.Creator<Task> CREATOR =
            new Parcelable.Creator<Task>() {
                public Task createFromParcel(Parcel in) {
                    return new Task(in);
                }

                public Task[] newArray(int size) {
                    return new Task[size];
                }
            };

    public int describeContents() {
        return 0;
    }
}
