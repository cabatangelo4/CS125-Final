package com.example.cs125finalproject;

import com.example.cs125finalproject.GameSetup;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.Random;
import java.util.HashMap;
import java.util.Map;
import androidx.appcompat.app.AppCompatActivity;

public class NewGameActivity extends AppCompatActivity implements View.OnClickListener {
    private Button[] buttons = new Button[24];
    @Override
    public void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bingo);
        findViewById(R.id.fillcell).setOnClickListener(unused -> fillCell());
        populateBoard();
    }
    public void populateBoard() {
        Map<Integer, Integer> board = GameSetup.boardValues();
        for (int i = 0; i < 24; i++) {
            if (i == 12) {
                continue;
            }
            String buttonID = "b_" + i;
            int resID = getResources().getIdentifier(buttonID, "id", getPackageName());
            buttons[i] = findViewById(resID);
        }
        for (int i = 0; i < 24; i++) {
            if (i == 12) {
                continue;
            }
            buttons[i].setText(board.get(i));
        }
    }
    @Override
    public void onClick(View v) {
        String start = ((Button) v).getText().toString();
        if (!start.startsWith("b_")) {
            return;
        }
        Toast.makeText(this, "Will mark unmarked cells", Toast.LENGTH_SHORT).show();
        return;
    }

    public void fillCell() {
        return;
    }
}
