package com.example.cs125finalproject;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class LaunchActivity extends AppCompatActivity {
    @Override
    public void onCreate(final Bundle savedInstanceState) {
        //Required call
        super.onCreate(savedInstanceState);
        //Set up UI from activity_main.xml
        setContentView(R.layout.activity_main);
        //Set a listener for the new game button
        findViewById(R.id.newGame).setOnClickListener(unused -> startActivity(
                new Intent(this, GameSetup.class)));


    }
}
