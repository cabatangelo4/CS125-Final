package com.example.cs125finalproject;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class GameSetup {

    /** A map which contains all values that have already been drawn. */
    private static Map<Integer, Integer> drawnMap = new HashMap<>();

    /** The number of different values that have aready been drawn. */
    private static int drawnCount = 0;

    /** The current player's board setup. */
    public static final Map<Integer, Integer> playerBoard = boardValues();

    /** Generates 25 unique random integers from 1 to 100 inclusive. */
    public static Map<Integer, Integer> boardValues() {
        Random random1 = new Random();
        int i = 0;
        Map<Integer, Integer> boardMap = new HashMap<>();
        while (i < 24) {
            int randInt = random1.nextInt(100) + 1;
            if (!(boardMap.containsValue(randInt))) {
                boardMap.put(i, randInt);
                i++;
            }
        }
        return boardMap;
    }

    /** Draws a value from the remaining un-drawn values (returns -1 if all values have been drawn). */
    public static int draw() {
        Random random1 = new Random();
        int i = 0;
        while(i < 1001 && drawnCount < 100) {
            int randInt = random1.nextInt(100) + 1;
            if (!(drawnMap.containsValue(randInt))) {
                drawnMap.put(drawnCount, randInt);
                drawnCount++;
                return randInt;
            }
            i++;
        }
        return -1;
    }

    /** Determines whether or not the player has won the game.
     * @return true if the player has won, and false otherwise. */
    public static boolean win() {
        if (drawnMap.containsValue(playerBoard.get(0)) && drawnMap.containsValue(playerBoard.get(6))
                && drawnMap.containsValue(playerBoard.get(17)) && drawnMap.containsValue(playerBoard.get(23))) {
            return true;
        } else if (drawnMap.containsValue(playerBoard.get(4)) && drawnMap.containsValue(playerBoard.get(8))
                && drawnMap.containsValue(playerBoard.get(15)) && drawnMap.containsValue(playerBoard.get(19))) {
            return true;
        }
        for (int i = 0; i < 5; i++) {
            if (i == 2) {
                if (drawnMap.containsValue(playerBoard.get(i)) && drawnMap.containsValue(playerBoard.get(i + 5))
                        && drawnMap.containsValue(playerBoard.get(i + 14)) && drawnMap.containsValue(playerBoard.get(i + 19))) {
                    return true;
                }
            }
            if (i < 2 && drawnMap.containsValue(playerBoard.get(i)) && drawnMap.containsValue(playerBoard.get(i + 5))
                    && drawnMap.containsValue(playerBoard.get(i + 10)) && drawnMap.containsValue(playerBoard.get(i + 14))
                    && drawnMap.containsValue(playerBoard.get(i + 19))) {
                return true;
            } else if (i > 2 && drawnMap.containsValue(playerBoard.get(i)) && drawnMap.containsValue(playerBoard.get(i + 5))
                    && drawnMap.containsValue(playerBoard.get(i + 9)) && drawnMap.containsValue(playerBoard.get(i + 14))
                    && drawnMap.containsValue(playerBoard.get(i + 19))) {
                return true;
            }
        }
        for (int j = 0; j <= 10; j += 5) {
            if (j < 10) {
                if (drawnMap.containsValue(playerBoard.get(j)) && drawnMap.containsValue(playerBoard.get(j + 1))
                        && drawnMap.containsValue(playerBoard.get(j + 2)) && drawnMap.containsValue(playerBoard.get(j + 3))
                        && drawnMap.containsValue(playerBoard.get(j + 4))) {
                    return true;
                }
            } else if (j == 10) {
                if (drawnMap.containsValue(playerBoard.get(j)) && drawnMap.containsValue(playerBoard.get(j + 1))
                        && drawnMap.containsValue(playerBoard.get(j + 2)) && drawnMap.containsValue(playerBoard.get(j + 3))) {
                    return true;
                }
            }
        }
        for (int k = 14; k < 20; k += 5) {
            if (drawnMap.containsValue(playerBoard.get(k)) && drawnMap.containsValue(playerBoard.get(k + 1))
                    && drawnMap.containsValue(playerBoard.get(k + 2)) && drawnMap.containsValue(playerBoard.get(k + 3))
                    && drawnMap.containsValue(playerBoard.get(k + 4))) {
                return true;
            }
        }
        return false;
    }
}
