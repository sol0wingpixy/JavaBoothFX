package jbfx.sample;

import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import jbfx.Sprite;

/**
 * Created by miller5157r on 6/5/2017.
 */
public class Util extends Sprite {
    public Util(){
        super(new Rectangle(1,1, Color.TRANSPARENT));
    }
    @Override
    public void runPerTick(long now) {
                if(keyPressed(KeyCode.I)) {
                    moveCameraY(3);
                }
                if(keyPressed(KeyCode.J)) {
                    moveCameraX(3);
                }
                if(keyPressed(KeyCode.K)) {
                    moveCameraY(-3);
                }
                if(keyPressed(KeyCode.L)) {
                    moveCameraX(-3);
                }
    }

    @Override
    public void whenKeyPressed(KeyEvent event) {

    }

    @Override
    public void collidesWith(Sprite other) {

    }
}
