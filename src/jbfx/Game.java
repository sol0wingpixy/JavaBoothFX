package jbfx;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.input.InputEvent;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

import java.util.ArrayList;
//This is the central class for the actual execution of the Game
public class Game {
    private double width;
    private double height;
    private ArrayList<Sprite> sprites;
    private KeyStates states;

    public Game(double width,double height)
    {
        sprites = new ArrayList<>();
        this.width = width;
        this.height = height;
        states = new KeyStates();
    }

    public Game() {
        this(500,500);
    }

    public void addSprite(Sprite sprite) {
        sprites.add(sprite);
        sprite.setParentGame(this);
    }

    public double getWidth() {
        return width;
    }

    public double getHeight() {
        return height;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public KeyStates getStates(){
        return states;
    }

    public ArrayList<Sprite> getSprites() {
        return sprites;
    }

    //Everything above used to set up instance variables.
    //Below is actual work

    //startGame is last method call from user's Main - starts game running.
    public void startGame() {
        Window.setGame(this);//give the window a reference to this
        Application.launch(Window.class);//then start the application
    }

    //makeScene starts the thread processes, assigns sprites to be drawn
    public void makeScene(Group root, Stage stage) {
        //add all Sprite nodes to root to draw
        for (Sprite sprite : getSprites()) {
            root.getChildren().add(sprite.getNode());
        }
        //obvious
        stage.setWidth(getWidth());
        stage.setHeight(getHeight());

        startAnimation();//see startAnimation
        startListening(stage);//see startListening
    }

    //starts the animation timer that calls the Sprite runPerTick, checks collision
    public void startAnimation() {
        new AnimationTimer() {
            @Override
            public void handle(long now) {
                for (Sprite sprite : sprites) {
                    sprite.runPerTick(now);//whatever use defines
                }
                //Could set up another AnimationTimer to check collision - should?
                for(int i=0;i<sprites.size();i++)//every sprite
                {
                    for(int j = i+1;j<sprites.size();j++)//every sprite that hasn't already been checked against sprites[i]
                    {
                        if(sprites.get(i).getNode().getBoundsInParent().intersects(sprites.get(j).getNode().getBoundsInParent()))//check collision
                        {
                            sprites.get(i).collidesWith(sprites.get(j));
                            sprites.get(j).collidesWith(sprites.get(i));
                        }
                    }
                }
            }
        }.start();
    }

    public void startListening(Stage stage) {
        stage.addEventHandler(KeyEvent.KEY_PRESSED,new EventHandler<KeyEvent>() {
            public void handle(KeyEvent keyEvent) {
                for (Sprite sprite : sprites) {
                    sprite.whenKeyPressed(keyEvent);
                }
                states.keyPressed(keyEvent.getCode());
            }
        });
        stage.addEventHandler(KeyEvent.KEY_RELEASED,new EventHandler<KeyEvent>() {
            public void handle(KeyEvent keyEvent) {
                states.keyReleased(keyEvent.getCode());
            }
        });
    }
}
