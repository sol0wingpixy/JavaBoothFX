package jbfx.sample;

import javafx.scene.Node;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import jbfx.Sprite;

/**
 * Created by miller5157r on 4/27/2017.
 */
public class Player extends Sprite {

    public Player(Node node)
    {
        super(node);
        relocate(100,100);
    }
    private double xVel = 0;
    private double yVel = 0;
    private double step = 3;
    private int coinCount = 0;

    public void runPerTick(long now)
    {
        relocate(xVel,yVel);
    }
    public void whenKeyPressed(KeyEvent keyEvent)
    {
        if(keyEvent.getCode()== KeyCode.W)
        {
            relocateY(-step);
        }
        if(keyEvent.getCode()== KeyCode.S)
        {
            relocateY(step);
        }
        if(keyEvent.getCode()== KeyCode.A)
        {
            relocateX(-step);
        }
        if(keyEvent.getCode()== KeyCode.D)
        {
            relocateX(step);
        }
    }
    public void collidesWith(Sprite other) {
        if(other instanceof Collectable)
        {
            coinCount++;
            if(Math.random()<.5)
                other.relocateX(Math.random()*100+20);
            else
                other.relocateX(Math.random()*-100-20);
        }
    }
}