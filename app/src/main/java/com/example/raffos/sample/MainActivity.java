package com.example.raffos.sample;

import android.content.Context;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.os.Handler;

import org.w3c.dom.Text;

import java.util.Timer;
import java.util.TimerTask;

import static android.R.attr.tag;

public class MainActivity extends AppCompatActivity {

    Button startBtn1, pauseBtn1, lapBtn1; //Button objects
    TextView timerText1; // The value on the
    Handler customHandler1 = new Handler();
    LinearLayout container1;
    boolean isPaused1 = true; //Used to handle runtime issues when timer is paused
    //Used to prevent errors caused from user pressing start multiple times
    boolean isStarted1 = false;
    //Values needed to update time correctly
    long startTime1 = 0L, timeInMilliseconds1 = 0L, timeSwapBuff1 = 0L, updateTime1 = 0L;

    Button startBtn2, pauseBtn2, lapBtn2; //Button objects
    TextView timerText2; // The value on the
    Handler customHandler2 = new Handler();
    LinearLayout container2;
    boolean isPaused2 = true; //Used to handle runtime issues when timer is paused
    //Used to prevent errors caused from user pressing start multiple times
    boolean isStarted2 = false;
    //Values needed to update time correctly
    long startTime2 = 0L, timeInMilliseconds2 = 0L, timeSwapBuff2 = 0L, updateTime2 = 0L;

    Button startBtn3, pauseBtn3, lapBtn3; //Button objects
    TextView timerText3; // The value on the
    Handler customHandler3 = new Handler();
    LinearLayout container3;
    boolean isPaused3 = true; //Used to handle runtime issues when timer is paused
    //Used to prevent errors caused from user pressing start multiple times
    boolean isStarted3 = false;
    //Values needed to update time correctly
    long startTime3 = 0L, timeInMilliseconds3 = 0L, timeSwapBuff3 = 0L, updateTime3 = 0L;

    Button startBtn4, pauseBtn4, lapBtn4; //Button objects
    TextView timerText4; // The value on the
    Handler customHandler4 = new Handler();
    LinearLayout container4;
    boolean isPaused4 = true; //Used to handle runtime issues when timer is paused
    //Used to prevent errors caused from user pressing start multiple times
    boolean isStarted4 = false;
    //Values needed to update time correctly
    long startTime4 = 0L, timeInMilliseconds4 = 0L, timeSwapBuff4 = 0L, updateTime4 = 0L;

    //Thread used to update the textview timer while timer is updating vars
    Runnable updateTimerThread1 = new Runnable() {
        @Override
        public void run() {
            timeInMilliseconds1 = SystemClock.uptimeMillis()-startTime1;
            updateTime1 = timeSwapBuff1 + timeInMilliseconds1;
            int secs = (int) (updateTime1/1000);
            int mins = secs/60;
            secs%=60;
            int milliseconds = (int) (updateTime1%1000);
            timerText1.setText("" + mins + ":" + String.format("%02d", secs) + ":" +
                                                String.format("%03d", milliseconds));
            customHandler1.postDelayed(this, 0);
        }
    };

    //Thread used to update the textview timer while timer is updating vars
    Runnable updateTimerThread2 = new Runnable() {
        @Override
        public void run() {
            timeInMilliseconds2 = SystemClock.uptimeMillis()-startTime2;
            updateTime2 = timeSwapBuff2 + timeInMilliseconds2;
            int secs = (int) (updateTime2/1000);
            int mins = secs/60;
            secs%=60;
            int milliseconds = (int) (updateTime2%1000);
            timerText2.setText("" + mins + ":" + String.format("%02d", secs) + ":" +
                    String.format("%03d", milliseconds));
            customHandler2.postDelayed(this, 0);
        }
    };

    //Thread used to update the textview timer while timer is updating vars
    Runnable updateTimerThread3 = new Runnable() {
        @Override
        public void run() {
            timeInMilliseconds3 = SystemClock.uptimeMillis()-startTime3;
            updateTime3 = timeSwapBuff3 + timeInMilliseconds3;
            int secs = (int) (updateTime3/1000);
            int mins = secs/60;
            secs%=60;
            int milliseconds = (int) (updateTime3%1000);
            timerText3.setText("" + mins + ":" + String.format("%02d", secs) + ":" +
                    String.format("%03d", milliseconds));
            customHandler3.postDelayed(this, 0);
        }
    };

