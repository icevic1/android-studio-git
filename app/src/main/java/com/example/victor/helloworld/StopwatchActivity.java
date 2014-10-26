package com.example.victor.helloworld;

import android.app.Activity;

import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.ToggleButton;

public class StopwatchActivity extends Activity {

    private static TextView textViewTimer;

    private Handler customHandler = new Handler();

    private long startTime = 0L;
    long timeInMilliseconds = 0L;
    long timeSwapBuff = 0L;
    long updatedTime = 0L;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.stopwatch_layout);

        //Store current view text
        textViewTimer = (TextView) findViewById(R.id.textViewTimerId);

        Log.d("myDebugStopwatch", "---- constructor---");
    }

    private Runnable updateTimerThread = new Runnable() {

        public void run() {

            timeInMilliseconds = SystemClock.uptimeMillis() - startTime;

            updatedTime = timeSwapBuff + timeInMilliseconds;

            int secs = (int) (updatedTime / 1000);
            int mins = secs / 60;
            secs = secs % 60;
            int milliseconds = (int) (updatedTime % 1000);
            StopwatchActivity.textViewTimer.setText(""
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
        StopwatchActivity.textViewTimer.setText(R.string.timerVal);

        // reset toggled button to default
        ToggleButton buttonTimerStart = (ToggleButton)findViewById(R.id.buttonStart);
        buttonTimerStart.setChecked(false);
    }

}
