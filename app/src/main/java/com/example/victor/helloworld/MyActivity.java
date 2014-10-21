package com.example.victor.helloworld;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.util.Log;
//import android.widget.Toast;
import android.widget.ToggleButton;

//import java.util.Timer;
//import java.util.TimerTask;


public class MyActivity extends Activity implements View.OnClickListener{

    private static String defaultScreenText;
    private static TextView screenTxt;

    private static TextView textViewTimer;

    private Handler customHandler = new Handler();

    private long startTime = 0L;
    long timeInMilliseconds = 0L;
    long timeSwapBuff = 0L;
    long updatedTime = 0L;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);

        //Store current view text
        screenTxt = (TextView) findViewById(R.id.main_screen_text);
        MyActivity.setDefaultScreenText(screenTxt.getText().toString());

       textViewTimer = (TextView) findViewById(R.id.textViewTimerId);

        final Button buttonResetText = (Button)findViewById(R.id.buttonResetText);
        buttonResetText.setOnClickListener(this);

        final Button buttonShowTime = (Button)findViewById(R.id.buttonShowTime);
        buttonShowTime.setOnClickListener(this);

        //final Button buttonTimerStart = (Button)findViewById(R.id.buttonStart);
       // buttonTimerStart.setOnClickListener(this);

//        final Button buttonTimerStop = (Button)findViewById(R.id.buttonStop);
//        buttonTimerStop.setOnClickListener(this);

       /* buttonTimerStart.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {
                startTime = SystemClock.uptimeMillis();
                customHandler.postDelayed(updateTimerThread, 0);

            }
        });*/

        /*buttonTimerStop.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {

                timeSwapBuff += timeInMilliseconds;
                customHandler.removeCallbacks(updateTimerThread);

            }
        });*/


        Log.d("myDebug", "finish constructor");
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

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
//            Utils.killCurrentProcess();
            screenTxt.setText("Pressed settings menu point");
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

        screenTxt.setText("Try to close...");

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

    @Override
    public void onClick (View view){
        switch (view.getId()) {
            case R.id.buttonResetText :
                MyActivity.screenTxt.setText(MyActivity.getDefaultScreenText());
                break;
            case R.id.buttonShowTime :
                MyActivity.screenTxt.setText(Utils.getFormattedDate(true));
                break;
            /*case R.id.buttonStart :
                startTime = SystemClock.uptimeMillis();
                customHandler.postDelayed(updateTimerThread, 0);
                break;
            case R.id.buttonStop :
                timeSwapBuff += timeInMilliseconds;
                customHandler.removeCallbacks(updateTimerThread);
                break;*/
        }

        Log.d("MyDebug", "Button " + view.getId() + " pressed !");
    }


    private Runnable updateTimerThread = new Runnable() {

        public void run() {

            timeInMilliseconds = SystemClock.uptimeMillis() - startTime;

            updatedTime = timeSwapBuff + timeInMilliseconds;

            int secs = (int) (updatedTime / 1000);
            int mins = secs / 60;
            secs = secs % 60;
            int milliseconds = (int) (updatedTime % 1000);
            MyActivity.textViewTimer.setText(""
                    + String.format("%02d", mins) + ":"
                    + String.format("%02d", secs) + ":"
                    + String.format("%03d", milliseconds));
            customHandler.postDelayed(this, 0);
        }

    };

    public void onToggleButtonTimerStartPauseClick(View button) {

        if (((ToggleButton) button).isChecked()) {
            //start timer
            startTime = SystemClock.uptimeMillis();
            customHandler.postDelayed(updateTimerThread, 0);
        } else {
            //stop timer
            timeSwapBuff += timeInMilliseconds;
            customHandler.removeCallbacks(updateTimerThread);
        }

        /*Toast.makeText(
                getApplicationContext(),
                Boolean.toString(((ToggleButton) button).isChecked()),
                Toast.LENGTH_SHORT).show();*/
    }

    public void onButtonTimerResetClick(View button) {
        startTime = 0L;
        timeInMilliseconds = 0L;
        timeSwapBuff = 0L;
        updatedTime = 0L;

        //stop timer
        timeSwapBuff += timeInMilliseconds;
        customHandler.removeCallbacks(updateTimerThread);



        // reset timer text with 00:00:00 - gotten from strings file
        MyActivity.textViewTimer.setText(R.string.timerVal);

        // reset toggled button to default
        ToggleButton buttonTimerStart = (ToggleButton)findViewById(R.id.buttonStart);
        buttonTimerStart.setChecked(false);
    }

}
