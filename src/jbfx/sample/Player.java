package jbfx.sample;

import javafx.scene.Node;
import jbfx.Sprite;

/**
 * Created by miller5157r on 4/27/2017.
 */
public class Player extends Sprite {
    public Player(Node node)
    {
        super(node);
    }
    public void runPerTick(long now)
    {
        if(now%4==0)
            super.rotate(10);
        else
            super.moveForward(10);
    }
}