package br.usp.icmc.poo.utils;

import br.usp.icmc.poo.funcs.ConstantFunction;
import br.usp.icmc.poo.funcs.RandomFunction;
import br.usp.icmc.poo.funcs.SineFunction;
import br.usp.icmc.poo.gameobj.BlinkingCar;
import br.usp.icmc.poo.gameobj.Car;
import br.usp.icmc.poo.gameobj.ColorfulCar;
import br.usp.icmc.poo.gameobj.Lane;
import javafx.scene.Group;

import java.util.Random;

// Determines each level of the game (8 different levels total)
public class Level {
    private static int yPos[] = {23, 39, 55, 71, 87, 104, 120, 136, 152, 168};
    private static Car normalCar = new Car("car");
    private static ColorfulCar colorfulCar = new ColorfulCar("car");
    private static BlinkingCar blinkingCar = new BlinkingCar("car");
    private static Car normalTruck = new Car("truck");
    private static ColorfulCar colorfulTruck = new ColorfulCar("truck");
    private static BlinkingCar blinkingTruck = new BlinkingCar("truck");
    private Lane[] lanes;

    public Lane[] getLanes(int level, Group parent) {
        if(lanes != null) {
            if (lanes[0] != null) {
                for (Lane l : lanes) {
                    l.erase();
                }
            }
        }

        lanes = new Lane[10];

        switch (level)
        {
            case(1): {  // Original level 1 of the freeway game
                lanes[0] = new Lane(yPos[0], -1, 1, 0, parent, new ConstantFunction(10), normalCar);
                lanes[1] = new Lane(yPos[1], -1, 1, 0, parent, new ConstantFunction(20), normalCar);
                lanes[2] = new Lane(yPos[2], -1, 1, 0, parent, new ConstantFunction(30), normalCar);
                lanes[3] = new Lane(yPos[3], -1, 1, 0, parent, new ConstantFunction(40), normalCar);
                lanes[4] = new Lane(yPos[4], -1, 1, 0, parent, new ConstantFunction(50), normalCar);
                lanes[5] = new Lane(yPos[5], 1, 1, 0, parent, new ConstantFunction(50), normalCar);
                lanes[6] = new Lane(yPos[6], 1, 1, 0, parent, new ConstantFunction(40), normalCar);
                lanes[7] = new Lane(yPos[7], 1, 1, 0, parent, new ConstantFunction(30), normalCar);
                lanes[8] = new Lane(yPos[8], 1, 1, 0, parent, new ConstantFunction(20), normalCar);
                lanes[9] = new Lane(yPos[9], 1, 1, 0, parent, new ConstantFunction(10), normalCar);
                break;
            }
            case(2): {
                lanes[0] = new Lane(yPos[0], -1, 2, 20, parent, new ConstantFunction(10), colorfulCar);
                lanes[1] = new Lane(yPos[1], -1, 2, 40, parent, new ConstantFunction(20), normalCar);
                lanes[2] = new Lane(yPos[2], -1, 3, 20, parent, new ConstantFunction(50), blinkingCar);
                lanes[3] = new Lane(yPos[3], -1, 2, 180, parent, new ConstantFunction(40), normalCar);
                lanes[4] = new Lane(yPos[4], -1, 1, 0, parent, new ConstantFunction(45), normalTruck);
                lanes[5] = new Lane(yPos[5], 1, 2, 180, parent, new ConstantFunction(50), colorfulCar);
                lanes[6] = new Lane(yPos[6], 1, 3, 20, parent, new ConstantFunction(45), normalCar);
                lanes[7] = new Lane(yPos[7], 1, 2, 40, parent, new ConstantFunction(30), normalCar);
                lanes[8] = new Lane(yPos[8], 1, 2, 20, parent, new ConstantFunction(20), colorfulCar);
                lanes[9] = new Lane(yPos[9], 1, 1, 0, parent, new ConstantFunction(10), normalCar);
                break;
            }
            case(3): {
                lanes[0] = new Lane(yPos[0], -1, 1, 0, parent, new SineFunction(0.2), colorfulCar);
                lanes[1] = new Lane(yPos[1], -1, 1, 0, parent, new SineFunction(0.4), normalCar);
                lanes[2] = new Lane(yPos[2], -1, 1, 0, parent, new SineFunction(0.6), blinkingCar);
                lanes[3] = new Lane(yPos[3], -1, 1, 0, parent, new SineFunction(0.8), normalCar);
                lanes[4] = new Lane(yPos[4], -1, 1, 0, parent, new SineFunction(1), colorfulCar);
                lanes[5] = new Lane(yPos[5], 1, 1, 0, parent, new SineFunction(1), colorfulCar);
                lanes[6] = new Lane(yPos[6], 1, 1, 0, parent, new SineFunction(0.8), normalCar);
                lanes[7] = new Lane(yPos[7], 1, 1, 0, parent, new SineFunction(0.6), blinkingCar);
                lanes[8] = new Lane(yPos[8], 1, 1, 0, parent, new SineFunction(0.4), normalCar);
                lanes[9] = new Lane(yPos[9], 1, 1, 0, parent, new SineFunction(0.2), colorfulCar);
                break;
            }
            case(4): {
                lanes[0] = new Lane(yPos[0], -1, 2, 30, parent, new RandomFunction(), normalCar);
                lanes[1] = new Lane(yPos[1], -1, 1, 0, parent, new SineFunction(1), blinkingTruck);
                lanes[2] = new Lane(yPos[2], -1, 1, 0, parent, new ConstantFunction(30), normalCar);
                lanes[3] = new Lane(yPos[3], -1, 1, 0, parent, new RandomFunction(), colorfulCar);
                lanes[4] = new Lane(yPos[4], -1, 2, 60, parent, new SineFunction(2), normalCar);
                lanes[5] = new Lane(yPos[5], 1, 1, 0, parent, new ConstantFunction(50), normalCar);
                lanes[6] = new Lane(yPos[6], 1, 1, 0, parent, new RandomFunction(), normalTruck);
                lanes[7] = new Lane(yPos[7], 1, 2, 10, parent, new SineFunction(1), normalCar);
                lanes[8] = new Lane(yPos[8], 1, 1, 0, parent, new RandomFunction(), blinkingTruck);
                lanes[9] = new Lane(yPos[9], 1, 3, 40, parent, new ConstantFunction(20), colorfulCar);
                break;
            }
            case(5): {
                lanes[0] = new Lane(yPos[0], -1, 1, 0, parent, new RandomFunction(), normalCar);
                lanes[1] = new Lane(yPos[1], -1, 1, 0, parent, new RandomFunction(), normalCar);
                lanes[2] = new Lane(yPos[2], -1, 1, 0, parent, new RandomFunction(), normalCar);
                lanes[3] = new Lane(yPos[3], -1, 1, 0, parent, new RandomFunction(), normalCar);
                lanes[4] = new Lane(yPos[4], -1, 1, 0, parent, new RandomFunction(), normalCar);
                lanes[5] = new Lane(yPos[5], 1, 1, 0, parent, new RandomFunction(), normalCar);
                lanes[6] = new Lane(yPos[6], 1, 1, 0, parent, new RandomFunction(), normalCar);
                lanes[7] = new Lane(yPos[7], 1, 1, 0, parent, new RandomFunction(), normalCar);
                lanes[8] = new Lane(yPos[8], 1, 1, 0, parent, new RandomFunction(), normalCar);
                lanes[9] = new Lane(yPos[9], 1, 1, 0, parent, new RandomFunction(), normalCar);
                break;
            }
            case(6): { // TOO MANY CARS!
                lanes[0] = new Lane(yPos[0], -1, 5, 10, parent, new ConstantFunction(10), normalCar);
                lanes[1] = new Lane(yPos[1], -1, 4, 10, parent, new SineFunction(1), normalCar);
                lanes[2] = new Lane(yPos[2], -1, 3, 10, parent, new ConstantFunction(20), normalCar);
                lanes[3] = new Lane(yPos[3], -1, 2, 10, parent, new SineFunction(0.8), normalCar);
                lanes[4] = new Lane(yPos[4], -1, 1, 10, parent, new ConstantFunction(30), normalCar);
                lanes[5] = new Lane(yPos[5], 1, 1, 10, parent, new SineFunction(0.6), normalCar);
                lanes[6] = new Lane(yPos[6], 1, 2, 10, parent, new ConstantFunction(40), normalCar);
                lanes[7] = new Lane(yPos[7], 1, 3, 10, parent, new SineFunction(0.4), normalCar);
                lanes[8] = new Lane(yPos[8], 1, 4, 10, parent, new ConstantFunction(50), normalCar);
                lanes[9] = new Lane(yPos[9], 1, 5, 10, parent, new SineFunction(0.2), normalCar);
                break;
            }
            case(7): { // ALL TRUCKS
                // Some functions are nested inside another, making an acceleration/deacceleration effect
                lanes[0] = new Lane(yPos[0], -1, 1, 0, parent, new SineFunction(new RandomFunction()), normalTruck);
                lanes[1] = new Lane(yPos[1], -1, 1, 0, parent, new RandomFunction(), colorfulTruck);
                lanes[2] = new Lane(yPos[2], -1, 1, 0, parent, new ConstantFunction(40), normalTruck);
                lanes[3] = new Lane(yPos[3], -1, 1, 0, parent, new RandomFunction(), colorfulTruck);
                lanes[4] = new Lane(yPos[4], -1, 1, 0, parent, new SineFunction(new SineFunction(2)), normalTruck);
                lanes[5] = new Lane(yPos[5], 1, 1, 0, parent, new RandomFunction(), normalTruck);
                lanes[6] = new Lane(yPos[6], 1, 1, 0, parent, new ConstantFunction(80), blinkingTruck);
                lanes[7] = new Lane(yPos[7], 1, 1, 0, parent, new RandomFunction(), colorfulTruck);
                lanes[8] = new Lane(yPos[8], 1, 1, 0, parent, new SineFunction(0.3), normalTruck);
                lanes[9] = new Lane(yPos[9], 1, 1, 0, parent, new RandomFunction(), blinkingTruck);
                break;
            }
            case(8): { // ALL RANDOM!
                // Cars are random, distance between cars are random, quantity of cars per lane are random, speed of cars is random
                Car[] randomCars = new Car[6];
                randomCars[0] = normalCar;
                randomCars[1] = colorfulCar;
                randomCars[2] = blinkingCar;
                randomCars[3] = normalTruck;
                randomCars[4] = colorfulTruck;
                randomCars[5] = blinkingTruck;
                Random rand = new Random();
                lanes[0] = new Lane(yPos[0], -1, rand.nextInt(2) + 1, rand.nextInt(100) + 20, parent, new RandomFunction(), randomCars[rand.nextInt(6)]);
                lanes[1] = new Lane(yPos[1], -1, rand.nextInt(2) + 1, rand.nextInt(100) + 20, parent, new RandomFunction(), randomCars[rand.nextInt(6)]);
                lanes[2] = new Lane(yPos[2], -1, rand.nextInt(2) + 1, rand.nextInt(100) + 20, parent, new RandomFunction(), randomCars[rand.nextInt(6)]);
                lanes[3] = new Lane(yPos[3], -1, rand.nextInt(3) + 1, rand.nextInt(100) + 20, parent, new RandomFunction(), randomCars[rand.nextInt(6)]);
                lanes[4] = new Lane(yPos[4], -1, rand.nextInt(3) + 1, rand.nextInt(100) + 20, parent, new RandomFunction(), randomCars[rand.nextInt(6)]);
                lanes[5] = new Lane(yPos[5], 1, rand.nextInt(3) + 1, rand.nextInt(100) + 20, parent, new RandomFunction(), randomCars[rand.nextInt(6)]);
                lanes[6] = new Lane(yPos[6], 1, rand.nextInt(3) + 1, rand.nextInt(100) + 20, parent, new RandomFunction(), randomCars[rand.nextInt(6)]);
                lanes[7] = new Lane(yPos[7], 1, rand.nextInt(2) + 1, rand.nextInt(100) + 20, parent, new RandomFunction(), randomCars[rand.nextInt(6)]);
                lanes[8] = new Lane(yPos[8], 1, rand.nextInt(2) + 1, rand.nextInt(100) + 20, parent, new RandomFunction(), randomCars[rand.nextInt(6)]);
                lanes[9] = new Lane(yPos[9], 1, rand.nextInt(2) + 1, rand.nextInt(100) + 20, parent, new RandomFunction(), randomCars[rand.nextInt(6)]);
                break;
            }
        }
        for(Lane l : lanes) {
            l.animate(0);
        }

        return lanes;
    }
}
