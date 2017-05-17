package jbfx.sample;

import javafx.scene.Node;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import jbfx.Physics;
import jbfx.Sprite;

/**
 * Created by miller5157r on 4/27/2017.
 */
public class Player extends Sprite {

    private Physics physics;
    private double step = 3;
    private int coinCount = 0;

    public Player(Node node)
    {
        super(node);
        physics = new Physics(this);
    }

    public void runPerTick(long now)
    {
        physics.tick();
        if(keyPressed(KeyCode.D))
            moveX(step);
        if(keyPressed(KeyCode.A))
            moveX(-step);
    }
    public void whenKeyPressed(KeyEvent keyEvent)
    {
        if(keyEvent.getCode()== KeyCode.W)//jump
        {
            physics.jump(3);
        }
    }
    public void collidesWith(Sprite other) {
        if(other instanceof Collectable)
        {
            coinCount++;
            System.out.println("PING!: "+coinCount);
        }
        else
        {
            physics.hitFloor();
        }
    }
}