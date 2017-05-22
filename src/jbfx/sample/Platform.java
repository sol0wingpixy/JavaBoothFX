package jbfx.sample;

import javafx.scene.input.KeyEvent;
import javafx.scene.shape.Rectangle;
import jbfx.Game;
import jbfx.Sprite;

/**
 * Created by miller5157r on 5/2/2017.
 */
public class Platform extends Sprite {
    public Platform(double x,double y,double width,double height)
    {
        super(new Rectangle(x,y,width,height));
    }
    
    @Override
    public void runPerTick(long now) {

    }

    @Override
    public void whenKeyPressed(KeyEvent event) {

    }

    @Override
    public void collidesWith(Sprite other) {

    }
}
