package jbfx;

import java.util.List;

/**
 * A Physics helper to be implemented in user-made Sprites
 */
public class Physics {
    private Sprite object;
    private double xVel = 0;
    private double yVel = 0;
    private boolean onFloor = false;
    private double gravity = .1;

    /**
     * Simply remembers which Sprite is the object of the physics.
     * @param object The parent.
     */
    public Physics(Sprite object)
    {
        this.object = object;
    }

    /**
     * Sould be called every frame, checks gravity, velocity, and if it is on another sprite (onFloor).
     */
    public void tick()
    {
        if(onFloor) {
            yVel = 0;
        }
        else{
            yVel += gravity;
        }
        object.move(xVel,yVel);
        object.moveY(1);
        boolean falling = true;
        for(Sprite sprite:object.getParentGame().getAllSprites())
        {
            if(!(sprite.equals(object))&&sprite.getNode().getBoundsInParent().intersects(object.getNode().getBoundsInParent()))//true if collides with something
            {
                falling = false;
                while(sprite.getNode().getBoundsInParent().intersects(object.getNode().getBoundsInParent()))//check collision
                {
                    object.moveY(-1);
                }
                object.moveY(1);
            }
        }
        if(falling)
            onFloor = false;
        else
            onFloor = true;
        object.moveY(-1);
    }

    /**
     * Explicitly informing this that it has hit a floor - jumps to top of floor.
     */
    public void hitFloor()
    {
        yVel = 0;
        onFloor = true;
        List<Sprite> allSprites = object.getParentGame().getAllSprites();
        for(Sprite sprite:allSprites)
        {
            while(!(sprite.equals(object))&&sprite.getNode().getBoundsInParent().intersects(object.getNode().getBoundsInParent()))//check collision
            {
                object.moveY(-1);
            }
            object.moveY(1);
        }
    }

    /**
     * Sets not on floor, increases yVel by manitude.
     * @param magnitude Increases yVel by this
     */
    public void jump(double magnitude)
    {
        onFloor = false;
        yVel = -magnitude;
    }

    /**
     * Establishes gravitiational acceleration
     * @param g Gravity coefficient.
     */
    public void setGravity(double g)
    {
        gravity = g;
    }
}
