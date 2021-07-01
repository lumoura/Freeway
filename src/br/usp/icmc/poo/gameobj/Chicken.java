package br.usp.icmc.poo.gameobj;

import br.usp.icmc.poo.utils.GamePrefs;
import br.usp.icmc.poo.utils.Textures;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;

import static java.lang.Math.abs;
import static java.lang.Math.min;
import static sun.swing.MenuItemLayoutHelper.max;

// This is the player pawn class
public class Chicken extends Actor {
    private Image tex0;
    private Image tex1;
    private int partialY;

    private int playerID;
    private boolean isControllable;
    private int speed;

    // Sets all the inputs to the pawn
    private void setup(Group parent){
        parent.requestFocus();
        parent.setFocusTraversable(false);

        parent.addEventHandler(KeyEvent.KEY_PRESSED, event -> {
            if(event.getCode() == GamePrefs.UP[playerID])
            {
                if(isControllable)
                {
                    speed = 50;
                }
            }
            else if(event.getCode() == GamePrefs.DOWN[playerID])
            {
                if(isControllable)
                {
                    speed = -50;
                }
            }
        });

        parent.addEventHandler(KeyEvent.KEY_RELEASED, event -> {
            if(event.getCode() == GamePrefs.UP[playerID])
            {
                if(isControllable)
                {
                    speed = 0;
                }
            }
            else if(event.getCode() == GamePrefs.DOWN[playerID])
            {
                if(isControllable)
                {
                    speed = 0;
                }
            }
        });

        isControllable = true;
    }

    // Sets the player owner of the pawn
    // Sets the textures, dimensions and position
    public Chicken(int x, int y, int playerID)
    {
        this.x = x;
        this.y = y;
        this.playerID = playerID;

        Image temp = new Image(Textures.CHICKEN0);
        this.tex0 = new Image(Textures.CHICKEN0, temp.getWidth() * GamePrefs.RESIZE, 0, true, false);
        this.tex1 = new Image(Textures.CHICKEN1, temp.getWidth() * GamePrefs.RESIZE, 0, true, false);
        this.sprite = new ImageView(tex0);
        this.height = (int) temp.getHeight();
        this.width = (int) temp.getWidth();
        this.sprite.relocate(x * GamePrefs.RESIZE, y * GamePrefs.RESIZE);
    }

    // Animation (this actor has 2 different textures in its sprite)
    @Override
    public void animate(long frame) {
        partialY += speed;

        int movementThreshold = 30;
        if(abs(partialY) >= movementThreshold)
        {
            y -= partialY/ movementThreshold;
            y = min(y, GamePrefs.STARTY);
            y = max(y, GamePrefs.FINALY);
            partialY = 0;
        }

        if(((y + 1) / 4) % 2 == 0)
        {
            sprite.setImage(tex0);
        }
        else
        {
            sprite.setImage(tex1);
        }
        sprite.relocate(x * GamePrefs.RESIZE, y * GamePrefs.RESIZE);
    }

    // Moves the actor to a new position
    @Override
    public void relocate(int x, int y) {
        this.x = x;
        this.y = y;
    }

    // Adds this actor to a group (if the group is on a scene, it will be drawn)
    @Override
    public void draw(Group g) {
        g.getChildren().add(this.sprite);
        setup(g);
    }

    // Remove this actor from a group (if it was on screen, it is erased)
    @Override
    public void erase(Group g) {

    }
}
