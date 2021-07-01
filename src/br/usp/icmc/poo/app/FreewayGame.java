package br.usp.icmc.poo.app;

import br.usp.icmc.poo.utils.*;
import br.usp.icmc.poo.gameobj.*;
import javafx.application.Application; // executavel
import javafx.scene.image.Image; // imagem
import javafx.event.EventHandler; //
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.concurrent.Service; // thread no javafx
import javafx.concurrent.Task;


/*
    This br.usp.icmc.poo.app is inspired by the following videogame: Freeway by Activision (1981)

    Known Rules:
    ->  Round duration: 2 minutes 16 seconds
    ->  1 or 2 Players (always 2 chickens on screen)
    ->  Chickens can only run up and down
    ->  10 lanes:
        -> Cars travel from left to right on the 5 bottom-most lanes
        -> Cars travel from right to left on the 5 top-most lanes
    -> 8 different playable levels
        -> Each one with different car setups (cars / trucks)
*/

public class FreewayGame extends Application {
    private Group root; // Root group for the javafx application
    private Service<Long> gameClock; // Clock tool for the gameloop
    private Level level; // Lane setter and getter
    private int levelID; // ID of the level (1 to 8)
    private Lane[] lanes; // Array of the game lanes
    private Chicken player0; // Pawn for player 0
    private Chicken player1; // Pawn for player 1
    private ScoreDisplay player0score; // Displays player0's score
    private ScoreDisplay player1score; // Displays player1's score

    private long startTime; // Time when the game starts
    private boolean endGame; // Is the game over?
    private long endGameTime; // Time when the g1
    // ame ends

    // Display the main screen (freeway and its lanes and the HUD limits)
    private void displayBackground()
    {
        Image tmp = new Image(Textures.FREEWAY);
        Image background = new Image(Textures.FREEWAY, tmp.getWidth() * GamePrefs.RESIZE, 0, true, false);
        ImageView backgroundiv = new ImageView(background);
        root.getChildren().add(backgroundiv);

        tmp = new Image(Textures.OVERLAY);
        Image overlay = new Image(Textures.OVERLAY, tmp.getWidth() * GamePrefs.RESIZE, 0, true, false);
        ImageView overlayiv = new ImageView(overlay);
        root.getChildren().add(overlayiv);
    }

