package jbfx.sample;

import javafx.scene.Node;
import javafx.scene.input.KeyEvent;
import jbfx.Sprite;

/**
 * Created by miller5157r on 5/1/2017.
 */
public class Collectable extends Sprite {
    public Collectable(Node in)
    {
        super(in);
    }
    @Override
    public void runPerTick(long now) {

    }

    @Override
    public void whenKeyPressed(KeyEvent event) {

    }

    @Override
    public void collidesWith(Sprite other) {
        System.out.println("PONG!");
        if(Math.random()<.5)
            relocateX(Math.random()*100+20);
        else
            relocateX(Math.random()*-100-20);
    }
}
