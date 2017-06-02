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
import java.util.ConcurrentModificationException;
import java.util.List;

/**
 * This is the central converter from the user's code to JavaFX language.
 */
public class Game {
    private double width;
    private double height;
    private List<Sprite> sprites;
    private List<AnimatedSprite> animatedSprites;
    private List<Sprite> allSprites;
    private Group spriteGroup;
    private Group animatedGroup;
    private AnimationTimer animationTimer;
    private EventHandler pressedHandler;
    private EventHandler releasedHandler;
    private KeyStates states;
    private ViewManager camera;
    private Window window;
    private boolean launched = false;

    /**
     * Creates a new instance of a Game object -   initializes the Sprite lists, KeyStates, and camera.
     * @param width Width of Game frame
     * @param height Height of Game frame
     */
    public Game(double width,double height)
    {
        sprites = new ArrayList<>();
        animatedSprites = new ArrayList<>();
        allSprites = new ArrayList<>();
        this.width = width;
        this.height = height;
        states = new KeyStates();
        camera = new ViewManager(states,0,0);
    }

    /**
     * Default game - width and height 500
     */
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
        allSprites.add(sprite);
        sprite.setParentGame(this);
        if(launched)
        {
            //window.update();
            if(sprite instanceof AnimatedSprite) {
                animatedGroup.getChildren().add(sprite.getNode());
            }
            else {
                spriteGroup.getChildren().add(sprite.getNode());
            }
        }
    }
    public void removeSprite(Sprite sprite)
    {
        try {
            if (sprite instanceof AnimatedSprite) {
                if (!animatedSprites.remove(sprite))
                    throw new NoSuchSpriteException("Trying to remove sprite that is not there.");
            } else {
                if (!sprites.remove(sprite))
                    throw new NoSuchSpriteException("Trying to remove sprite that is not there.");
            }
            if (!allSprites.remove(sprite))
                throw new NoSuchSpriteException("Trying to remove sprite that is not there.");

            if(launched)
            {
                if(!(sprite instanceof AnimatedSprite))
                    if(!spriteGroup.getChildren().remove(sprite.getNode()))
                        throw new NoSuchSpriteException("Trying to remove sprite that is not there.");
            }
        }
        catch (NoSuchSpriteException e)
        {
            e.printStackTrace();
        }
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

    void setWindow(Window window)
    {
        this.window = window;
    }

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public double getHeight() {
        return height;
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

    public List<Sprite> getAllSprites() {return allSprites;}

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
    void makeScene(Group root,Group animatedGroup, Stage stage) {
        //add all Sprite nodes to root to draw
        for (Sprite sprite : sprites) {
            root.getChildren().add(sprite.getNode());
        }
        for(Sprite sprite : animatedSprites) {
            animatedGroup.getChildren().add(sprite.getNode());
        }
        this.animatedGroup = animatedGroup;
        root.getChildren().add(animatedGroup);
        spriteGroup = root;
        //obvious
        stage.setWidth(getWidth());
        stage.setHeight(getHeight());

        startAnimation();//see startAnimation
        startListening(stage);//see startListening
        launched = true;
    }

    //starts the animation timer that calls the Sprite runPerTick, checks collision
    private void startAnimation() {
        animationTimer = new AnimationTimer() {//run at 60 fps
            @Override
            public void handle(long now) {
                List<Sprite> spritesClone = new ArrayList<>(sprites);
                List<Sprite> animatedSpritesClone = new ArrayList<>(animatedSprites);
                ObservableList<Node> toAnimate = animatedGroup.getChildren();
                toAnimate.remove(0,toAnimate.size());
                for(int i=0;i<animatedSprites.size();i++) {
                    toAnimate.add(animatedSprites.get(i).nextFrame());
                }
                for (Sprite sprite:spritesClone) {//go through all sprites, tick them
                    sprite.setOffset(camera.getOffsetX(), camera.getOffsetY());
                    sprite.runPerTick(now);//whatever user defines
                }
                for (Sprite sprite : animatedSpritesClone) {
                        sprite.setOffset(camera.getOffsetX(), camera.getOffsetY());
                        sprite.runPerTick(now);//whatever user defines
                }
                List<Sprite> allSpritesClone = new ArrayList<>(allSprites);
                //Could set up another AnimationTimer to check collision - should?
                for(int i=0;i<allSpritesClone.size();i++)//every sprite
                {
                    for(int j = i+1;j<allSpritesClone.size();j++)//every sprite that hasn't already been checked against sprites[i] - efficency
                    {
                        if(allSpritesClone.get(i).getNode().getBoundsInParent().intersects(allSpritesClone.get(j).getNode().getBoundsInParent()))//check collision
                        {
                            allSpritesClone.get(i).collidesWith(allSpritesClone.get(j));//collide one with other
                            allSpritesClone.get(j).collidesWith(allSpritesClone.get(i));//collide other with one
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
            }
        }.start();
    }

    private void startListening(Stage stage) {//will handle keys being pressed
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