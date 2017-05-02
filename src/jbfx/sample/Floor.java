package jbfx.sample;

import javafx.scene.input.KeyEvent;
import javafx.scene.shape.Rectangle;
import jbfx.Game;
import jbfx.Sprite;

/**
 * Created by miller5157r on 5/2/2017.
 */
public class Floor extends Sprite {
    public Floor(double height,Game game)
    {
        super(new Rectangle(0,height,game.getWidth(),game.getHeight()-height));
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
