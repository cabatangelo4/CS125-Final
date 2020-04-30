package com.example.cs125finalproject;

import android.os.CountDownTimer;
import android.widget.Button;
import android.widget.TextView;

public class DrawTimer extends CountDownTimer {
    private TextView timeRemaining;
    public boolean finished = false;
    private Button button;
    public DrawTimer(long millisInFuture, long countDownInterval, TextView view, Button b) {
        super(millisInFuture, countDownInterval);
        timeRemaining = view;
        button = b;
    }
    public void onTick(long millisUntilFinished) {
        timeRemaining.setText(String.valueOf(millisUntilFinished / 1000));
    }
    public void onFinish() {
        String timeUp = "TIME'S UP";
        finished = true;
        button.setOnClickListener(null);
        timeRemaining.setText(timeUp);
    }
}
