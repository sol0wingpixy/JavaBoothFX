package jbfx.sprite;

import javafx.scene.Node;
import javafx.scene.shape.Rectangle;
import jbfx.animation.FrameHandler;
import javafx.scene.image.Image;

public class Sprite {
    private Image image;
    private Node node;
    private FrameHandler handler;
    private double heading;

    public Sprite(Node inNode,double heading, Image image) {
        super();
        node = inNode;

        this.heading = heading;
        this.image = image;
    }

    public Sprite() {
        this(new Rectangle(10,10),0,null);
    }

    public void addFrameHandler(FrameHandler handler) {
        this.handler = handler;
    }

    public FrameHandler getFrameHandler() {
        return handler;
    }

    public Node getNode()
    {
        return node;
    }

    public void rotate(double theta) {
        theta = Math.toRadians(theta);
        heading +=theta;
        node.setRotate(node.getRotate()+theta);
    }
    public void setHeading(double theta) {
        theta = Math.toRadians(theta);
        heading = theta;
        node.setRotate(theta);
    }

    public void moveForward(double dist) {
        node.setTranslateX(node.getTranslateX()+dist*Math.cos(heading));
        node.setTranslateY(node.getTranslateY()+dist*Math.sin(heading));
    }

    public void translateX(double dist) {
        node.setTranslateX(node.getTranslateX()+dist);
    }

    public void translateY(double dist) {
        node.setTranslateY(node.getTranslateY()+dist);
    }
}
