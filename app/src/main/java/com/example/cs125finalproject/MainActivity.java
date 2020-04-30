package com.example.cs125finalproject;

import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    /** An array of the cells as buttons*/
    public static Button[] currentBoard = new Button[24];

    /** Tells whether or not there is a game in progress*/
    private Boolean gameInProgress = false;

    /**
     * Displays the remaining time that a user has to select
     * a cell after a value on their board has been drawn.
     * */
    private DrawTimer timer;

    /** The cell which contains the drawn value. */
    private Button button;

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
        middle.setBackgroundColor(getResources().getColor(R.color.colorYellow));
        for (int i = 0; i < 24; i++) {
            String buttonID = "b_" + i;
            String buttonValue = GameSetup.playerBoard.get(i).toString();
            int resID = getResources().getIdentifier(buttonID, "id", getPackageName());
            Button button = findViewById(resID);
            button.setText(buttonValue);
            currentBoard[i] = findViewById(resID);
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

    /**
     * Draws a random value from the 'un-drawn' [see GameSetup.draw()] values
     * and starts a 10 second timer if the drawn value is on the player's board
     * */
    public void drawValue() {
        try {
            timer.cancel();
            button.setOnClickListener(null);
            int value = GameSetup.draw();
            TextView display = findViewById(R.id.draw);
            display.setText(((Object) value).toString());
            for (int i = 0; i < 24; i++) {
                timer = new DrawTimer(11000, 1000,
                        (TextView) findViewById(R.id.timer), currentBoard[i]);
                if (value == GameSetup.playerBoard.get(i)) {
                    timer.start();
                    currentBoard[i].setOnClickListener(this);
                    button = currentBoard[i];
                    return;
                }
            }
        } catch (Exception e) {
            int value = GameSetup.draw();
            TextView display = findViewById(R.id.draw);
            display.setText(((Object) value).toString());
            for (int i = 0; i < 24; i++) {
                timer = new DrawTimer(11000, 1000,
                        (TextView) findViewById(R.id.timer), currentBoard[i]);
                if (value == GameSetup.playerBoard.get(i)) {
                    timer.start();
                    currentBoard[i].setOnClickListener(this);
                    button = currentBoard[i];
                    return;
                }
            }
        }
    }

    /**
     * Gets the input button's background color.
     * @return the integer value that represents the button's color.
     * */
    private int getButtonBackgroundColor(Button button){
        int buttonColor = 0;
        if (button.getBackground() instanceof ColorDrawable) {
            ColorDrawable cd = (ColorDrawable) button.getBackground();
            buttonColor = cd.getColor();
        }
        return buttonColor;
    }

    /**
     * Tests the color of the input button.
     * @return true if the cell (button) has been filled, and false otherwise.
     * */
    public boolean isYellow(Button button) {
        if (button == null) {
            return false;
        }
        int color = getButtonBackgroundColor(button);
        return color == getResources().getColor(R.color.colorYellow);

    }

    /**
     * Determines whether or not the player has won the game.
     * @return true if the player has won, and false otherwise.
     * */
    public boolean win() {
        if (isYellow(currentBoard[0]) && isYellow(currentBoard[6]) && isYellow(currentBoard[17]) && isYellow(currentBoard[23])) {
            return true;
        } else if (isYellow(currentBoard[4]) && isYellow(currentBoard[8]) && isYellow(currentBoard[15]) && isYellow(currentBoard[19])) {
            return true;
        }
        for (int i = 0; i < 5; i++) {
            if (i == 2) {
                if (isYellow(currentBoard[i]) && isYellow(currentBoard[i + 5]) && isYellow(currentBoard[i + 14]) && isYellow(currentBoard[i + 19])) {
                    return true;
                }
            }
            if (i < 2 && isYellow(currentBoard[i]) && isYellow(currentBoard[i + 5]) && isYellow(currentBoard[i + 10])
                    && isYellow(currentBoard[i + 14]) && isYellow(currentBoard[i + 19])) {
                return true;
            } else if (i > 2 && isYellow(currentBoard[i]) && isYellow(currentBoard[i + 5]) && isYellow(currentBoard[i + 9])
                    && isYellow(currentBoard[i + 14]) && isYellow(currentBoard[i + 19])) {
                return true;
            }
        }
        for (int j = 0; j <= 10; j += 5) {
            if (j < 10) {
                if (isYellow(currentBoard[j]) && isYellow(currentBoard[j + 1]) && isYellow(currentBoard[j + 2])
                        && isYellow(currentBoard[j + 3]) && isYellow(currentBoard[j + 4])) {
                    return true;
                }
            } else if (j == 10) {
                if (isYellow(currentBoard[j]) && isYellow(currentBoard[j + 1]) && isYellow(currentBoard[j + 2]) && isYellow(currentBoard[j + 3])) {
                    return true;
                }
            }
        }
        for (int k = 14; k < 20; k += 5) {
            if (isYellow(currentBoard[k]) && isYellow(currentBoard[k + 1]) && isYellow(currentBoard[k + 2])
                    && isYellow(currentBoard[k + 3]) && isYellow(currentBoard[k + 4])) {
                return true;
            }
        }
        return false;
    }

    /** Alerts the player when they have won. */
    public void winCheck() {
        if (win()) {
            Toast.makeText(this, "You have won.", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onClick(View v) {
        if (!gameInProgress) {
            Toast.makeText(this, "There is no game in progress.", Toast.LENGTH_SHORT).show();
        } else {
            fillCell((Button) v);
        }
    }

    /**
     * Function to check whether or not a cell is filled and fill it.
     */
    public void fillCell(Button button) {
        button.setBackgroundColor(getResources().getColor(R.color.colorYellow));
        timer.cancel();
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
