package br.usp.icmc.poo.gameobj;

import br.usp.icmc.poo.funcs.ConstantFunction;
import br.usp.icmc.poo.funcs.Function;
import br.usp.icmc.poo.utils.GamePrefs;
import javafx.scene.paint.Color;

import static java.lang.Math.random;

// Blinking variant to the normal vehicle
public class BlinkingCar extends Car {
    private boolean visible;

    @Override
    public Car copy() {
        return new BlinkingCar(this.direction, this.x, this.y, this.speedFunction, this.color, this.type);
    }

    @Override
    public void animate(long frame){
        if(frame % (GamePrefs.FRAMERATE / 3)== 0) {
            if(visible)
            {
                composition.setOpacity(0);
                visible = false;
            }
            else
            {
                composition.setOpacity(1);
                visible = true;
            }
        }

        super.animate(frame);
    }

    private BlinkingCar(int direction, int x, int y, Function speedFunction, Color color, String type)
    {
        this.type = type;
        setup(direction, x, y, speedFunction, color);
    }

    public BlinkingCar(String type) {
        this.type = type;
        setup(1, 0, 0, new ConstantFunction(0), new Color(random(), random(), random(), 1));
    }
}
