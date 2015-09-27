package com.gottime.gottime;

import java.util.List;
/**
 * Created by Anmol on 26/09/15.
 */
public class FindTask {

    public static Task findTask(List<Task> taskList, int minutes) {
        if (taskList == null) {
            return null;
        }
        Task temp = null;
        int mostTime = 0;
        for (Task t: taskList) {
            if (t.getMinutes() > mostTime && t.getMinutes() <= minutes) {
                temp = t;
                mostTime = t.getMinutes();
            }
        }
        return temp;
    }
}
