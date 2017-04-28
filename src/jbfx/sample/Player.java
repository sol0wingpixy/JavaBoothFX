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
        translateX(100);
        translateY(100);
    }
    private double xVel = 0;
    private double yVel = 0;

    public void runPerTick(long now)
    {
        translateX(xVel);
        translateY(yVel);
    }
    public void whenKeyPressed(KeyEvent keyEvent)
    {
        if(keyEvent.getCode()== KeyCode.W)
        {
            yVel-=.3;
        }
        if(keyEvent.getCode()== KeyCode.S)
        {
            yVel+=.3;
        }
        if(keyEvent.getCode()== KeyCode.A)
        {
            xVel-=.3;
        }
        if(keyEvent.getCode()== KeyCode.D)
        {
            xVel+=.3;
        }
    }
}