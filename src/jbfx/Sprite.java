package jbfx;

import javafx.scene.Node;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.shape.Rectangle;
/**
*This is the basic Sprite class. Everything that appears on screen will directly extend either Sprite or AnimatedSprites.\n
* Each frame, the {@code runPerTick(long now)} method will be called.
* Whenever a key is pressed, the {@code whenKeyPressed(KeyEvent event)} method is called, with {@code event} representing the proper key.
* Whenever it collides with another Sprite, {@code collidesWith(Sprite other)} will be called for each sprite.\n
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
        move(parent.getX(),parent.getY());
        changeOffsetValues(parent.getOffsetX(),parent.getOffsetY());
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
     * 
     * @param now
     */
    public abstract void runPerTick(long now);//executed 60 times a second by Game

    public abstract void whenKeyPressed(KeyEvent event);//whenever a key is pressed

    public abstract void collidesWith(Sprite other);//checks collision every tick


    public void removeThis()
    {
        parentGame.removeSprite(this);
    }

    public void addSprite(Sprite sprite)
    {
        parentGame.addSprite(sprite);
    }

    public void setParentGame(Game game){//used by Game.java
        parentGame = game;
    }

    public Game getParentGame() {
        return parentGame;
    }

    public void setOffset(double offX,double offY) {
        move(offX-offsetX,offY-offsetY);
        offsetX = offX;
        offsetY = offY;
    }
    protected void changeOffsetValues(double offX,double offY) {
        offsetX = offX;
        offsetY = offY;
    }
    public void centerCamera() {
        parentGame.centerOn(this);
    }

    public boolean keyPressed(KeyCode code){//intended for access by user's implementation
        return parentGame.getStates().isKeyPressed(code);
    }

    public void rotate(double theta) {//rotate theta degrees counterclockwise
        heading += theta;
        heading %=360;
        node.setRotate(node.getRotate()+theta);
    }
    public void setHeading(double theta) {//set theta.
        heading = theta;
        heading %= 360;
        node.setRotate(theta);
    }
    public double getHeading(){
        return heading;
    }

    public void moveForward(double dist) {//used by user - will use heading and Trig to move indicated amount in Heading direction
        node.relocate(node.getLayoutX() + dist * Math.cos(Math.toRadians(heading)),node.getLayoutY() + dist * Math.sin(Math.toRadians(heading)));
    }

    public void moveX(double dist) {//used by user - moves dist pixels left(negative) or right
        move(dist,0);
    }

    public void moveY(double dist) {//used by user - moves dist pixels up(negative) or down
        move(0,dist);
    }

    public void move(double xDist,double yDist) {//used by user - moves xDist pixels L/R, yDist pixels U/D
        node.relocate(node.getLayoutX()+xDist+node.getBoundsInLocal().getMinX(),node.getLayoutY()+yDist+node.getBoundsInLocal().getMinY());
    }

    public double getOffsetX() {
        return offsetX;
    }

    public double getOffsetY() {
        return offsetY;
    }

    public double getX(){return node.getLayoutX()+node.getLayoutBounds().getWidth()/2;}

    public double getY(){return node.getLayoutY()+node.getLayoutBounds().getHeight()/2;}

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
