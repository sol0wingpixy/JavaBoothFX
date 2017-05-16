package jbfx.sample;


import javafx.scene.Node;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Shape;
import jbfx.*;

/**
 * Created by miller5157r on 5/12/2017.
 */
public class Bug extends Sprite {
    public Bug(Node inNode, double heading) {
        super(inNode, heading);
    }

    @Override
    public void runPerTick(long now) {

        if(keyPressed(KeyCode.LEFT))
        {
            rotate(-3);
        }
        if(keyPressed(KeyCode.RIGHT))
        {
            rotate(3);
        }
        if(keyPressed(KeyCode.UP))
        {
            moveForward(2);
        }
    }

    @Override
    public void whenKeyPressed(KeyEvent event) {
        if(event.getCode()==KeyCode.SPACE) {

        }
    }

    @Override
    public void collidesWith(Sprite other) {

    }
}
