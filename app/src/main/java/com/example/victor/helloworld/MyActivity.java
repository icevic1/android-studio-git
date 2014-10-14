package com.example.victor.helloworld;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.util.Log;

import org.w3c.dom.Text;
//import android.widget.Toast;


public class MyActivity extends Activity {

    private static Context context;
    private static String defaultScreenText;
    private static TextView screenTxt;
//    public TextView screenTxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);
//        MyActivity.context = getApplicationContext();

        this.screenTxt = (TextView) findViewById(R.id.main_screen_text);
        MyActivity.setDefaultScreenText(this.screenTxt.getText().toString());
        Log.d("myDebug", "my debug message 1");
        Log.d("myDebug", MyActivity.getDefaultScreenText());
    }

    private static void setDefaultScreenText(String screenText) {
        MyActivity.defaultScreenText = screenText;
    }

    private static String getDefaultScreenText() {
        return MyActivity.defaultScreenText;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.my, menu);
        return true;
    }
   /* public static void setDefaultScreenText(TextView screenTxt) {
        MyActivity.defaultScreenText = screenTxt;
    }*/



    public static Context getAppContext() {
        return MyActivity.context;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
//            Utils.killCurrentProcess();
            return true;
        } else if (id == R.id.action_exit) {
            //Utils.showAlert("mesage", this.getBaseContext());
            this.onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        //final TextView defaultScreenText = this.screenTxt;

        this.screenTxt.setText("Try to close...");

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(false);
        builder.setMessage("Do you want to Exit?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //if user pressed "yes", then he is allowed to exit from application
                Log.d("myDebug", "Yes");
                finish();
//                System.exit(0);
            }
        });
        builder.setNegativeButton("No",new DialogInterface.OnClickListener() {
//            public TextView screenTxt;

            @Override
            public void onClick(DialogInterface dialog, int which) {
                //if user select "No", just cancel this dialog and continue with app
                MyActivity.screenTxt.setText(MyActivity.getDefaultScreenText());
                Log.d("myDebug", "No");
                dialog.cancel();
            }
        });
        AlertDialog alert=builder.create();
        alert.show();
    }
}
