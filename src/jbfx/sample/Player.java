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
        move(100,100);
    }
    private double xVel = 0;
    private double yVel = 0;
    private double gravity = .1;
    private double step = 3;
    private int coinCount = 0;

    public void runPerTick(long now)
    {
        move(xVel,yVel);
        yVel+=gravity;
        if(keyPressed(KeyCode.D))
            moveX(step);
        if(keyPressed(KeyCode.A))
            moveX(-step);
    }
    public void whenKeyPressed(KeyEvent keyEvent)
    {
        if(keyEvent.getCode()== KeyCode.W)
        {
            yVel = -3;
        }
    }
    public void collidesWith(Sprite other) {
        if(other instanceof Collectable)
        {
            coinCount++;
            System.out.println("PING!: "+coinCount);
        }
        if(other instanceof Floor)
        {
            yVel = 0;
        }
    }
}