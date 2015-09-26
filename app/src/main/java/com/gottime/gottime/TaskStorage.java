package com.gottime.gottime;

import android.content.Context;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.List;

/**
 * Created by Alex on 9/26/2015.
 */
public class TaskStorage {
    public void storeTask(Context ctx, List<Task> tasks) throws IOException {
        FileOutputStream fos = ctx.openFileOutput("test", Context.MODE_PRIVATE);
        ObjectOutputStream os = new ObjectOutputStream(fos);
        os.writeObject(tasks);
        os.close();
        fos.close();
    }

    public List<Task> loadTasks(Context ctx) {
        try {
            FileInputStream fis = ctx.openFileInput("test");
            ObjectInputStream is = new ObjectInputStream(fis);
            List<Task> tasks = (List<Task>) is.readObject();
            is.close();
            fis.close();

            return tasks;
        }
        catch(IOException ex){
            System.out.println("Cannot perform read output. " + ex);
        }
        catch(ClassNotFoundException ex2){
            System.out.println("Cannot find class. " + ex2);
        }

        return null; // fix to throw errors
    }
}
