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

    public int getMinutes() {
        return this.minutes;
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
