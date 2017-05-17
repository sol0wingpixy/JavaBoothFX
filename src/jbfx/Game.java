package jbfx;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

//This is the central class for the actual execution of the Game
public class Game {
    private double width;
    private double height;
    private List<Sprite> sprites;
    private AnimationTimer animationTimer;
    private AnimationTimer scrollingTimer;
    private EventHandler pressedHandler;
    private EventHandler releasedHandler;
    private KeyStates states;
    private ViewManager viewManager;
    private boolean launched = false;

    public Game(double width,double height)
    {
        sprites = new ArrayList<Sprite>();
        this.width = width;
        this.height = height;
        states = new KeyStates();
        viewManager = new ViewManager(states,0,0);
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

    public List<Sprite> getSprites() {
        return sprites;
    }

    //Everything above used to set up instance variables.
    //Below is actual work

    //startGame is last method call from user's Main - starts game running.
    public void startGame() {
        Window.setGame(this);//give the window a reference to this
        Application.launch(Window.class);//then start the application
        launched = true;
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
        startScrolling();
    }

    //starts the animation timer that calls the Sprite runPerTick, checks collision
    public void startAnimation() {
        animationTimer = new AnimationTimer() {//run at 60 fps
            @Override
            public void handle(long now) {
                for (Sprite sprite:sprites) {//go through all sprites, tick them
                    sprite.setOffset(viewManager.getOffsetX(),viewManager.getOffsetY());
                    sprite.runPerTick(now);//whatever user defines
                }
                //Could set up another AnimationTimer to check collision - should?
                for(int i=0;i<sprites.size();i++)//every sprite
                {
                    for(int j = i+1;j<sprites.size();j++)//every sprite that hasn't already been checked against sprites[i] - efficency
                    {
                        if(sprites.get(i).getNode().getBoundsInParent().intersects(sprites.get(j).getNode().getBoundsInParent()))//check collision
                        {
                            sprites.get(i).collidesWith(sprites.get(j));//collide one with other
                            sprites.get(j).collidesWith(sprites.get(i));//collide other with one
                        }
                    }
                }
            }
        };
        animationTimer.start();
    }

    public void startListening(Stage stage) {//will handle keys being pressed
        pressedHandler = new EventHandler<KeyEvent>() {//whenever a key is pressed down
            public void handle(KeyEvent keyEvent) {
                for (int i=0;i<sprites.size();i++) {
                    sprites.get(i).whenKeyPressed(keyEvent);//have each sprite react
                }
                states.keyPressed(keyEvent.getCode());//have KeyStates remember that the key is pressed
            }
        };
        stage.addEventHandler(KeyEvent.KEY_PRESSED,pressedHandler);

        releasedHandler = new EventHandler<KeyEvent>() {//whenever a key is released
            public void handle(KeyEvent keyEvent) {
                states.keyReleased(keyEvent.getCode());//have KeyStates remember that key is released
            }
        };

        stage.addEventHandler(KeyEvent.KEY_RELEASED,releasedHandler);
    }
    public void startScrolling()
    {
        scrollingTimer = new AnimationTimer() {//run at 60 fps
            @Override
            public void handle(long now) {
                viewManager.tick();
            }
        };
        scrollingTimer.start();
    }
}