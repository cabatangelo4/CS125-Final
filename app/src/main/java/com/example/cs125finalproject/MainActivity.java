package com.example.cs125finalproject;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.drawable.DrawableCompat;

import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    /** An array of the cells as buttons*/
    private Button[] currentBoard = new Button[24];

    /** A map which contains all values that have already been drawn. */
    //private static Map<Integer, Integer> drawnMap = new HashMap<>();

    /** The number of different values that have aready been drawn. */
    //private static int drawnIndex = 0;

    /** Tells whether or not there is a game in progress*/
    private Boolean gameInProgress = false;

    /** The player's board (from GameSetup class). */
    //private static final Map<Integer, Integer> board = GameSetup.playerBoard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bingo);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //Activates the reset button
        Button reset = findViewById(R.id.resetBoard);
        reset.setOnClickListener(unused ->
                resetBoard());
                //Toast.makeText(this, "Will clear the board", Toast.LENGTH_SHORT).show());
        //Activates the New Game Button
        Button newGame = findViewById(R.id.newGame);
        //Calls populateBoard when New Game is pressed
        newGame.setOnClickListener(unused ->
                populateBoard());
        //Activates the Draw Number button
        Button drawNumber = findViewById(R.id.drawNumber);
        drawNumber.setOnClickListener(unused ->
                drawValue());
        Button checkWin = findViewById(R.id.checkWin);
        checkWin.setOnClickListener(unused ->
                winCheck());
    }

    /**
     * A function that fills each cell of the board with a random number and activates their onClick function
     */
    public void populateBoard() {
        Button middle = findViewById(R.id.b_center);
        String freeSpace = "Free";
        middle.setText(freeSpace);
        middle.setBackgroundColor(getResources().getColor(R.color.colorGreen));
        for (int i = 0; i < 24; i++) {
            String buttonID = "b_" + i;
            String buttonValue = GameSetup.playerBoard.get(i).toString();
            int resID = getResources().getIdentifier(buttonID, "id", getPackageName());
            Button button = findViewById(resID);
            button.setText(buttonValue);
            currentBoard[i] = findViewById(resID);
            button.setOnClickListener(this);
        }
        gameInProgress = true;
    }

    public void resetBoard() {
        Button middle = findViewById(R.id.b_center);
        middle.setText("");
        middle.setBackgroundResource(android.R.drawable.btn_default);
        for (int i = 0; i < 24; i++) {
            String buttonID = "b_" + i;
            int resID = getResources().getIdentifier(buttonID, "id", getPackageName());
            Button button = findViewById(resID);
            button.setText("");
            button.setBackgroundResource(android.R.drawable.btn_default);
        }
        gameInProgress = false;
    }

    public void drawValue() {
        int value = GameSetup.draw();
        //Object displayValue = value;
        //Map<Integer, Integer> board = GameSetup.playerBoard;
        for (int i = 0; i < 24; i++) {
            if (value == GameSetup.playerBoard.get(i)) {
                fillCell(currentBoard[i]);
                //return;
            }
        }
    }

    public void winCheck() {
        if (GameSetup.win()) {
            Toast.makeText(this, "You have won.", Toast.LENGTH_SHORT).show();
        }
        /*else {
            Toast.makeText(this, "You have not won.", Toast.LENGTH_SHORT).show();
        }*/
    }

    @Override
    public void onClick(View v) {
        if (gameInProgress) {
            fillCell((Button) v);
        } else {
            Toast.makeText(this, "There is no game in progress.", Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * Function to check whether or not a cell is filled and fill it.
     */
    public void fillCell(Button button) {
        button.setBackgroundColor(getResources().getColor(R.color.colorGreen));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
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
