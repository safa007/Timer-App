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

    Button startBtn, pauseBtn, lapBtn; //Button objects
    TextView timerText; // The value on the
    Handler customHandler = new Handler();
    LinearLayout container;
    boolean isPaused = true; //Used to handle runtime issues when timer is paused
    //Used to prevent errors caused from user pressing start multiple times
    boolean isStarted = false;


    //Values needed to update time correctly
    long startTime = 0L, timeInMilliseconds = 0L, timeSwapBuff = 0L, updateTime = 0L;

    //Thread used to update the textview timer while timer is updating vars
    Runnable updateTimerThread = new Runnable() {
        @Override
        public void run() {
            timeInMilliseconds = SystemClock.uptimeMillis()-startTime;
            updateTime = timeSwapBuff + timeInMilliseconds;
            int secs = (int) (updateTime/1000);
            int mins = secs/60;
            secs%=60;
            int milliseconds = (int) (updateTime%1000);
            timerText.setText("" + mins + ":" + String.format("%02d", secs) + ":" +
                                                String.format("%03d", milliseconds));
            customHandler.postDelayed(this, 0);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.d("MyActivity", "Starting application");

        //Reference the buttons and text on the design layout
        startBtn = (Button) findViewById(R.id.startBtn);
        pauseBtn = (Button) findViewById(R.id.pauseBtn);
        lapBtn = (Button) findViewById(R.id.lapBtn);
        timerText = (TextView) findViewById(R.id.timerText);
        container = (LinearLayout)findViewById(R.id.container);


        Log.d("run", "inside of the onCreate method");

        startBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Log.d("run", "Clicked the start button");

                //If we haven't already clicked start
                if (!isStarted) {
                    isPaused = false; //Set to false because we are starting timer again
                    startTime = SystemClock.uptimeMillis();
                    customHandler.postDelayed(updateTimerThread, 0);
                }
                isStarted = true; //To prevent user from pressing start again
            }
        });

        startBtn.setOnLongClickListener(new View.OnLongClickListener(){
            @Override
            public boolean onLongClick(View view){
                Log.d("MyActivity", "Clicked long on start button");

                //Don't allow the user to reset timer if timer is running
                if(!isStarted) {
                    //Reset all variables
                    startTime = 0L;
                    timeInMilliseconds = 0L;
                    timeSwapBuff = 0L;
                    updateTime = 0L;

                    //Update the textview
                    timerText.setText("" + 0 + ":" + String.format("%02d", 0) + ":" +
                            String.format("%03d", 0));

                    return true;
                }
                return false;
            }
        });

        //On pause, update the temporary buffer and stop the thread
        pauseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("MyActivity", "Clicked the pause button");

                //Only update the time if we are not already paused
                if(!isPaused) {
                    timeSwapBuff += timeInMilliseconds;
                    customHandler.removeCallbacks(updateTimerThread);
                }
                //Set paused to true
                isPaused = true;
                //Since we paused, we haven't started yet
                isStarted = false;
            }
        });

        //On lap button press
        lapBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("MyActivity", "Clicked on the lap button");

                //Create an inflater to add a new row to the layout
                LayoutInflater inflater = (LayoutInflater) getBaseContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                View addView = inflater.inflate(R.layout.row, null);
                //New textview for the newly added row
                TextView txtValue = (TextView) addView.findViewById(R.id.txtContent);
                txtValue.setText(timerText.getText());
                container.addView(addView);

            }
        });

    }
}