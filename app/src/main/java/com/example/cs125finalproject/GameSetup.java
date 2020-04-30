package com.example.cs125finalproject;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class GameSetup {

    /**
     * A map which contains all values that have already been drawn.
     */
    private static Map<Integer, Integer> drawnMap = new HashMap<>();

    /**
     * The number of different values that have already been drawn.
     */
    private static int drawnCount = 0;

    /**
     * The current player's board setup.
     */
    public static final Map<Integer, Integer> playerBoard = boardValues();


    /**
     * Generates 25 unique random integers from 1 to 100 inclusive.
     */
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

    /**
     * Draws a value from the remaining un-drawn values
     * (resets if all values have been drawn once).
     */
    public static int draw() {
        Random random1 = new Random();
        int i = 0;
        if (drawnCount < 101) {
            while (i < 1001) {
                int randInt = random1.nextInt(100) + 1;
                if (!(drawnMap.containsValue(randInt))) {
                    drawnMap.put(drawnCount, randInt);
                    drawnCount++;
                    return randInt;
                }
                i++;
            }
        }
        drawnCount = 0;
        drawnMap = new HashMap<Integer, Integer>();
        return draw();
    }
}
