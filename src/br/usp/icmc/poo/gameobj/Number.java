package br.usp.icmc.poo.gameobj;

import br.usp.icmc.poo.utils.GamePrefs;
import br.usp.icmc.poo.utils.Textures;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

// Simply a number (0 to 9) to be displayed (used as the score display for players and level picker)
public class Number extends Actor {
    private Image tex[];

    void changeValue(int value)
    {
        sprite.setImage(tex[value]);
    }

    public Number(int x, int y, int value){
        this.x = x;
        this.y = y;

        tex = new Image[10];

        Image temp = new Image(Textures.NUMBER0);
        tex[0] = new Image(Textures.NUMBER0, temp.getWidth() * GamePrefs.RESIZE, 0, true, false);
        tex[1] = new Image(Textures.NUMBER1, temp.getWidth() * GamePrefs.RESIZE, 0, true, false);
        tex[2] = new Image(Textures.NUMBER2, temp.getWidth() * GamePrefs.RESIZE, 0, true, false);
        tex[3] = new Image(Textures.NUMBER3, temp.getWidth() * GamePrefs.RESIZE, 0, true, false);
        tex[4] = new Image(Textures.NUMBER4, temp.getWidth() * GamePrefs.RESIZE, 0, true, false);
        tex[5] = new Image(Textures.NUMBER5, temp.getWidth() * GamePrefs.RESIZE, 0, true, false);
        tex[6] = new Image(Textures.NUMBER6, temp.getWidth() * GamePrefs.RESIZE, 0, true, false);
        tex[7] = new Image(Textures.NUMBER7, temp.getWidth() * GamePrefs.RESIZE, 0, true, false);
        tex[8] = new Image(Textures.NUMBER8, temp.getWidth() * GamePrefs.RESIZE, 0, true, false);
        tex[9] = new Image(Textures.NUMBER9, temp.getWidth() * GamePrefs.RESIZE, 0, true, false);

        sprite = new ImageView(tex[value]);
        height = (int) temp.getHeight();
        width = (int) temp.getWidth();

        this.sprite.relocate(x * GamePrefs.RESIZE, y * GamePrefs.RESIZE);
    }

    @Override
    public void animate(long frame) {

    }

    @Override
    public void relocate(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public void draw(Group g) { g.getChildren().add(this.sprite); }

    @Override
    public void erase(Group g) { g.getChildren().remove(this.sprite); }
}
