package com.example.victor.helloworld;

import android.app.AlertDialog;
import android.content.Context;

/**
 * Created by victor on 11.10.2014.
 */
public class Utils {

    public static void showAlert(String message, Context context){
//        AlertDialog.Builder builder = new AlertDialog.Builder(this.getContext());
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Here is a message message from my activity")
                .setMessage(message)
                .setNeutralButton("OK", null);
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    public static void killCurrentProcess() {
        int pid = android.os.Process.myPid();
        android.os.Process.killProcess(pid);
        System.exit(0);
    }
}
