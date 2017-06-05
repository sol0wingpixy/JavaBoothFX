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

/**
 * This is the central converter from the user's code to JavaFX language.
 */
public class Game {
    private double width;
    private double height;
    private List<Sprite> sprites; //List of non-animated Sprites
    private List<AnimatedSprite> animatedSprites; //List of animated Sprites
    private List<Sprite> allSprites; //List of all Sprites - animated or not
    private Group spriteGroup; //Group of non-animated Sprites - used to dynamically add and remove
    private Group animatedGroup; //Group of animated Sprites - used to change images, dynamically add and remove
    private KeyStates states; //Map of all KeyCodes and whether or not they are pressed - updated by Game
    private ViewManager camera; //Manages the offset values to simulate moving camera
    private boolean launched = false;

    /**
     * Creates a new instance of a Game object -   initializes the Sprite lists, KeyStates, and camera.
     * @param width Width of Game frame
     * @param height Height of Game frame
     */
    public Game(double width,double height)
    {
        //instantiation
        sprites = new ArrayList<>();
        animatedSprites = new ArrayList<>();
        allSprites = new ArrayList<>();
        this.width = width;
        this.height = height;
        states = new KeyStates();
        camera = new ViewManager(0,0);
    }

    /**
     * Default game - width and height 500
     */
    public Game() {
        this(500,500);
    }

    /**
     * Will add the sent Sprite to the game. Will be split by Animated and Static sprites.
     * Also adds the Sprite's node to the Window.
     * @param sprite Sprite to be added.
     */
    public void addSprite(Sprite sprite) {
        //if an animatedSprite, add to that list, else to regular list
        if(sprite instanceof AnimatedSprite) {
            animatedSprites.add((AnimatedSprite)sprite);
        }
        else {
            sprites.add(sprite);
        }
        allSprites.add(sprite);//regardless, add to allsprites
        sprite.setParentGame(this);
        if(launched)
        {//if the game is already launched, we need o manually add it to the Groups to appear.
            if(sprite instanceof AnimatedSprite) {
                animatedGroup.getChildren().add(sprite.getNode());
            }
            else {
                spriteGroup.getChildren().add(sprite.getNode());
            }
        }//otherwise, it will be defaulted into the groups
    }

    /**
     * Will remove the stated sprite from the game - if fails, will throw NoSuchSpriteException
     * @param sprite Reference to Sprite to be removed.
     */
    public void removeSprite(Sprite sprite)
    {
        try {
            if (sprite instanceof AnimatedSprite) {
                if (!animatedSprites.remove(sprite))//List.remove() returns true if successful, false if fails - if fails, throw exception
                    throw new NoSuchSpriteException("Trying to remove sprite that is not there.");
            } else {
                if (!sprites.remove(sprite))
                    throw new NoSuchSpriteException("Trying to remove sprite that is not there.");
            }
            if (!allSprites.remove(sprite))
                throw new NoSuchSpriteException("Trying to remove sprite that is not there.");

            if(launched)
            {//if launched, need to remove node reference for non-animated Sprite - animated Sprites are already checked every tick
                if(!(sprite instanceof AnimatedSprite))
                    if(!spriteGroup.getChildren().remove(sprite.getNode()))
                        throw new NoSuchSpriteException("Trying to remove sprite that is not there.");
            }
        }
        catch (NoSuchSpriteException e)
        {
            e.printStackTrace();//if trying to remove Sprite not present
        }
    }

    /**
     * Will add sent Sprites to the game using addSprite method
     * @param sprites The Sprites to be added
     */
    public void addSprites(Sprite... sprites) {
        for(Sprite sprite : sprites){
            addSprite(sprite);//simply traverse through list, call addSprite()
        }
    }

    /**
     * Will add sent Sprites to the game using addSprite method
     * @param sprites THe Sprites to be added
     */
    public void addSprites(Collection<Sprite> sprites){
        for(Sprite sprite : sprites){
            addSprite(sprite);//simply traverse through list, call addSprite()
        }
    }

    /**
     * Returns the width of the game
     * @return Width of the game
     */
    public double getWidth() {
        return width;
    }

    /**
     * Sets the width of the game to sent value
     * @param width New width of game
     */
    public void setWidth(double width) {
        this.width = width;
    }

    /**
     * Returns the height of the game
     * @return Height of the game
     */
    public double getHeight() {
        return height;
    }

    /**
     * Sets the height of the game to sent value
     * @param height New height of the game
     */
    public void setHeight(double height) {
        this.height = height;
    }

    /**
     * Returns the KeyStates used to keep track of key depressions
     * @return The KeyStates object
     */
    public KeyStates getStates(){
        return states;
    }

