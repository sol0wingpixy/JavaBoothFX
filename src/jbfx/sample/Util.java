package jbfx.sample;

import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import jbfx.Sprite;

/**
 * Created by miller5157r on 6/5/2017.
 */
public class Util extends Sprite {
    @Override
    public void runPerTick(long now) {
                if(keyPressed(KeyCode.I)) {
                    camera.moveY(3);
                }
                if(keyPressed(KeyCode.J)) {
                    camera.moveX(3);
                }
                if(keyPressed(KeyCode.K)) {
                    camera.moveY(-3);
                }
                if(keyPressed(KeyCode.L)) {
                    camera.moveX(-3);
                }
    }

    @Override
    public void whenKeyPressed(KeyEvent event) {

    }

    @Override
    public void collidesWith(Sprite other) {

    }
}
