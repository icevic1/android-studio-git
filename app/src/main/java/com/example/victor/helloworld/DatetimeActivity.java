package com.example.victor.helloworld;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
//import android.widget.Toast;

//import java.util.Timer;
//import java.util.TimerTask;


public class DatetimeActivity extends Activity implements View.OnClickListener {

    private static TextView screenTxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.datetime_layout);

        //Store current view text
        screenTxt = (TextView) findViewById(R.id.clock_screen_text);
        screenTxt.setText(Utils.getFormattedDate(true));

        final Button buttonShowTime = (Button)findViewById(R.id.buttonShowTime);
        buttonShowTime.setOnClickListener(this);

        Log.d("myDebug", "finish constructor");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.my, menu);
        return true;
    }

    @Override
    public void onClick (View view){
        switch (view.getId()) {
            case R.id.buttonShowTime :
                DatetimeActivity.screenTxt.setText(Utils.getFormattedDate(true));
                Log.d("MyDebug", "Button show time pressed !");
                break;
        }

        Log.d("MyDebug", "Button " + view.getId() + " pressed !");
    }

}