    /**
     * Returns all the sprites currently in the game.
     * @return The allSprites list
     */
    public List<Sprite> getAllSprites() {return allSprites;}

    /**
     * Moves the 'camera' by the sent values - actually changes the offset of all Sprites
     * @param x Distance scrolled in X-axis
     * @param y Distance scrolled in Y-axis
     */
    public void scroll(double x,double y) {
        camera.move(x,y);
    }

    /**
     * Moves the 'camera' by the sent value - actually changes the offset of all Sprites
     * @param xOffset Distance scrolled in X-axis
     */
    public void scrollX(double xOffset){
        camera.moveX(xOffset);
    }

    /**
     * Moves the 'camera' by the sent value - actually changes the offset of all Sprites
     * @param yOffset Distance scrolled in Y-axis
     */
    public void scrollY(double yOffset){
        camera.moveY(yOffset);
    }

    /**
     * Moves the camera to the given values - top-left corner.
     * @param xCo X-coordinate of the left edge of the view
     * @param yCo Y-coordinate of the top edge of the view
     */
    public void scrollTo(double xCo,double yCo) {
        camera.offsetTo(xCo,yCo);
    }
    /**
     * Moves the camera to the given values - centered.
     * @param xCo X-coordinate of the center of the view
     * @param yCo Y-coordinate of the center of the view
     */
    public void centerOn(double xCo,double yCo){
        camera.offsetTo(xCo+width/2,yCo+height/2);
    }

    /**
     * Moves the camera to center on the given Sprite - a bit jerky
     * @param object The object to center upon.
     */
    public void centerOn(Sprite object){
        Node centerTo = object.getNode();//I honestly don't know why this works. It just does.
        centerOn(-(centerTo.getLayoutX()-object.getOffsetX()),-(centerTo.getLayoutY()-object.getOffsetY()));
    }

    /**
     * Should be the final call from the user's code - will create all begin the game, draw sprites.
     */
    public void startGame() {
        Window.setGame(this);//give the window a reference to this
        Application.launch(Window.class);//then start the application - will call makeScene
        launched = true;
    }

    //makeScene starts the thread processes, assigns sprites to be drawn
    void makeScene(Group root,Group animatedGroup, Stage stage) {
        //add all Sprite nodes to root to draw
        for (Sprite sprite : sprites) {
            root.getChildren().add(sprite.getNode());//add all regular Sprite nodes to that group
        }
        for(Sprite sprite : animatedSprites) {
            animatedGroup.getChildren().add(sprite.getNode());//add all animated Sprite nodes to that group
        }
        this.animatedGroup = animatedGroup; //set animatedGroup reference
        root.getChildren().add(animatedGroup); //put animategGroup in default Sprite group - to draw every frame
        spriteGroup = root; //set spriteGroup reference

        stage.setWidth(getWidth());
        stage.setHeight(getHeight());

        startAnimation();//see startAnimation
        startListening(stage);//see startListening
        launched = true;
    }

    //starts the animation timer that calls the Sprite runPerTick, checks collision
    private void startAnimation() {
        new AnimationTimer() {//run at 60 fps
            @Override
            public void handle(long now) {
                List<Sprite> spritesClone = new ArrayList<>(sprites);
                List<Sprite> animatedSpritesClone = new ArrayList<>(animatedSprites);
                //clones each list to avoid dynamic deletion errors - any deletions won't take effect in current tick

                ObservableList<Node> toAnimate = animatedGroup.getChildren(); //get the list of animatedSprit nodes
                toAnimate.remove(0,toAnimate.size());//remove all nodes
                for(int i=0;i<animatedSprites.size();i++) {
                    toAnimate.add(animatedSprites.get(i).nextFrame());//add each animatedSprites' next frame
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
                //clone list to avoid dynamic deletion errors
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
        }.start();
    }

    private void startListening(Stage stage) {//will handle keys being pressed
        stage.addEventHandler(KeyEvent.KEY_PRESSED,new EventHandler<KeyEvent>() {//whenever a key is pressed down
            public void handle(KeyEvent keyEvent) {
                for (int i=0;i<sprites.size();i++) {
                    sprites.get(i).whenKeyPressed(keyEvent);//have each sprite react
                }
                states.keyPressed(keyEvent.getCode());//have KeyStates remember that the key is pressed
            }
        });

        stage.addEventHandler(KeyEvent.KEY_RELEASED, new EventHandler<KeyEvent>() {//whenever a key is released
            public void handle(KeyEvent keyEvent) {
                states.keyReleased(keyEvent.getCode());//have KeyStates remember that key is released
            }
        });
    }
}