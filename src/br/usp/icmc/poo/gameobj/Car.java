package br.usp.icmc.poo.gameobj;

import br.usp.icmc.poo.funcs.ConstantFunction;
import br.usp.icmc.poo.funcs.Function;
import br.usp.icmc.poo.utils.GamePrefs;
import br.usp.icmc.poo.utils.Textures;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import static java.lang.Math.abs;
import static java.lang.Math.min;
import static java.lang.Math.random;

// This class determines the vehicles
public class Car extends Actor {
    Rectangle colorOverlay; //
    Color color;
    String type; // carro ou caminhao

    Group composition; // carroceria + rodinhas

    int direction; // 1 -> right, -1 -> left
    private int partialX; // guarda a parte decimal do movimento

    public int getSpeedX() {
        return speedX;
    }

    private int speedX; // velocidade horizontal

    Function speedFunction; // funcao da velocidade do carro

    // simply returns a copy of the instance of the car
    public Car copy(){
        return new Car(this.direction, this.x, this.y, this.speedFunction, this.color, this.type);
    }

    // Initializes the textures, speed, direction, color and speed fucntion
    void setup(int direction, int x, int y, Function speedFunction, Color color)
    {
        this.direction = direction;
        this.x = x;
        this.y = y;
        this.speedFunction = speedFunction;
        this.color = color;
        Image temp;

        Image tex0;
        Image tex1;
        if(type.equals("car")) {
            temp = new Image(Textures.CAR);
            tex0 = new Image(Textures.CAR, temp.getWidth() * GamePrefs.RESIZE, 0, true, false);
            tex1 = new Image(Textures.CARWHEELS, temp.getWidth() * GamePrefs.RESIZE, 0, true, false);
        }
        else { // truck
            temp = new Image(Textures.TRUCK);
            tex0 = new Image(Textures.TRUCK, temp.getWidth() * GamePrefs.RESIZE, 0, true, false);
            tex1 = new Image(Textures.TRUCKWHEELS, temp.getWidth() * GamePrefs.RESIZE, 0, true, false);
        }

        this.height = (int) temp.getHeight();
        this.width = (int) temp.getWidth();

        sprite = new ImageView(tex1);  // sprite é so a rodinha

        colorOverlay = new Rectangle(width * GamePrefs.RESIZE, height * GamePrefs.RESIZE, color); // cria retangulo colorido
        colorOverlay.setClip(new ImageView(tex0)); // recorta o retangulo colorido na imagem do carro/caminhao

        composition = new Group(colorOverlay, sprite); // combina retangulo (carroceria) com rodinhas
        partialX = 0;

        if(direction == -1) {
            this.composition.setRotate(180);
        }
    }

    Car() {
        this.type = "car";
        setup(1, 0, 0, new ConstantFunction(0), new Color(random(), random(), random(), 1));
    }

    // Constructor able to choose between car/truck
    // pra pegar largura do carro/caminhao pra posicionamento inicial
    public Car(String type) {
        this.type = type;
        setup(1, 0, 0, new ConstantFunction(0), new Color(random(), random(), random(), 1));
    }

    // Construtor REAL
    private Car(int direction, int x, int y, Function speedFunction, Color color, String type)
    {
        this.type = type;
        setup(direction, x, y, speedFunction, color);
    }

    // Moves the car to a new position if it has traveled enough
    @Override
    public void animate(long frame){
        speedX = min(speedFunction.evaluate(frame), GamePrefs.MAXCARSPEED); // calcula velocidade do carro
        partialX += speedX * direction;

        int movementThreshold = 30;
        if(abs(partialX) >= movementThreshold)
        {
            x += partialX / movementThreshold;
        }
        partialX = partialX % movementThreshold;

        composition.relocate(x * GamePrefs.RESIZE, y * GamePrefs.RESIZE);
    }

    // Moves the car to a new x,y position
    @Override
    public void relocate(int x, int y){
        this.x = x;
        this.y = y;
    }

    // Adds this actor to a group (if the group is on a scene, it will be drawn)
    @Override
    public void draw(Group g){
        g.getChildren().add(this.composition);
    }

    // Remove this actor from a group (if it was on scene, it is erased)
    @Override
    public void erase(Group g) { g.getChildren().remove(this.composition); }
}
