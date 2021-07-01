package br.usp.icmc.poo.gameobj;

import javafx.scene.Group;
import javafx.scene.image.ImageView;

// This class determines what any displayable game object should have and do
public abstract class Actor {
    int x;
    int y;
    int height;
    int width;
    ImageView sprite;

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public abstract void animate(long frame);

    public abstract void relocate(int x, int y);

    public abstract void draw(Group g);

    public abstract void erase(Group g);
}
