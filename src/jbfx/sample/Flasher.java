package jbfx.sample;

import javafx.scene.Node;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import jbfx.AnimatedSprite;
import jbfx.Sprite;

import java.util.List;

/**
 * Created by miller5157r on 5/18/2017.
 */
public class Flasher extends AnimatedSprite {
    public Flasher()
    {
        super();
    }
    @Override
    public void runPerTick(long now) {
        if(keyPressed(KeyCode.LEFT))
            moveX(-3);
        if(keyPressed(KeyCode.RIGHT))
            moveX(3);
        if(keyPressed(KeyCode.UP))
            moveY(-3);
        if(keyPressed(KeyCode.DOWN))
            moveY(3);
    }

    @Override
    public void whenKeyPressed(KeyEvent event) {

    }

    @Override
    public void collidesWith(Sprite other) {

    }
}
