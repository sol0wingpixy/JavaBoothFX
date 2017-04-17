package sprite;

import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.shape.Shape;

public class Sprite {
    //include Node?
    private Image image;
    private Shape shape;
    private double heading;

    public Sprite(Shape shape, double heading, Image image) {
        this.shape = shape;
        this.heading = heading;
        this.image = image;
    }

    public Sprite(Shape shape, double heading){
        this(shape,heading,null);
    }

    public Sprite(Shape shape) {
        this(shape,0,null);
    }

    public void rotate(double theta) {
        theta = Math.toRadians(theta);
        heading +=theta;
        //rotate shape
    }
    public void setHeading(double theta) {
        theta = Math.toRadians(theta);
        heading = theta;
        //rotate shape
    }

    public void moveForward(double dist) {

    }

    public void translateX(double dist) {
        shape.setTranslateX(shape.getTranslateX()+dist);
    }

    public void translateY(double dist) {
        shape.setTranslateY(shape.getTranslateY()+dist);
    }
}
