package jbfx.sprite;

import javafx.animation.AnimationTimer;
import javafx.scene.Node;
import javafx.scene.shape.Rectangle;
import jbfx.animation.FrameHandler;
import javafx.scene.image.Image;

public class Sprite {
    private Node node;
    private double heading;

    public Sprite(Node inNode,double heading) {
        super();
        node = inNode;
        this.heading = heading;
    }
    public Sprite(Node inNode)
    {
        this(inNode,0);
    }

    public Sprite() {
        this(new Rectangle(10,10));
    }

    public void runPerTick(long now)
    {
        if(now%9==0)
            moveForward(20);
        else
            rotate(10);
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

    public Node getNode()
    {
        return node;
    }
}
