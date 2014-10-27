package com.example.victor.helloworld;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.util.Log;
import android.content.Intent;
import android.widget.ImageButton;
//import android.widget.Toast;

//import java.util.Timer;
//import java.util.TimerTask;


public class MyActivity extends Activity implements View.OnClickListener{

    private static String defaultScreenText;
    private static TextView screenTxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);

        //Store current view text
        screenTxt = (TextView) findViewById(R.id.main_screen_text);
        MyActivity.setDefaultScreenText(screenTxt.getText().toString());

        final Button buttonResetText = (Button)findViewById(R.id.buttonResetText);
        buttonResetText.setOnClickListener(this);

        final Button buttonShowTime = (Button)findViewById(R.id.buttonShowTime);
        buttonShowTime.setOnClickListener(this);

//        final Button buttonStopwatch = (Button)findViewById(R.id.buttonStopwatch);
//        buttonStopwatch.setOnClickListener(this);

        final ImageButton buttonStopwatch = (ImageButton) findViewById(R.id.buttonStopwatch);
        buttonStopwatch.setOnClickListener(this);

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

        switch (item.getItemId()) {
            case R.id.action_settings:
//                Utils.killCurrentProcess();
                screenTxt.setText("Pressed settings menu point");
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
                Log.d("MyDebug", "Button show time pressed !");
                break;
            case R.id.buttonStopwatch :
                Intent intent = new Intent(MyActivity.this, StopwatchActivity.class);
                startActivity(intent);
                Log.d("MyDebug", "Button suka pressed !");
                break;
            /*case R.id.buttonStop :
                timeSwapBuff += timeInMilliseconds;
                customHandler.removeCallbacks(updateTimerThread);
                break;*/
        }

        Log.d("MyDebug", "Button " + view.getId() + " pressed !");
    }

}
