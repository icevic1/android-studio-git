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



public class MyActivity extends Activity implements View.OnClickListener{

    private static String defaultScreenText;
    private static TextView screenTxt;
//    private static Time currentTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);

        //Initialize curent time
//        this.currentTime = new Time(Time.getCurrentTimezone());
//        this.currentTime.setToNow();

        //Store current view text
        this.screenTxt = (TextView) findViewById(R.id.main_screen_text);
        MyActivity.setDefaultScreenText(this.screenTxt.getText().toString());

        final Button buttonResetText = (Button)findViewById(R.id.buttonResetText);
        buttonResetText.setOnClickListener(this);

        final Button buttonShowTime = (Button)findViewById(R.id.buttonShowTime);
        buttonShowTime.setOnClickListener(this);

//        buttonResetText.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//            }
//        });
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

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
//            Utils.killCurrentProcess();
            this.screenTxt.setText("Pressed settings menu point");
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

    @Override
    public void onClick (View view){
        switch (view.getId()) {
            case R.id.buttonResetText :
                MyActivity.screenTxt.setText(MyActivity.getDefaultScreenText());
                break;
            case R.id.buttonShowTime :
//                MyActivity.screenTxt.setText(MyActivity.currentTime.format("%d.%m.%Y %H:%I:%S"));
                MyActivity.screenTxt.setText(Utils.getFormattedDate(true));
                break;
        }

        Log.d("MyDebug", "Button " + view.getId() + " pressed !");
    }


}
