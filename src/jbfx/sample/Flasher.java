package jbfx.sample;

import javafx.scene.Node;
import javafx.scene.input.KeyEvent;
import jbfx.AnimatedSprite;
import jbfx.Sprite;

/**
 * Created by miller5157r on 5/18/2017.
 */
public class Flasher extends AnimatedSprite {
    public Flasher(Node inNode)
    {
        super(inNode);
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
