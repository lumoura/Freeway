package br.usp.icmc.poo.funcs;

import br.usp.icmc.poo.gameobj.Car;

// Simply sets the speed to the "copy" cars to the "master" car
public class ImitateFunction implements Function{
    private Car c;

    public ImitateFunction(Car c){
        this.c = c;
    }

    @Override
    public int evaluate(long t) {
        return c.getSpeedX();
    }
}
