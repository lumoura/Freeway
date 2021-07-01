package br.usp.icmc.poo.gameobj;

import javafx.scene.Group;

// Manages the score for each player and displays them on screen
public class ScoreDisplay {
    private int x;
    private int y;

    private int score;

    private Group father;
    private Number tens;
    private Number units;

    public void setScore(int score) {
        this.score = score;
        units.changeValue(score % 10);
    }

    public int getScore() {
        return score;
    }

    public ScoreDisplay(int x, int y, Group father) {
        this.x = x;
        this.y = y;
        score = 0;
        this.father = father;

        units = new Number(x + 17, y, 0);
        units.draw(this.father);
    }

    public void score() {
        score++;

        if(score == 10) {
            tens = new Number(x, y, 1);
            tens.draw(this.father);
        }
        units.changeValue(score % 10);

        if(tens != null)
            tens.changeValue(score / 10);
    }

    public void erase() {
        units.erase(father);
        if(tens != null) {
            tens.erase(father);
        }
    }
}
