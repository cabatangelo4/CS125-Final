package com.example.cs125finalproject;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class GameSetup {

    /** A class which is used to select "random" integer values. */
    private Random random = new Random();

    /** A map which contains all values that have already been drawn. */
    private Map<Integer, Integer> drawn = new HashMap<>();

    /** The number of different values that have aready been drawn. */
    private int drawnIndex = 0;

    /** The current player's board setup. */
    private final Map<Integer, Integer> playerBoard = boardValues();


    /** Generates 25 unique random integers from 1 to 100 inclusive. */
    public Map<Integer, Integer> boardValues() {
        int i = 0;
        Map<Integer, Integer> boardMap = new HashMap<>();
        while (i < 25) {
            int randInt = random.nextInt(100) + 1;
            if (!(boardMap.containsValue(randInt))) {
                boardMap.put(i, randInt);
                i++;
            }
        }
        return boardMap;
    }

    /** Draws a value from the remaining un-drawn values (returns -1 if all values have been drawn). */
    public int draw() {
        int i = 0;
        while(i < 101) {
            int randInt = random.nextInt(100) + 1;
            if (!(drawn.containsValue(randInt))) {
                drawn.put(drawnIndex, randInt);
                drawnIndex++;
                return randInt;
            }
            i++;
        }
        return -1;
    }

    /** Determines whether or not the player has won the game.
     * @return true if the player has won, and false otherwise. */
    public boolean checkWin() {
        if (drawn.containsValue(playerBoard.get(0)) && drawn.containsValue(playerBoard.get(6))
                && drawn.containsValue(playerBoard.get(12)) && drawn.containsValue(playerBoard.get(18))
                && drawn.containsKey(playerBoard.get(24))) {
            return true;
        } else if (drawn.containsValue(playerBoard.get(4)) && drawn.containsValue(playerBoard.get(8))
                && drawn.containsValue(playerBoard.get(12)) && drawn.containsValue(playerBoard.get(16))
                && drawn.containsKey(playerBoard.get(20))) {
            return true;
        }
        for (int i = 0; i < 5; i++) {
            if (drawn.containsValue(playerBoard.get(i)) && drawn.containsValue(playerBoard.get(i + 5))
                    && drawn.containsValue(playerBoard.get(i + 10)) && drawn.containsValue(playerBoard.get(i + 15))
                    && drawn.containsValue(playerBoard.get(i + 20))) {
                return true;
            }
        }
        for (int j = 0; j < 25; j += 5) {
            if (drawn.containsValue(playerBoard.get(j)) && drawn.containsValue(playerBoard.get(j + 1))
                    && drawn.containsValue(playerBoard.get(j + 2)) && drawn.containsValue(playerBoard.get(j + 3))
                    && drawn.containsValue(playerBoard.get(j + 4))) {
                return true;
            }
        }
        return false;
    }
}
