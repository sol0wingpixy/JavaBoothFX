package jbfx;

import javafx.scene.Node;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.shape.Rectangle;

public abstract class Sprite {//to be implemented by user
    private Node node;
    private double heading;
    private Game parentGame;//needed for accessing KeyStates

    public Sprite(Node inNode,double heading) {
        node = inNode;
        this.heading = heading;
    }

    public Sprite(Node inNode) {
        this(inNode,0);
    }

    public Sprite() {
        this(new Rectangle(10,10));
    }

    public abstract void runPerTick(long now);//executed 60 times a second by Game

    public abstract void whenKeyPressed(KeyEvent event);//whenever a key is pressed

    public abstract void collidesWith(Sprite other);//checks collision every tick

    public void setParentGame(Game game){//used by Game.java
        parentGame = game;
    }

    public Game getParentGame() {
        return parentGame;
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
        node.relocate(node.getLayoutX() + dist,node.getLayoutY());
    }

    public void moveY(double dist) {//used by user - moves dist pixels up(negative) or down
        node.relocate(node.getLayoutX(),node.getLayoutY() + dist);
    }

    public void move(double xDist,double yDist) {//used by user - moves xDist pixels L/R, yDist pixels U/D
        node.relocate(node.getLayoutX()+xDist,node.getLayoutY()+yDist);
    }

    public Node getNode() {//needed for collision, setting up drawing
        return node;
    }
}
