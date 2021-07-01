package br.usp.icmc.poo.funcs;

import static java.lang.Math.sin;

// Evaluates the sine of t * multiplier / 30 (so it varies in a slower pace)
public class SineFunction implements Function {
    private double multiplier;
    private Function compositeFunction;

    @Override
    public int evaluate(long t) {
        // will vary between 10 and 110
        if(compositeFunction != null)
        {
            return 60 + (int) ((t * sin(compositeFunction.evaluate(t) / 30)) * 50);
        }
        return 60 + (int) ((sin(t * multiplier / 30)) * 50);
    }

    public SineFunction(double multiplier) {
        this.multiplier = multiplier;
    }

    public SineFunction(Function function) {
        compositeFunction = function;
    }
}