    //Thread used to update the textview timer while timer is updating vars
    Runnable updateTimerThread4 = new Runnable() {
        @Override
        public void run() {
            timeInMilliseconds4 = SystemClock.uptimeMillis()-startTime4;
            updateTime4 = timeSwapBuff4 + timeInMilliseconds4;
            int secs = (int) (updateTime4/1000);
            int mins = secs/60;
            secs%=60;
            int milliseconds = (int) (updateTime4%1000);
            timerText4.setText("" + mins + ":" + String.format("%02d", secs) + ":" +
                    String.format("%03d", milliseconds));
            customHandler4.postDelayed(this, 0);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.d("MyActivity", "Starting application");

        //Reference the buttons and text on the design layout
        startBtn1 = (Button) findViewById(R.id.startBtn1);
        pauseBtn1 = (Button) findViewById(R.id.pauseBtn1);
        lapBtn1 = (Button) findViewById(R.id.lapBtn1);
        timerText1 = (TextView) findViewById(R.id.timerText1);
        container1 = (LinearLayout)findViewById(R.id.container);


        Log.d("run", "inside of the onCreate method");

        startBtn1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Log.d("run", "Clicked the start button");

                //If we haven't already clicked start
                if (!isStarted1) {
                    isPaused1 = false; //Set to false because we are starting timer again
                    startTime1 = SystemClock.uptimeMillis();
                    customHandler1.postDelayed(updateTimerThread1, 0);
                }
                isStarted1 = true; //To prevent user from pressing start again
            }
        });

        startBtn1.setOnLongClickListener(new View.OnLongClickListener(){
            @Override
            public boolean onLongClick(View view){
                Log.d("MyActivity", "Clicked long on start button");

                //Don't allow the user to reset timer if timer is running
                if(!isStarted1) {
                    //Reset all variables
                    startTime1 = 0L;
                    timeInMilliseconds1 = 0L;
                    timeSwapBuff1 = 0L;
                    updateTime1 = 0L;

                    //Update the textview
                    timerText1.setText("" + 0 + ":" + String.format("%02d", 0) + ":" +
                            String.format("%03d", 0));

                    return true;
                }
                return false;
            }
        });

        //On pause, update the temporary buffer and stop the thread
        pauseBtn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("MyActivity", "Clicked the pause button");

                //Only update the time if we are not already paused
                if(!isPaused1) {
                    timeSwapBuff1 += timeInMilliseconds1;
                    customHandler1.removeCallbacks(updateTimerThread1);
                }
                //Set paused to true
                isPaused1 = true;
                //Since we paused, we haven't started yet
                isStarted1 = false;
            }
        });

        //On lap button press
        lapBtn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("MyActivity", "Clicked on the lap button");

                //Create an inflater to add a new row to the layout
                LayoutInflater inflater = (LayoutInflater) getBaseContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                View addView = inflater.inflate(R.layout.row, null);
                //New textview for the newly added row
                TextView txtValue = (TextView) addView.findViewById(R.id.txtContent);
                txtValue.setText(timerText1.getText());
                container1.addView(addView);

            }
        });

        //Reference the buttons and text on the design layout
        startBtn2 = (Button) findViewById(R.id.startBtn2);
        pauseBtn2 = (Button) findViewById(R.id.pauseBtn2);
        lapBtn2 = (Button) findViewById(R.id.lapBtn2);
        timerText2 = (TextView) findViewById(R.id.timerText2);
        container2 = (LinearLayout)findViewById(R.id.container);


        Log.d("run", "inside of the onCreate method");

        startBtn2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Log.d("run", "Clicked the start button");

                //If we haven't already clicked start
                if (!isStarted2) {
                    isPaused2 = false; //Set to false because we are starting timer again
                    startTime2 = SystemClock.uptimeMillis();
                    customHandler2.postDelayed(updateTimerThread2, 0);
                }
                isStarted2 = true; //To prevent user from pressing start again
            }
        });

        startBtn2.setOnLongClickListener(new View.OnLongClickListener(){
            @Override
            public boolean onLongClick(View view){
                Log.d("MyActivity", "Clicked long on start button");

                //Don't allow the user to reset timer if timer is running
                if(!isStarted2) {
                    //Reset all variables
                    startTime2 = 0L;
                    timeInMilliseconds2 = 0L;
                    timeSwapBuff2 = 0L;
                    updateTime2 = 0L;

                    //Update the textview
                    timerText2.setText("" + 0 + ":" + String.format("%02d", 0) + ":" +
                            String.format("%03d", 0));

                    return true;
                }
                return false;
            }
        });

        //On pause, update the temporary buffer and stop the thread
        pauseBtn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("MyActivity", "Clicked the pause button");

                //Only update the time if we are not already paused
                if(!isPaused2) {
                    timeSwapBuff2 += timeInMilliseconds2;
                    customHandler2.removeCallbacks(updateTimerThread2);
                }
                //Set paused to true
                isPaused2 = true;
                //Since we paused, we haven't started yet
                isStarted2 = false;
            }
        });

        //On lap button press
        lapBtn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("MyActivity", "Clicked on the lap button");

                //Create an inflater to add a new row to the layout
                LayoutInflater inflater = (LayoutInflater) getBaseContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                View addView = inflater.inflate(R.layout.row, null);
                //New textview for the newly added row
                TextView txtValue = (TextView) addView.findViewById(R.id.txtContent);
                txtValue.setText(timerText2.getText());
                container2.addView(addView);

            }
        });

        //Reference the buttons and text on the design layout
        startBtn3 = (Button) findViewById(R.id.startBtn3);
        pauseBtn3 = (Button) findViewById(R.id.pauseBtn3);
        lapBtn3 = (Button) findViewById(R.id.lapBtn3);
        timerText3 = (TextView) findViewById(R.id.timerText3);
        container3 = (LinearLayout)findViewById(R.id.container);

        startBtn3.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Log.d("run", "Clicked the start button");

                //If we haven't already clicked start
                if (!isStarted3) {
                    isPaused3 = false; //Set to false because we are starting timer again
                    startTime3 = SystemClock.uptimeMillis();
                    customHandler3.postDelayed(updateTimerThread3, 0);
                }
                isStarted3 = true; //To prevent user from pressing start again
            }
        });

        startBtn3.setOnLongClickListener(new View.OnLongClickListener(){
            @Override
            public boolean onLongClick(View view){
                Log.d("MyActivity", "Clicked long on start button");

                //Don't allow the user to reset timer if timer is running
                if(!isStarted3) {
                    //Reset all variables
                    startTime3 = 0L;
                    timeInMilliseconds3 = 0L;
                    timeSwapBuff3 = 0L;
                    updateTime3 = 0L;

                    //Update the textview
                    timerText3.setText("" + 0 + ":" + String.format("%02d", 0) + ":" +
                            String.format("%03d", 0));

                    return true;
                }
                return false;
            }
        });

        //On pause, update the temporary buffer and stop the thread
        pauseBtn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("MyActivity", "Clicked the pause button");

                //Only update the time if we are not already paused
                if(!isPaused3) {
                    timeSwapBuff3 += timeInMilliseconds3;
                    customHandler3.removeCallbacks(updateTimerThread3);
                }
                //Set paused to true
                isPaused3 = true;
                //Since we paused, we haven't started yet
                isStarted3 = false;
            }
        });

        //On lap button press
        lapBtn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("MyActivity", "Clicked on the lap button");

                //Create an inflater to add a new row to the layout
                LayoutInflater inflater = (LayoutInflater) getBaseContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                View addView = inflater.inflate(R.layout.row, null);
                //New textview for the newly added row
                TextView txtValue = (TextView) addView.findViewById(R.id.txtContent);
                txtValue.setText(timerText3.getText());
                container3.addView(addView);

            }
        });


        //Reference the buttons and text on the design layout
        startBtn4 = (Button) findViewById(R.id.startBtn4);
        pauseBtn4 = (Button) findViewById(R.id.pauseBtn4);
        lapBtn4 = (Button) findViewById(R.id.lapBtn4);
        timerText4 = (TextView) findViewById(R.id.timerText4);
        container4 = (LinearLayout)findViewById(R.id.container);


        startBtn4.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Log.d("run", "Clicked the start button");

                //If we haven't already clicked start
                if (!isStarted4) {
                    isPaused4 = false; //Set to false because we are starting timer again
                    startTime4 = SystemClock.uptimeMillis();
                    customHandler4.postDelayed(updateTimerThread4, 0);
                }
                isStarted4 = true; //To prevent user from pressing start again
            }
        });

        startBtn4.setOnLongClickListener(new View.OnLongClickListener(){
            @Override
            public boolean onLongClick(View view){
                Log.d("MyActivity", "Clicked long on start button");

                //Don't allow the user to reset timer if timer is running
                if(!isStarted4) {
                    //Reset all variables
                    startTime4 = 0L;
                    timeInMilliseconds4 = 0L;
                    timeSwapBuff4 = 0L;
                    updateTime4 = 0L;

                    //Update the textview
                    timerText4.setText("" + 0 + ":" + String.format("%02d", 0) + ":" +
                            String.format("%03d", 0));

                    return true;
                }
                return false;
            }
        });

        //On pause, update the temporary buffer and stop the thread
        pauseBtn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("MyActivity", "Clicked the pause button");

                //Only update the time if we are not already paused
                if(!isPaused4) {
                    timeSwapBuff4 += timeInMilliseconds4;
                    customHandler4.removeCallbacks(updateTimerThread4);
                }
                //Set paused to true
                isPaused4 = true;
                //Since we paused, we haven't started yet
                isStarted4 = false;
            }
        });

        //On lap button press
        lapBtn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("MyActivity", "Clicked on the lap button");

                //Create an inflater to add a new row to the layout
                LayoutInflater inflater = (LayoutInflater) getBaseContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                View addView = inflater.inflate(R.layout.row, null);
                //New textview for the newly added row
                TextView txtValue = (TextView) addView.findViewById(R.id.txtContent);
                txtValue.setText(timerText4.getText());
                container4.addView(addView);

            }
        });

    }
}