    // Before the game starts, the players are able to choose a level to play (1 to 8)
    // Enables input (UP/DOWN, ENTER) to choose the level while showing the current level ID
    private void levelPicker()
    {
        ScoreDisplay levelIDDisplay = new ScoreDisplay(GamePrefs.P0SCOREX, 2, root); // mostra numero da fase
        levelIDDisplay.setScore(1); // fase inicial = 1
        levelID = 1;
        lanes = level.getLanes(levelID, root); // pega pistas de acordo com a fase

        root.requestFocus(); // pede o foco para pegar input do teclado
        root.setFocusTraversable(false); // nao pode trocar o foco

        root.addEventHandler(KeyEvent.KEY_PRESSED, new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if((event.getCode() == GamePrefs.UP[0]) || (event.getCode() == GamePrefs.UP[1])) {
                    if (levelID >= 8) {
                        levelID = 1;
                    } else {
                        levelID++;
                    }
                    levelIDDisplay.setScore(levelID); // mostra numero da fase
                    lanes = level.getLanes(levelID, root); // mostra pistas da fase
                }
                else if((event.getCode() == GamePrefs.DOWN[0]) || (event.getCode() == GamePrefs.DOWN[1])) {
                    if (levelID <= 1) {
                        levelID = 8;
                    } else {
                        levelID--;
                    }
                    levelIDDisplay.setScore(levelID);
                    lanes = level.getLanes(levelID, root);
                }
                else if((event.getCode() == GamePrefs.START[0]) || (event.getCode() == GamePrefs.START[1])) {
                    levelIDDisplay.erase(); // apaga numero da fase
                    root.removeEventHandler(KeyEvent.KEY_PRESSED, this); // remove este gerenciador de eventos
                    play();
                }
            }
        });
    }

    // Game starter function
    // Adds a listener to the clock ticks
    // Creates the players' pawns
    private void play() {
        lanes = level.getLanes(levelID, root); // cria a pista da fase escolhida

        startTime = System.currentTimeMillis(); // moment when the clock started
        endGameTime = startTime + GamePrefs.TIMELIMIT;

        // cria um listener pro relogio
        gameClock.stateProperty().addListener((observable, oldValue, newValue) -> {
            switch (newValue)
            {
                case SUCCEEDED: {
                    update();
                }
            }
        });

        player0 = new Chicken(GamePrefs.P0STARTINGX, GamePrefs.STARTY, 0);
        player0.draw(root);

        player1 = new Chicken(GamePrefs.P1STARTINGX, GamePrefs.STARTY, 1);
        player1.draw(root);

        player0score = new ScoreDisplay(GamePrefs.P0SCOREX, 2, root);
        player1score = new ScoreDisplay(GamePrefs.P1SCOREX, 2, root);

        gameClock.reset();
        gameClock.start();
    }

    // Checks collisions between players and vehicles
    private boolean checkCollision(Actor a, Actor b) {
        boolean collided;

        if(a.getX() > b.getX())
            collided = (b.getX() + b.getWidth() >= a.getX());
        else
            collided = (a.getX() + a.getWidth() >= b.getX());
        if(collided) {
            if (a.getY() > b.getY())
                return (b.getY() + b.getHeight() >= a.getY());
            else
                return (a.getY() + a.getHeight() >= b.getY());
        }
        else return false;
    }

    // Checks if a player has reached the top of the screen
    private boolean checkVictory(Chicken c)
    {
        return c.getY() == GamePrefs.FINALY;
    }

    // Gameloop: does everything needed every tick
    // Calls for check collisions, animates the actors, checks victory conditions, and game over conditions
    private void update(){
        for(Lane l : lanes)
        {
            l.animate(gameClock.getValue());
            Car[] cars = l.getCars();
            for(Car c : cars)
            {
                // checa colisoes
                if(checkCollision(c, player0)){
                    player0.relocate(GamePrefs.P0STARTINGX, GamePrefs.STARTY);
                }
                if(checkCollision(c, player1)){
                    player1.relocate(GamePrefs.P1STARTINGX, GamePrefs.STARTY);
                }
            }
        }
        player0.animate(gameClock.getValue());
        player1.animate(gameClock.getValue());
        if(checkVictory(player0))
        {
            player0score.score();
            player0.relocate(GamePrefs.P0STARTINGX, GamePrefs.STARTY);
        }
        if(checkVictory(player1))
        {
            player1score.score();
            player1.relocate(GamePrefs.P1STARTINGX, GamePrefs.STARTY);
        }
        if(endGame){
            System.out.println("Game over!");
            if(player0score.getScore() > player1score.getScore()) {
                System.out.println("Player 1 won!");
            }
            else if(player0score.getScore() < player1score.getScore()) {
                System.out.println("Player 2 won!");
            }
            else {
                System.out.println("Its a draw!");
            }
        }
        else
        {
            // recomeça o relogio
            gameClock.reset();
            gameClock.start();
        }
    }

    // Main application method:
    // Creates the window and the Game Clock
    @Override
    public void start(Stage gameWindow) {
        root = new Group();

        Scene scene = new Scene(root, GamePrefs.SCREEN_WIDTH * GamePrefs.RESIZE, GamePrefs.SCREEN_HEIGHT * GamePrefs.RESIZE, Color.GRAY);

        gameWindow.setTitle("Freeway");
        gameWindow.setScene(scene);
        gameWindow.setWidth(scene.getWidth() + 6); // valor de reajuste
        gameWindow.setHeight(scene.getHeight() + 29);
        gameWindow.setResizable(false);
        gameWindow.show();

        // jeito do javafx para o codigo interagir com a interface grafica
        // n tem muito como saber como funciona
        gameClock = new Service<Long>() {
            private long frame = 0;
            private long delay;

            @Override
            protected Task<Long> createTask() {
                return new Task<Long>() {
                    @Override
                    protected Long call() throws Exception {
                        delay = startTime + (frame * 1000 / GamePrefs.FRAMERATE) - System.currentTimeMillis();
                        if(delay > 0) {
                            Thread.sleep(delay);
                        }
                        frame++;
                        if (System.currentTimeMillis() >= endGameTime) {
                            endGame = true;
                        }
                        return frame;
                    }
                };
            }
        };

        displayBackground(); // mostra pista
        lanes = new Lane[10]; // cria pistinhas
        level = new Level();
        endGame = false;

        levelPicker();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
