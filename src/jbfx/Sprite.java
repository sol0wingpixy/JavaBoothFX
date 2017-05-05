package jbfx;

import javafx.scene.Node;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.shape.Rectangle;

public abstract class Sprite {
    private Node node;
    private double heading;
    private KeyStates handler;
    private Game parentGame;

    public Sprite(Node inNode,double heading) {
        super();
        node = inNode;
        this.heading = heading;
    }

    public Sprite(Node inNode) {
        this(inNode,0);
    }

    public Sprite() {
        this(new Rectangle(10,10));
    }

    public abstract void runPerTick(long now);

    public abstract void whenKeyPressed(KeyEvent event);

    public abstract void collidesWith(Sprite other);

    public void addInputHandler(KeyStates handler) {
        this.handler = handler;
    }

    public void setParentGame(Game game){
        parentGame = game;
    }

    public boolean keyPressed(KeyCode code){
        return parentGame.getStates().isKeyPressed(code);
    }

    public void rotate(double theta) {
        theta = Math.toRadians(theta);
        heading += theta;
        node.setRotate(node.getRotate()+theta);
    }
    public void setHeading(double theta) {
        theta = Math.toRadians(theta);
        heading = theta;
        node.setRotate(theta);
    }

    public void moveForward(double dist) {
        node.relocate(node.getLayoutX() + dist * Math.cos(heading),node.getLayoutY() + dist * Math.sin(heading));
    }

    public void moveX(double dist) {
        node.relocate(node.getLayoutX() + dist,node.getLayoutY());
    }

    public void moveY(double dist) {
        node.relocate(node.getLayoutX(),node.getLayoutY() + dist);
    }

    public void move(double xDist,double yDist)
    {
        node.relocate(node.getLayoutX()+xDist,node.getLayoutY()+yDist);
    }

    public Node getNode() {
        return node;
    }
}
