package jbfx.sprite;

import jbfx.animation.FrameHandler;
import javafx.scene.image.Image;
import javafx.scene.shape.Shape;

public class Sprite extends Shape {
    public com.sun.javafx.geom.Shape impl_configShape()
    {
        return null;
    }
    private Image image;
    private FrameHandler handler;
    private double heading;

    public Sprite(double left,double top,double width,double height,double heading, Image image) {
        super();
        super.setTranslateX(left);
        super.setTranslateX(top);
        super.resize(width,height);

        this.heading = heading;
        this.image = image;
    }

    public Sprite( double heading){
        this(0,0,10,10,heading,null);
    }

    public Sprite() {
        this(0);
    }

    public void addFrameHandler(FrameHandler handler) {
        this.handler = handler;
    }

    public FrameHandler getFrameHandler() {
        return handler;
    }

    public void rotate(double theta) {
        theta = Math.toRadians(theta);
        heading +=theta;
        super.setRotate(super.getRotate()+theta);
    }
    public void setHeading(double theta) {
        theta = Math.toRadians(theta);
        heading = theta;
        super.setRotate(theta);
    }

    public void moveForward(double dist) {
        super.setTranslateX(super.getTranslateX()+dist*Math.cos(heading));
        super.setTranslateY(super.getTranslateY()+dist*Math.sin(heading));
    }

    public void translateX(double dist) {
        super.setTranslateX(super.getTranslateX()+dist);
    }

    public void translateY(double dist) {
        super.setTranslateY(super.getTranslateY()+dist);
    }
}
