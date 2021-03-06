package com.example.eddie.multi_timer;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.NumberPicker;
import android.widget.TextView;
import java.util.concurrent.TimeUnit;

@TargetApi(Build.VERSION_CODES.GINGERBREAD)
@SuppressLint("NewApi")

public class MainActivity extends AppCompatActivity {


    Button btnStart, btnStop, btnStart2, btnStop2;
    TextView textViewTime, textViewTime2;
    NumberPicker hours, mins, secs;
    SecondsTimer timer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        btnStart = (Button) findViewById(R.id.btnStart);
        btnStop = (Button) findViewById(R.id.btnStop);
        textViewTime = (TextView) findViewById(R.id.textViewTime);

        textViewTime.setText("00:00:00");

        hours = (NumberPicker) findViewById(R.id.hourscroll);
        mins = (NumberPicker) findViewById(R.id.minsscroll);
        secs = (NumberPicker) findViewById(R.id.secscroll);

        /*String[] dispHrs = {"00","01","02","03"};
        String[] dispMins = new String[60];
        String[] dispSecs = new String[60];
        for (int i = 0; i < 60; i++) {
            if (i < 10) {
                dispMins[i] = "0" + i;
                dispSecs[i] = "0" + i;
            }
            else {
                dispMins[i] = Integer.toString(i);
                dispSecs[i] = Integer.toString(i);
            }
        }

        hours.setDisplayedValues(dispHrs);
        mins.setDisplayedValues(dispMins);
        secs.setDisplayedValues(dispSecs); */

        hours.setMinValue(0);
        hours.setMaxValue(10);
        mins.setMinValue(0);
        mins.setMaxValue(59);
        secs.setMinValue(0);
        secs.setMaxValue(59);

        hours.setWrapSelectorWheel(true);
        mins.setWrapSelectorWheel(true);
        secs.setWrapSelectorWheel(true);

        btnStart.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                int timeInSecs = hours.getValue() * 3600 + mins.getValue() * 60 + secs.getValue();
                timer = new SecondsTimer(timeInSecs, 1, textViewTime);
                timer.start();
            }
        });
        btnStop.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                timer.cancel();
            }
        });



        /*final CounterClass timer = new CounterClass(180000, 1000);
        btnStart.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                timer.start();
            }
        });

        btnStop.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                timer.cancel();
            }
        }); */

        /*Evan's code starts here

        btnStart2 = (Button) findViewById(R.id.btnStart2);
        btnStop2 = (Button) findViewById(R.id.btnStop2);
        textViewTime2 = (TextView) findViewById(R.id.textViewTime2);

        textViewTime2.setText("00:03:00");

        final SecondsTimer timer2 = new SecondsTimer (180, 1, textViewTime2, btnStart2, btnStop2);

        //End myCode */

    }

    @TargetApi(Build.VERSION_CODES.GINGERBREAD)
    @SuppressLint("NewApi")
    public class CounterClass extends CountDownTimer {

        public CounterClass(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        @SuppressLint("NewApi")
        @TargetApi(Build.VERSION_CODES.GINGERBREAD)
        @Override
        public void onTick(long millisUntilFinished) {

            long millis = millisUntilFinished;
            String hms = String.format("%02d:%02d:%02d", TimeUnit.MILLISECONDS.toHours(millis),
                    TimeUnit.MILLISECONDS.toMinutes(millis) - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(millis)),
                    TimeUnit.MILLISECONDS.toSeconds(millis) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millis)));
            System.out.println(hms);
            textViewTime.setText(hms);
        }

        @Override
        public void onFinish(){
            textViewTime.setText("Completed.");
        }

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
