package br.usp.icmc.poo.gameobj;

import br.usp.icmc.poo.funcs.ConstantFunction;
import br.usp.icmc.poo.funcs.Function;
import br.usp.icmc.poo.utils.GamePrefs;
import javafx.scene.paint.Color;

import static java.lang.Math.random;

// Colorful variant of a vehicle (alternates between random colors every x frames)
public class ColorfulCar extends Car {
    @Override
    public Car copy() {
        return new ColorfulCar(this.direction, this.x, this.y, this.speedFunction, this.color, this.type);
    }

    @Override
    public void animate(long frame){
        if(frame % (GamePrefs.FRAMERATE / 2)== 0) {
            colorOverlay.setFill(new Color(random(), random(), random(), 1));
        }

        super.animate(frame);
    }

    private ColorfulCar(int direction, int x, int y, Function speedFunction, Color color, String type)
    {
        this.type = type;
        setup(direction, x, y, speedFunction, color);
    }

    public ColorfulCar(String type) {
        this.type = type;
        setup(1, 0, 0, new ConstantFunction(0), new Color(random(), random(), random(), 1));
    }
}
