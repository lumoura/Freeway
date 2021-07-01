package br.usp.icmc.poo.funcs;

import br.usp.icmc.poo.utils.GamePrefs;

// Returns a random integer in a range (any integer between 0 and max car speed)
public class RandomFunction implements Function {
    private int max = 100;
    private int time = 0;
    private int result;

    public RandomFunction() {
        result = (int) (Math.random() * max);
    }

    @Override
    public int evaluate(long t) {
        if(t >= time) { // randomly changes to a new random speed
            result = (int) (Math.random() * max);

            // the next speed change will occur in a random time between 0.5 sand 3.5 seconds
            time = (int) (t + GamePrefs.FRAMERATE * 0.5 + Math.random() * GamePrefs.FRAMERATE * 3);
        }
        return result;
    }
}
