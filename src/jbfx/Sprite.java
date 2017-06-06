package jbfx;

import javafx.scene.Node;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.shape.Rectangle;
/**
*This is the basic Sprite class. Everything that appears on screen will directly extend either Sprite or AnimatedSprites.
* Each frame, the {@code runPerTick(long now)} method will be called.
* Whenever a key is pressed, the {@code whenKeyPressed(KeyEvent event)} method is called, with {@code event} representing the proper key.
* Whenever it collides with another Sprite, {@code collidesWith(Sprite other)} will be called for each sprite.
 * All these methods must be overriden by the user's own Sprite.
 */
public abstract class Sprite {//to be implemented by user

    private Node node;
    private double heading;
    private Game parentGame;//needed for accessing KeyStates and camera
    private double offsetX = 0;
    private double offsetY = 0;

    /**
     * This constructor is used if a Sprite creates another Sprite.
     * @param parent The Sprite that is creating this Sprite.
     * @param inNode The Node that is sent in by user. Will be drawn. Is automatically moved such that the parent Sprite is the origin.
     * @param heading Direction this Sprite faces by default, in degrees.
     */
    public Sprite(Sprite parent,Node inNode,double heading)
    {
        node = inNode;
        this.heading = heading;
        move(parent.getX(),parent.getY());//move this Sprite to the coordinates of the parent
        changeOffsetValues(parent.getOffsetX(),parent.getOffsetY());//ensure drawn at same location - fix offsets
    }

    /**
     * This constructor is used if a Sprite creates another Sprite. Has a default heading of 0.
     * @param parent The Sprite that is creating this Sprite.
     * @param inNode The Node that is sent in by user. Will be drawn. Is automatically moved such that the parent Sprite is the origin.
     */
    public Sprite(Sprite parent,Node inNode)
    {
        this(parent,inNode,0);
    }

    /**
     * Simple constructor. Just sets internal values to the parameters.
     * @param inNode The drawn node. Should not be offset prior to being added. Instead, make the Sprite, then use {@code move(x,y)}
     * @param heading The direction the Node is facing in degrees. 0 = right, 90 = up, 180 = left, 270 = down. Will wrap around for values greater than 360. Supports negatives.
     */
    public Sprite(Node inNode, double heading) {
        node = inNode;
        this.heading = heading;
    }

    /**
     * Simple constructor. Just sets internal values to the parameters. Defaults heading to 0.
     * @param inNode The drawn node. Should not be offset prior to being added. Instead, make the Sprite, then use {@code move(x,y)}
     */
    public Sprite(Node inNode) {
        this(inNode,0);
    }

    /**
     * Defaults Sprite to a black 10x10 rectangle, and heading to 0.
     */
    public Sprite() {
        this(new Rectangle(10,10));
    }

    //ABSTRACT METHODS

    /**
     * This method is called about 60 times every second for each Sprite.
     * Should contain any actions like Physics.tick(), or continous keyboard input, using keyPressed(KeyCode code)
     * @param now Time in nanoseconds since start of game.
     */
    public abstract void runPerTick(long now);//executed 60 times a second by Game

    /**
     * Called whenever a key is pressed.
     * @param event The KeyEvent of the key that is pressed. Mostly used for KeyEvent.getCode()
     */
    public abstract void whenKeyPressed(KeyEvent event);//whenever a key is pressed

    /**
     * Called whenever a Sprite collides with another, for each Sprite
     * @param other The Sprite collided with.
     */
    public abstract void collidesWith(Sprite other);//checks collision every tick

    /**
     * Will remove this sprite from the game after current tick.
     */
    public void removeThis()
    {
        parentGame.removeSprite(this);
    }

    /**
     * Adds the given Sprite to the parent game at the end of this tick.
     * @param sprite The Sprite to be added
     */
    protected void addSprite(Sprite sprite)
    {
        parentGame.addSprite(sprite);
    }

    /**
     * Used internally to set the reference of the parent game, for things like adding and removing Sprites.
     * @param game The parent game.
     */
    void setParentGame(Game game){//used by Game.java
        parentGame = game;
    }

    /**
     *
     * @return The reference to the parentGame.
     */
    Game getParentGame() {
        return parentGame;
    }

    void setOffset(double offX,double offY) {
        move(offX-offsetX,offY-offsetY);//move location to proper offset
        offsetX = offX;
        offsetY = offY;
    }
    void changeOffsetValues(double offX,double offY) {
        offsetX = offX;
        offsetY = offY;
    }

