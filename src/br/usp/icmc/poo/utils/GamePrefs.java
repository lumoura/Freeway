package br.usp.icmc.poo.utils;

import javafx.scene.input.KeyCode;

// Sets global values for the game
public class GamePrefs {

    // common ATARI 2600 screen resolution
    public static final int SCREEN_WIDTH = 306;
    public static final int SCREEN_HEIGHT = 193;

    // resize for a better visualization in modern PCs
    public static final int RESIZE = 4;

    // frames per second (ticks)
    public static final int FRAMERATE = 40;

    // time limit (miliseconds) = 2 minutes and 16 seconds according to the original game;
    public static final int TIMELIMIT = 136000;

    // input configuration
    public static final KeyCode[] UP = { KeyCode.UP, KeyCode.W };
    public static final KeyCode[] DOWN = { KeyCode.DOWN, KeyCode.S };
    public static final KeyCode[] START = { KeyCode.ENTER, KeyCode.SPACE };

    // start game config
    public static final int P0STARTINGX = 201;
    public static final int P1STARTINGX = 73;
    public static final int P0SCOREX = 194;
    public static final int P1SCOREX = 67;
    public static final int STARTY = 184;
    public static final int FINALY = 12;

    // car speed
    public static final int SPEED = 40;
    public static final int MAXCARSPEED = 150;
}
