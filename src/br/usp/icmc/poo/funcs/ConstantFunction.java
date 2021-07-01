package br.usp.icmc.poo.funcs;

import br.usp.icmc.poo.utils.GamePrefs;

// Simply evaluates to the constant set when constructed
public class ConstantFunction implements Function {
    private int result = GamePrefs.SPEED;

    public ConstantFunction(int constant) {
        result = constant;
    }

    @Override
    public int evaluate(long t) {
        return result;
    }
}
