package jbfx.sample;

import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import jbfx.Sprite;

/**
 * Created by miller5157r on 5/24/2017.
 */
public class Bullet extends Sprite {
    private double xVel,yVel;
    private long originTime = -1;
    private Sprite parent;
    public Bullet(double xVel,double yVel,Sprite parent)
    {
        super(parent,new Circle(5, Color.MAROON));
        this.xVel = xVel;
        this.yVel = yVel;
        this.parent = parent;
    }
    @Override
    public void runPerTick(long now) {
        move(xVel,yVel);
        if(originTime<0)
            originTime = now;
        if(now-originTime>9999999999L)
        {
            removeThis();
            addSprite(new Explosion(this));
        }
    }

    @Override
    public void whenKeyPressed(KeyEvent event) {

    }

    @Override
    public void collidesWith(Sprite other) {
        if(!(other.equals(parent)||other instanceof Bullet||other instanceof Explosion))
        {
            removeThis();
            addSprite(new Explosion(this));
        }
    }
}
