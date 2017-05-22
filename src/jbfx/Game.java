package jbfx;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

//This is the central class for the actual execution of the Game
public class Game {
    private double width;
    private double height;
    private List<Sprite> sprites;
    private List<AnimatedSprite> animatedSprites;
    private Group animatedGroup;
    private AnimationTimer animationTimer;
    private EventHandler pressedHandler;
    private EventHandler releasedHandler;
    private KeyStates states;
    private ViewManager camera;
    GameInfo gameInfo;
    private boolean launched = false;

    public Game(double width,double height)
    {
        sprites = new ArrayList<>();
        animatedSprites = new ArrayList<>();
        this.width = width;
        this.height = height;
        states = new KeyStates();
        camera = new ViewManager(states,0,0);
        gameInfo = new GameInfo(this);
    }

    public Game() {
        this(500,500);
    }

    public void addSprite(Sprite sprite) {
            if(sprite instanceof AnimatedSprite) {
                animatedSprites.add((AnimatedSprite)sprite);
            }
            else {
                sprites.add(sprite);
            }
            sprite.setParentGame(this);
    }

    public void addSprites(Sprite... sprites) {
        for(Sprite sprite : sprites){
            addSprite(sprite);
        }
    }

    public void addSprites(Collection<Sprite> sprites){
        for(Sprite sprite : sprites){
            addSprite(sprite);
        }
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

    public void scroll(double x,double y) {
        camera.move(x,y);
    }

    public void scrollX(double xOffset){
        camera.moveX(xOffset);
    }

    public void scrollY(double yOffset){
        camera.moveY(yOffset);
    }

    public void scrollTo(double xCo,double yCo) {
        camera.offsetTo(xCo,yCo);
    }

    public void centerOn(double xCo,double yCo){
        camera.offsetTo(xCo+width/2,yCo+height/2);
    }

    public void centerOn(Sprite object){
        Node centerTo = object.getNode();
        centerOn(-(centerTo.getLayoutX()-object.getOffsetX()),-(centerTo.getLayoutY()-object.getOffsetY()));
    }

    //startGame is last method call from user's Main - starts game running.
    public void startGame() {
        Window.setGame(this);//give the window a reference to this
        Application.launch(Window.class);//then start the application
        launched = true;
    }

    //makeScene starts the thread processes, assigns sprites to be drawn
    public void makeScene(Group root,Group animatedGroup, Stage stage) {
        //add all Sprite nodes to root to draw
        for (Sprite sprite : sprites) {
            root.getChildren().add(sprite.getNode());
        }
        for(Sprite sprite : animatedSprites) {
            animatedGroup.getChildren().add(sprite.getNode());
        }
        this.animatedGroup = animatedGroup;
        root.getChildren().add(animatedGroup);
        gameInfo.update(this);
        root.getChildren().add(gameInfo.getNode());
        //obvious
        stage.setWidth(getWidth());
        stage.setHeight(getHeight());

        startAnimation();//see startAnimation
        startListening(stage);//see startListening
    }

    //starts the animation timer that calls the Sprite runPerTick, checks collision
    public void startAnimation() {
        animationTimer = new AnimationTimer() {//run at 60 fps
            @Override
            public void handle(long now) {
                ObservableList<Node> toAnimate = animatedGroup.getChildren();
                for(int i=0;i<toAnimate.size();i++) {
                    toAnimate.set(i,animatedSprites.get(i).nextFrame());
                }
                for (Sprite sprite:sprites) {//go through all sprites, tick them
                    sprite.setOffset(camera.getOffsetX(), camera.getOffsetY());
                    sprite.runPerTick(now);//whatever user defines
                }
                for(Sprite sprite:animatedSprites) {
                    sprite.setOffset(camera.getOffsetX(), camera.getOffsetY());
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
        Game game = this;
        new AnimationTimer(){
            public void handle(long now)
            {
                if(states.isKeyPressed(KeyCode.I)) {
                    camera.moveY(3);
                }
                if(states.isKeyPressed(KeyCode.J)) {
                    camera.moveX(3);
                }
                if(states.isKeyPressed(KeyCode.K)) {
                    camera.moveY(-3);
                }
                if(states.isKeyPressed(KeyCode.L)) {
                    camera.moveX(-3);
                }
                gameInfo.update(game);
            }
        }.start();
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
}