    /**
     * Moves the camera the given distances
     * @param x X distance to move
     * @param y Y distance to move
     */
    public void moveCamera(double x,double y)
    {
        parentGame.scroll(x,y);
    }

    /**
     * Moves the camera the given distance in the X-axis
     * @param x Distance to move
     */
    public void moveCameraX(double x)
    {
        parentGame.scrollX(x);
    }

    /**
     * Moves the camera the given distance in the Y-axis
     * @param y Distance to move
     */
    public void moveCameraY(double y)
    {
        parentGame.scrollY(y);
    }

    /**
     * Moves the camera to the given coordinates
     * @param x X coordinate
     * @param y Y coordinate
     */
    public void moveCameraTo(double x,double y)
    {
        parentGame.scrollTo(x,y);
    }

    /**
     * Will center the camera on this Sprite.
     */
    public void centerCamera() {
        parentGame.centerOn(this);
    }

    /**
     * If the provided KeyCode is pressed
     * @param code Asking about this KeyCode
     * @return Whether or not the given KeyCode is pressed
     */
    public boolean keyPressed(KeyCode code){//intended for access by user's implementation
        return parentGame.getStates().isKeyPressed(code);
    }

    /**
     * Rotate the given number of degrees
     * @param theta Rotate this many degrees
     */
    public void rotate(double theta) {//rotate theta degrees counterclockwise
        heading += theta;//modify the angle
        heading %= 360;//make the angle simpler
        node.setRotate(node.getRotate()+theta);//change the node's rotation
    }

    /**
     * Will set the rotation value to the given heading.
     * @param theta Degrees from 0 - right.
     */
    public void setHeading(double theta) {//set theta.
        heading = theta;//modify the angle
        heading %= 360;//make the angle simpler
        node.setRotate(theta);//set the rotation of node
    }

    /**
     * Returns the heading
     * @return The heading in degrees from right.
     */
    public double getHeading(){
        return heading;
    }

    /**
     * Move forward the privided distance, using the heading and trigonometry.
     * @param dist How far to move.
     */
    public void moveForward(double dist) {//used by user - will use heading and Trig to move indicated amount in Heading direction
        node.relocate(node.getLayoutX() + dist * Math.cos(Math.toRadians(heading)),node.getLayoutY() + dist * Math.sin(Math.toRadians(heading)));
    }

    /**
     * Moves the sprite dist pixels on the X-axis
     * @param dist Distance to move. Can be negative
     */
    public void moveX(double dist) {//used by user - moves dist pixels left(negative) or right
        move(dist,0);
    }

    /**
     * Moves the sprite dist pixels on the Y-axis
     * @param dist Distance to move. Can be negative
     */
    public void moveY(double dist) {//used by user - moves dist pixels up(negative) or down
        move(0,dist);
    }

    /**
     * Moves the sprite xDist pixels on the X-axis and yDist pixels on the Y-axis
     * @param xDist Distance to move on X-axis. Can be negative
     * @param yDist Distance to move on Y-axis. Can be negative
     */
    public void move(double xDist,double yDist) {//used by user - moves xDist pixels L/R, yDist pixels U/D
        node.relocate(node.getLayoutX()+xDist+node.getBoundsInLocal().getMinX(),node.getLayoutY()+yDist+node.getBoundsInLocal().getMinY());
    }

    double getOffsetX() {
        return offsetX;
    }

    double getOffsetY() {
        return offsetY;
    }

    /**
     * Returns the X value of the center of the Sprite
     * @return Roughly the X value of the center of the Sprite
     */
    public double getX(){return node.getLayoutX()+node.getLayoutBounds().getWidth()/2;}

    /**
     * Returns the Y value of the center of the Sprite
     * @return Roughly the Y value of the center of the Sprite
     */
    public double getY(){return node.getLayoutY()+node.getLayoutBounds().getHeight()/2;}

    /**
     * Returns the internal Node
     * @return The internal Node that is drawn to screen
     */
    public Node getNode() {//needed for collision, setting up drawing
        return node;
    }

    @Override
    public boolean equals(Object o) {//autogenerated!
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Sprite sprite = (Sprite) o;

        if (Double.compare(sprite.heading, heading) != 0) return false;
        if (Double.compare(sprite.offsetX, offsetX) != 0) return false;
        if (Double.compare(sprite.offsetY, offsetY) != 0) return false;
        if (!node.equals(sprite.node)) return false;
        return parentGame.equals(sprite.parentGame);
    }
}
