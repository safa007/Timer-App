package com.example.raffos.timer;

import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ToggleButton;

/**
 * Created by raffos on 7/26/2017.
 */

public class Tab2Countdown extends Fragment {

    //To reference the buttons and text
    Button startBtn5, startBtn6, startBtn7, startBtn8, cancelBtn5,
            cancelBtn6, cancelBtn7, cancelBtn8;
    ToggleButton pauseBtn5, pauseBtn6, pauseBtn7, pauseBtn8;
    EditText enterNum5, enterNum6, enterNum7, enterNum8;

    //How much time there is to count down
    long timeInMillis5, timeInMillis6, timeInMillis7, timeInMillis8;
    //1 ms difference in time
    long countDownInterval5, countDownInterval6, countDownInterval7, countDownInterval8;

    //Used to prevent user from canceling when time is running and pressing pause twice
    boolean isPaused5, isPaused6, isPaused7, isPaused8 = false;
    boolean isCanceled5, isCanceled6, isCanceled7, isCanceled8 = false;
    long remainingTime5, remainingTime6, remainingTime7, remainingTime8 = 0;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.tab2countdown, container, false);

        startBtn5 = (Button) rootView.findViewById(R.id.startBtn5);
        pauseBtn5 = (ToggleButton) rootView.findViewById(R.id.pauseBtn5);
        cancelBtn5 = (Button) rootView.findViewById(R.id.cancelBtn5);
        enterNum5 = (EditText) rootView.findViewById(R.id.enterTimeText5);

        startBtn6 = (Button) rootView.findViewById(R.id.startBtn6);
        pauseBtn6 = (ToggleButton) rootView.findViewById(R.id.pauseBtn6);
        cancelBtn6 = (Button) rootView.findViewById(R.id.cancelBtn6);
        enterNum6 = (EditText) rootView.findViewById(R.id.enterTimeText6);

        startBtn7 = (Button) rootView.findViewById(R.id.startBtn7);
        pauseBtn7 = (ToggleButton) rootView.findViewById(R.id.pauseBtn7);
        cancelBtn7 = (Button) rootView.findViewById(R.id.cancelBtn7);
        enterNum7 = (EditText) rootView.findViewById(R.id.enterTimeText7);

        startBtn8 = (Button) rootView.findViewById(R.id.startBtn8);
        pauseBtn8 = (ToggleButton) rootView.findViewById(R.id.pauseBtn8);
        cancelBtn8 = (Button) rootView.findViewById(R.id.cancelBtn8);
        enterNum8 = (EditText) rootView.findViewById(R.id.enterTimeText8);

        //Don't let the user click the buttons if time is not running
        cancelBtn5.setEnabled(false);
        pauseBtn5.setEnabled(false);
        cancelBtn6.setEnabled(false);
        pauseBtn6.setEnabled(false);
        cancelBtn7.setEnabled(false);
        pauseBtn7.setEnabled(false);
        cancelBtn8.setEnabled(false);
        pauseBtn8.setEnabled(false);

        startBtn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //Try to parse the string to a number
                try {
                    timeInMillis5 = 1000*(Long.parseLong(enterNum5.getText().toString()));
                    countDownInterval5 = 1;
                }
                //Don't do anything if there is an error parsing the string
                catch (NumberFormatException e){
                    return;
                }

                //Disable start button when it has already been clicked
                startBtn5.setEnabled(false);
                //Make pause and cancel available to click
                pauseBtn5.setEnabled(true);
                cancelBtn5.setEnabled(true);

                isPaused5 = false;
                isCanceled5 = false;
                enterNum5.setEnabled(false); //Disable text editing after start button is clicked

                new CountDownTimer(timeInMillis5, countDownInterval5){

                    @Override
                    public void onTick(long timeInMillis) {

                        //If either is true, cancel the timer
                        if(isPaused5 || isCanceled5){
                            cancel();
                        }
                        else{
                            //Divide time into seconds, minutes, and milliseconds
                            int secs = (int) (timeInMillis/1000);
                            int mins = secs/60;
                            secs = secs % 60;
                            int milliseconds = (int) (timeInMillis%1000);

                            //Set the text of the timer accordinly
                            enterNum5.setText("" + mins + ":" + String.format("%02d", secs) + ":" +
                                    String.format("%03d", milliseconds));
                            enterNum5.setTextColor(Color.BLACK);
                            //Used for when we pause the timer
                            remainingTime5 = timeInMillis;
                        }
                    }

                    @Override
                    //Let the user know that time is up
                    public void onFinish() {
                        enterNum5.setText("Time is finished!");
                    }
                }.start();

            }
        });

        pauseBtn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(pauseBtn5.isChecked()) {
                    isPaused5 = true;
                    //Don't let the user edit the text
                    enterNum5.setEnabled(false);
                }
                else{
                    isPaused5 = false;
                    timeInMillis5 = remainingTime5; //How much time there is to count down
                    countDownInterval5 = 1; //1 ms difference in time

                    new CountDownTimer(timeInMillis5, countDownInterval5){

                        @Override
                        public void onTick(long timeInMillis) {

                            if(isPaused5 || isCanceled5){
                                cancel();
                            }
                            else{
                                //Divide time into seconds, minutes, and milliseconds
                                int secs = (int) (timeInMillis/1000);
                                int mins = secs/60;
                                secs = secs % 60;
                                int milliseconds = (int) (timeInMillis%1000);

                                //Set the text of the timer
                                enterNum5.setText("" + mins + ":" + String.format("%02d", secs) + ":" +
                                        String.format("%03d", milliseconds));
                                remainingTime5 = timeInMillis;
                            }
                        }

                        @Override
                        public void onFinish() {
                            enterNum5.setText("Time is finished!");
                        }
                    }.start();
                }
            }
        });

        cancelBtn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                isCanceled5 = true;
                enterNum5.setText(""); //Reset the text
                enterNum5.setEnabled(true); //Allow the text to be modified

                //Set the buttons accordingly
                startBtn5.setEnabled(true);
                pauseBtn5.setEnabled(false);
                pauseBtn5.setChecked(false);
                cancelBtn5.setEnabled(false);
            }
        });

        startBtn6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //Try to parse the string to a number
                try {
                    timeInMillis6 = 1000*(Long.parseLong(enterNum6.getText().toString()));
                    countDownInterval6 = 1;
                }
                //Don't do anything if there is an error parsing the string
                catch (NumberFormatException e){
                    return;
                }

                //Disable start button when it has already been clicked
                startBtn6.setEnabled(false);
                //Make pause and cancel available to click
                pauseBtn6.setEnabled(true);
                cancelBtn6.setEnabled(true);

                isPaused6 = false;
                isCanceled6 = false;
                enterNum6.setEnabled(false); //Disable text editing after start button is clicked

                new CountDownTimer(timeInMillis6, countDownInterval6){

                    @Override
                    public void onTick(long timeInMillis) {

                        //If either is true, cancel the timer
                        if(isPaused6 || isCanceled6){
                            cancel();
                        }
                        else{
                            //Divide time into seconds, minutes, and milliseconds
                            int secs = (int) (timeInMillis/1000);
                            int mins = secs/60;
                            secs = secs % 60;
                            int milliseconds = (int) (timeInMillis%1000);

                            //Set the text of the timer accordinly
                            enterNum6.setText("" + mins + ":" + String.format("%02d", secs) + ":" +
                                    String.format("%03d", milliseconds));
                            enterNum6.setTextColor(Color.BLACK);
                            //Used for when we pause the timer
                            remainingTime6 = timeInMillis;
                        }
                    }

                    @Override
                    //Let the user know that time is up
                    public void onFinish() {
                        enterNum6.setText("Time is finished!");
                    }
                }.start();
            }
        });

        pauseBtn6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(pauseBtn6.isChecked()) {
                    isPaused6 = true;
                    //Don't let the user edit the text
                    enterNum6.setEnabled(false);
                }
                else{
                    isPaused6 = false;
                    timeInMillis6 = remainingTime6; //How much time there is to count down
                    countDownInterval6 = 1; //1 ms difference in time

                    new CountDownTimer(timeInMillis6, countDownInterval6){

                        @Override
                        public void onTick(long timeInMillis) {

                            if(isPaused6 || isCanceled6){
                                cancel();
                            }
                            else{
                                //Divide time into seconds, minutes, and milliseconds
                                int secs = (int) (timeInMillis/1000);
                                int mins = secs/60;
                                secs = secs % 60;
                                int milliseconds = (int) (timeInMillis%1000);

                                //Set the text of the timer
                                enterNum6.setText("" + mins + ":" + String.format("%02d", secs) + ":" +
                                        String.format("%03d", milliseconds));
                                remainingTime6 = timeInMillis;
                            }
                        }

                        @Override
                        public void onFinish() {
                            enterNum6.setText("Time is finished!");
                        }
                    }.start();
                }

            }
        });

        cancelBtn6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                isCanceled6 = true;
                enterNum6.setText(""); //Reset the text
                enterNum6.setEnabled(true); //Allow the text to be modified

                //Set the buttons accordingly
                startBtn6.setEnabled(true);
                pauseBtn6.setEnabled(false);
                pauseBtn6.setChecked(false);
                cancelBtn6.setEnabled(false);
            }
        });

        startBtn7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //Try to parse the string to a number
                try {
                    timeInMillis7 = 1000*(Long.parseLong(enterNum7.getText().toString()));
                    countDownInterval7 = 1;
                }
                //Don't do anything if there is an error parsing the string
                catch (NumberFormatException e){
                    return;
                }

                //Disable start button when it has already been clicked
                startBtn7.setEnabled(false);
                //Make pause and cancel available to click
                pauseBtn7.setEnabled(true);
                cancelBtn7.setEnabled(true);

                isPaused7 = false;
                isCanceled7 = false;
                enterNum7.setEnabled(false); //Disable text editing after start button is clicked

                new CountDownTimer(timeInMillis7, countDownInterval7){

                    @Override
                    public void onTick(long timeInMillis) {

                        //If either is true, cancel the timer
                        if(isPaused7 || isCanceled7){
                            cancel();
                        }
                        else{
                            //Divide time into seconds, minutes, and milliseconds
                            int secs = (int) (timeInMillis/1000);
                            int mins = secs/60;
                            secs = secs % 60;
                            int milliseconds = (int) (timeInMillis%1000);

                            //Set the text of the timer accordinly
                            enterNum7.setText("" + mins + ":" + String.format("%02d", secs) + ":" +
                                    String.format("%03d", milliseconds));
                            enterNum7.setTextColor(Color.BLACK);
                            //Used for when we pause the timer
                            remainingTime7 = timeInMillis;
                        }
                    }

                    @Override
                    //Let the user know that time is up
                    public void onFinish() {
                        enterNum7.setText("Time is finished!");
                    }
                }.start();
            }
        });

        pauseBtn7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(pauseBtn7.isChecked()) {
                    isPaused7 = true;
                    //Don't let the user edit the text
                    enterNum7.setEnabled(false);
                }
                else{
                    isPaused7 = false;
                    timeInMillis7 = remainingTime7; //How much time there is to count down
                    countDownInterval7 = 1; //1 ms difference in time

                    new CountDownTimer(timeInMillis7, countDownInterval7){

                        @Override
                        public void onTick(long timeInMillis) {

                            if(isPaused7 || isCanceled7){
                                cancel();
                            }
                            else{
                                //Divide time into seconds, minutes, and milliseconds
                                int secs = (int) (timeInMillis/1000);
                                int mins = secs/60;
                                secs = secs % 60;
                                int milliseconds = (int) (timeInMillis%1000);

                                //Set the text of the timer
                                enterNum7.setText("" + mins + ":" + String.format("%02d", secs) + ":" +
                                        String.format("%03d", milliseconds));
                                remainingTime7 = timeInMillis;
                            }
                        }

                        @Override
                        public void onFinish() {
                            enterNum7.setText("Time is finished!");
                        }
                    }.start();
                }

            }
        });

        cancelBtn7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                isCanceled7 = true;
                enterNum7.setText(""); //Reset the text
                enterNum7.setEnabled(true); //Allow the text to be modified

                //Set the buttons accordingly
                startBtn7.setEnabled(true);
                pauseBtn7.setEnabled(false);
                pauseBtn7.setChecked(false);
                cancelBtn7.setEnabled(false);
            }
        });

        startBtn8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //Try to parse the string to a number
                try {
                    timeInMillis8 = 1000*(Long.parseLong(enterNum8.getText().toString()));
                    countDownInterval8 = 1;
                }
                //Don't do anything if there is an error parsing the string
                catch (NumberFormatException e){
                    return;
                }

                //Disable start button when it has already been clicked
                startBtn8.setEnabled(false);
                //Make pause and cancel available to click
                pauseBtn8.setEnabled(true);
                cancelBtn8.setEnabled(true);

                isPaused8 = false;
                isCanceled8 = false;
                enterNum8.setEnabled(false); //Disable text editing after start button is clicked

                new CountDownTimer(timeInMillis8, countDownInterval8){

                    @Override
                    public void onTick(long timeInMillis) {

                        //If either is true, cancel the timer
                        if(isPaused8 || isCanceled8){
                            cancel();
                        }
                        else{
                            //Divide time into seconds, minutes, and milliseconds
                            int secs = (int) (timeInMillis/1000);
                            int mins = secs/60;
                            secs = secs % 60;
                            int milliseconds = (int) (timeInMillis%1000);

                            //Set the text of the timer accordinly
                            enterNum8.setText("" + mins + ":" + String.format("%02d", secs) + ":" +
                                    String.format("%03d", milliseconds));
                            enterNum8.setTextColor(Color.BLACK);
                            //Used for when we pause the timer
                            remainingTime8 = timeInMillis;
                        }
                    }

                    @Override
                    //Let the user know that time is up
                    public void onFinish() {
                        enterNum8.setText("Time is finished!");
                    }
                }.start();
            }
        });

        pauseBtn8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(pauseBtn8.isChecked()) {
                    isPaused8 = true;
                    //Don't let the user edit the text
                    enterNum8.setEnabled(false);
                }
                else{
                    isPaused8 = false;
                    timeInMillis8 = remainingTime8; //How much time there is to count down
                    countDownInterval8 = 1; //1 ms difference in time

                    new CountDownTimer(timeInMillis8, countDownInterval8){

                        @Override
                        public void onTick(long timeInMillis) {

                            if(isPaused8 || isCanceled8){
                                cancel();
                            }
                            else{
                                //Divide time into seconds, minutes, and milliseconds
                                int secs = (int) (timeInMillis/1000);
                                int mins = secs/60;
                                secs = secs % 60;
                                int milliseconds = (int) (timeInMillis%1000);

                                //Set the text of the timer
                                enterNum8.setText("" + mins + ":" + String.format("%02d", secs) + ":" +
                                        String.format("%03d", milliseconds));
                                remainingTime8 = timeInMillis;
                            }
                        }

                        @Override
                        public void onFinish() {
                            enterNum8.setText("Time is finished!");
                        }
                    }.start();
                }

            }
        });

        cancelBtn8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                isCanceled8 = true;
                enterNum8.setText(""); //Reset the text
                enterNum8.setEnabled(true); //Allow the text to be modified

                //Set the buttons accordingly
                startBtn8.setEnabled(true);
                pauseBtn8.setEnabled(false);
                pauseBtn8.setChecked(false);
                cancelBtn8.setEnabled(false);
            }
        });

        return rootView;
    }
}
