package jbfx;

import java.util.List;

/**
 * Created by miller5157r on 5/17/2017.
 */
public class Physics {
    private Sprite object;
    private double xVel = 0;
    private double yVel = 0;
    private boolean onFloor = false;
    private double gravity = .1;

    public Physics(Sprite object)
    {
        this.object = object;
    }
    public void tick()
    {
        if(!onFloor) {
            yVel += gravity;
        }
        object.move(xVel,yVel);
    }
    public void hitFloor()
    {
        yVel = 0;
        onFloor = true;
        List<Sprite> allSprites = object.getParentGame().getSprites();
        for(Sprite sprite:allSprites)
        {
            while(!(sprite.equals(object))&&sprite.getNode().getBoundsInParent().intersects(object.getNode().getBoundsInParent()))//check collision
            {
                object.moveY(-1);
            }
            object.moveY(1);
        }
    }
    public void jump(double magnitude)
    {
        onFloor = false;
        yVel = -magnitude;
    }
}
