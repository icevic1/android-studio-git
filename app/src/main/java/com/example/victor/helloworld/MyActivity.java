package com.example.victor.helloworld;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.util.Log;
import android.content.Intent;
import android.widget.ImageButton;
//import android.widget.Toast;

//import java.util.Timer;
//import java.util.TimerTask;


public class MyActivity extends Activity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);

        final ImageButton buttonStopwatch = (ImageButton) findViewById(R.id.buttonStopwatch);
        buttonStopwatch.setOnClickListener(this);

        final ImageButton buttonClockTime = (ImageButton) findViewById(R.id.buttonDateTime);
        buttonClockTime.setOnClickListener(this);

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

        switch (item.getItemId()) {
            case R.id.action_settings:
//                Utils.killCurrentProcess();
//                return true;
                break;
            case R.id.action_exit:
//                Utils.showAlert("mesage", this.getBaseContext());
                this.onBackPressed();
                break;
            case R.id.action_about:
                Intent intent = new Intent(MyActivity.this, AboutActivity.class);
                startActivity(intent);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        //final TextView defaultScreenText = this.screenTxt;

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
            case R.id.buttonStopwatch :
                Intent intent = new Intent(MyActivity.this, StopwatchActivity.class);
                startActivity(intent);
                Log.d("MyDebug", "Button StopwatchActivity pressed !");
                break;
            case R.id.buttonDateTime :
//                Intent intent = new Intent(MyActivity.this, DatetimeActivity.class);
                startActivity(new Intent(MyActivity.this, DatetimeActivity.class));
                Log.d("MyDebug", "Button DatetimeActivity pressed !");
                break;
        }

        Log.d("MyDebug", "Button " + view.getId() + " pressed !");
    }

}
