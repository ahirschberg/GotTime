package com.gottime.gottime;

import java.util.List;
/**
 * Created by Anmol on 26/09/15.
 */
public class FindTask {

    public static Task findTask(List<Task> taskList, int hours, int minutes) {
        int timeAvailable = hours * 60 + minutes;
        int totalTime;
        Task temp = null;
        int mostTime = 0;
        for (Task t: taskList) {
            totalTime = t.getHours() * 60 + t.getMinutes();
            if (totalTime > mostTime && totalTime <= timeAvailable) {
                temp = t;
                mostTime = totalTime;
            }
        }
        return temp;
    }
}
