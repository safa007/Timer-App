package com.example.raffos.timer;

/**
 * Created by raffos on 7/26/2017.
 */
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.ToggleButton;

public class Tab1Timer extends Fragment {

    //Various Buttons for start, reset, and pause
    Button startBtn1, resetBtn1, startBtn2, resetBtn2, startBtn3, resetBtn3,
            startBtn4, resetBtn4; //Button objects
    ToggleButton pauseBtn1, pauseBtn2, pauseBtn3, pauseBtn4;

    //Text values for the timer
    TextView timerText1, timerText2, timerText3, timerText4;

    //Handlers for runnable objects for each timer
    Handler customHandler1 = new Handler();
    Handler customHandler2 = new Handler();
    Handler customHandler3 = new Handler();
    Handler customHandler4 = new Handler();

    //Values needed to update time correctly
    long startTime1 = 0L, timeInMilliseconds1 = 0L, timeSwapBuff1 = 0L, updateTime1 = 0L,
            startTime2 = 0L, timeInMilliseconds2 = 0L, timeSwapBuff2 = 0L, updateTime2 = 0L,
            startTime3 = 0L, timeInMilliseconds3 = 0L, timeSwapBuff3 = 0L, updateTime3 = 0L,
            startTime4 = 0L, timeInMilliseconds4 = 0L, timeSwapBuff4 = 0L, updateTime4 = 0L;

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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.tab1timer, container, false);

        Log.d("MyActivity", "Starting application");

        //Reference the buttons and text on the design layout
        startBtn1 = (Button) rootView.findViewById(R.id.startBtn1);
        pauseBtn1 = (ToggleButton) rootView.findViewById(R.id.pauseBtn1);
        resetBtn1 = (Button) rootView.findViewById(R.id.resetBtn1);
        resetBtn1.setEnabled(false);
        pauseBtn1.setEnabled(false);
        timerText1 = (TextView) rootView.findViewById(R.id.timerText1);


        Log.d("run", "inside of the onCreate method");

        startBtn1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Log.d("run", "Clicked the start button");

                //Start the timer
                startTime1 = SystemClock.uptimeMillis();
                customHandler1.postDelayed(updateTimerThread1, 0);

                startBtn1.setEnabled(false);
                pauseBtn1.setEnabled(true);
                resetBtn1.setEnabled(false);
            }
        });

        //On pause, update the temporary buffer and stop the thread
        pauseBtn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("MyActivity", "Clicked the pause button");

                //Resume timer
                if(!pauseBtn1.isChecked()) {
                    startTime1 = SystemClock.uptimeMillis();
                    customHandler1.postDelayed(updateTimerThread1, 0);
                    resetBtn1.setEnabled(false);
                }
                //Pause timer
                else {
                    timeSwapBuff1 += timeInMilliseconds1;
                    customHandler1.removeCallbacks(updateTimerThread1);
                    resetBtn1.setEnabled(true);
                }
            }
        });

        //On lap button press
        resetBtn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Log.d("MyActivity", "Clicked on reset button");

                startBtn1.setEnabled(true); //Allow the start button to be pressed
                //Reset all variables
                startTime1 = 0L;
                timeInMilliseconds1 = 0L;
                timeSwapBuff1 = 0L;
                updateTime1 = 0L;

                //Update the textview
                timerText1.setText("" + 0 + ":" + String.format("%02d", 0) + ":" +
                        String.format("%03d", 0));
                pauseBtn1.setEnabled(false);
                pauseBtn1.setChecked(false);
                resetBtn1.setEnabled(false);
            }
        });

        //Reference the buttons and text on the design layout
        startBtn2 = (Button) rootView.findViewById(R.id.startBtn2);
        pauseBtn2 = (ToggleButton) rootView.findViewById(R.id.pauseBtn2);
        resetBtn2 = (Button) rootView.findViewById(R.id.resetBtn2);
        resetBtn2.setEnabled(false);
        pauseBtn2.setEnabled(false);
        timerText2 = (TextView) rootView.findViewById(R.id.timerText2);


        Log.d("run", "inside of the onCreate method");

        startBtn2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Log.d("run", "Clicked the start button");

                //Start the timer
                startTime2 = SystemClock.uptimeMillis();
                customHandler2.postDelayed(updateTimerThread2, 0);

                startBtn2.setEnabled(false);
                pauseBtn2.setEnabled(true);
                resetBtn2.setEnabled(false);
            }
        });

        //On pause, update the temporary buffer and stop the thread
        pauseBtn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("MyActivity", "Clicked the pause button");

                //Resume timer
                if(!pauseBtn2.isChecked()) {
                    startTime2 = SystemClock.uptimeMillis();
                    customHandler2.postDelayed(updateTimerThread2, 0);
                    resetBtn2.setEnabled(false);
                }
                //Pause timer
                else {
                    timeSwapBuff2 += timeInMilliseconds2;
                    customHandler2.removeCallbacks(updateTimerThread2);
                    resetBtn2.setEnabled(true);
                }

            }
        });

        //On lap button press
        resetBtn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("MyActivity", "Clicked on reset button");

                startBtn2.setEnabled(true); //Allow the start button to be pressed
                //Reset all variables
                startTime2 = 0L;
                timeInMilliseconds2 = 0L;
                timeSwapBuff2 = 0L;
                updateTime2 = 0L;

                //Update the textview
                timerText2.setText("" + 0 + ":" + String.format("%02d", 0) + ":" +
                        String.format("%03d", 0));

                pauseBtn2.setEnabled(false);
                pauseBtn2.setChecked(false);
                resetBtn2.setEnabled(false);
            }
        });

        //Reference the buttons and text on the design layout
        startBtn3 = (Button) rootView.findViewById(R.id.startBtn3);
        pauseBtn3 = (ToggleButton) rootView.findViewById(R.id.pauseBtn3);
        resetBtn3 = (Button) rootView.findViewById(R.id.resetBtn3);
        resetBtn3.setEnabled(false);
        pauseBtn3.setEnabled(false);
        timerText3 = (TextView) rootView.findViewById(R.id.timerText3);

        startBtn3.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Log.d("run", "Clicked the start button");

                //Start the timer
                startTime3 = SystemClock.uptimeMillis();
                customHandler3.postDelayed(updateTimerThread3, 0);

                startBtn3.setEnabled(false);
                pauseBtn3.setEnabled(true);
                resetBtn3.setEnabled(false);
            }
        });

        //On pause, update the temporary buffer and stop the thread
        pauseBtn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("MyActivity", "Clicked the pause button");

                //Resume timer
                if(!pauseBtn3.isChecked()) {
                    startTime3 = SystemClock.uptimeMillis();
                    customHandler3.postDelayed(updateTimerThread3, 0);
                    resetBtn3.setEnabled(false);
                }
                //Pause timer
                else {
                    timeSwapBuff3 += timeInMilliseconds3;
                    customHandler3.removeCallbacks(updateTimerThread3);
                    resetBtn3.setEnabled(true);
                }

            }
        });

        //On lap button press
        resetBtn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("MyActivity", "Clicked on reset button");

                startBtn3.setEnabled(true); //Allow the start button to be pressed
                //Reset all variables
                startTime3 = 0L;
                timeInMilliseconds3 = 0L;
                timeSwapBuff3 = 0L;
                updateTime3 = 0L;

                //Update the textview
                timerText3.setText("" + 0 + ":" + String.format("%02d", 0) + ":" +
                        String.format("%03d", 0));

                pauseBtn3.setEnabled(false);
                pauseBtn3.setChecked(false);
                resetBtn3.setEnabled(false);
            }
        });


        //Reference the buttons and text on the design layout
        startBtn4 = (Button) rootView.findViewById(R.id.startBtn4);
        pauseBtn4 = (ToggleButton) rootView.findViewById(R.id.pauseBtn4);
        resetBtn4 = (Button) rootView.findViewById(R.id.resetBtn4);
        resetBtn4.setEnabled(false);
        pauseBtn4.setEnabled(false);
        timerText4 = (TextView) rootView.findViewById(R.id.timerText4);

        startBtn4.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Log.d("run", "Clicked the start button");

                startTime4 = SystemClock.uptimeMillis();
                customHandler4.postDelayed(updateTimerThread4, 0);

                startBtn4.setEnabled(false);
                pauseBtn4.setEnabled(true);
                resetBtn4.setEnabled(false);
            }
        });

        //On pause, update the temporary buffer and stop the thread
        pauseBtn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("MyActivity", "Clicked the pause button");

                //Resume timer
                if(!pauseBtn4.isChecked()) {
                    startTime4 = SystemClock.uptimeMillis();
                    customHandler4.postDelayed(updateTimerThread4, 0);
                    resetBtn4.setEnabled(false);
                }
                //Pause timer
                else {
                    timeSwapBuff4 += timeInMilliseconds4;
                    customHandler4.removeCallbacks(updateTimerThread4);
                    resetBtn4.setEnabled(true);
                }
            }
        });

        //On lap button press
        resetBtn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("MyActivity", "Clicked on reset button");

                startBtn4.setEnabled(true); //Allow the start button to be pressed
                //Reset all variables
                startTime4 = 0L;
                timeInMilliseconds4 = 0L;
                timeSwapBuff4 = 0L;
                updateTime4 = 0L;

                //Update the textview
                timerText4.setText("" + 0 + ":" + String.format("%02d", 0) + ":" +
                        String.format("%03d", 0));

                pauseBtn4.setEnabled(false);
                pauseBtn4.setChecked(false);
                resetBtn4.setEnabled(false);

            }
        });


        return rootView;
    }


}
