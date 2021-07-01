package br.usp.icmc.poo.gameobj;

import br.usp.icmc.poo.funcs.Function;
import br.usp.icmc.poo.funcs.ImitateFunction;
import br.usp.icmc.poo.utils.GamePrefs;
import javafx.scene.Group;
import javafx.scene.paint.Color;

import static java.lang.Math.random;

public class Lane {
    private Car[] groupA;
    private Car[] groupB;
    private int direction;
    private int y;
    private int quantity = 2;
    private Group father;

    public Car[] getCars(){ // retorna os carros pra checar colisoes
        Car[] cars = new Car[quantity * 2];
        for(int i = 0; i < quantity; i ++)
        {
            cars[2 * i] = groupA[i];
            cars[2 * i + 1] = groupB[i];
        }
        return cars;
    }

    public Lane(int y, int direction, int quantity, int distance, Group father, Function speedFunction, Car firstCar) {
        int x;

        this.direction = direction;
        this.quantity = quantity;
        this.father = father;

        distance += firstCar.width;


        if(direction == 1)
        {
            x = 0;
        }
        else
        {
            x = GamePrefs.SCREEN_WIDTH - firstCar.width;
        }
        this.y = y;
        groupA = new Car[quantity];
        groupB = new Car[quantity];

        Color c;
        for(int i = 0; i < quantity; i++)
        {
            c = new Color(random(), random(), random(), 1);
            if(direction == 1) {
                groupA[i] = firstCar.copy();
                if(i == 0) {
                    // cria o carro 0, CHEFE
                    groupA[i].setup(direction, x, this.y, speedFunction, c);
                }
                else {
                    // cria novos carros no grupo A imitando o carro 0
                    groupA[i].setup(direction, x + distance * i, this.y, new ImitateFunction(groupA[0]), c);
                }
                // grupo B sao carros que imitam os respectivos no grupo A
                groupB[i] = firstCar.copy();
                groupB[i].setup(direction, x - GamePrefs.SCREEN_WIDTH + distance * i, this.y, new ImitateFunction(groupA[0]), c);
            }
            else {
                groupA[i] = firstCar.copy();
                if(i == 0) {
                    groupA[i].setup(direction, x, this.y, speedFunction, c);
                }
                else {
                    groupA[i].setup(direction, x - distance * i, this.y, new ImitateFunction(groupA[0]), c);
                }
                groupB[i] = firstCar.copy();
                groupB[i].setup(direction, -firstCar.width - distance * i, this.y, new ImitateFunction(groupA[0]), c);
            }

            // adiciona os carros na tela
            groupA[i].draw(father);
            groupB[i].draw(father);
        }
    }

    public void animate(long frame) {

        // animation with horizontal loop
        for(int i = 0; i < quantity; i++)
        {
            // move os carros e teleporta pra o começo da tela de volta
            if(direction == 1) {
                groupA[i].animate(frame);
                groupB[i].animate(frame);
                if (groupA[i].x + groupA[i].width >= GamePrefs.SCREEN_WIDTH) {
                    groupB[i].relocate(groupA[i].x - GamePrefs.SCREEN_WIDTH, this.y);
                }
                if (groupB[i].x + groupB[i].width >= GamePrefs.SCREEN_WIDTH) {
                    groupA[i].relocate(groupB[i].x - GamePrefs.SCREEN_WIDTH, this.y);
                }
            }
            else
            {
                groupA[i].animate(frame);
                groupB[i].animate(frame);
                if (groupA[i].x <= 0) {
                    groupB[i].relocate(groupA[i].x + GamePrefs.SCREEN_WIDTH, this.y);
                }
                if (groupB[i].x <= 0) {
                    groupA[i].relocate(groupB[i].x + GamePrefs.SCREEN_WIDTH, this.y);
                }
            }
        }
    }

    public void erase() {
        for(int i = 0; i < quantity; i++) {
            groupA[i].erase(father);
            groupB[i].erase(father);
        }
    }
}
