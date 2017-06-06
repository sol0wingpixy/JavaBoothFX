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
        //if in the air, modify gravity
        if(onFloor) {
            yVel = 0;
        }
        else{
            yVel += gravity;
        }
        object.move(xVel,yVel);//move the sprite per tick
        //the following checks for onFloor.
        object.moveY(1);//offset the Sprite down one - will collide with floor
        boolean falling = true;//default value
        for(Sprite sprite:object.getParentGame().getAllSprites())
        {//go through all sprites
            if(!(sprite.equals(object))&&sprite.getNode().getBoundsInParent().intersects(object.getNode().getBoundsInParent()))//true if collides with something
            {//if the sprite does collide with something
                falling = false;//it won't be falling
                while(sprite.getNode().getBoundsInParent().intersects(object.getNode().getBoundsInParent()))//check collision
                {
                    object.moveY(-1);//this will move the sprite up on top of whatever floor - keeps from getting stuck
                }
                object.moveY(1);//would have ended up a little above floor - moves back down
            }
        }
        if(falling)//translates old boolean to onFloor state
            onFloor = false;
        else
            onFloor = true;
        object.moveY(-1);//un-do moveY(1) at beginning
    }
    /**
     * Explicitly informing this that it has hit a floor - jumps to top of floor.
     */
    public void hitFloor()
    {
        yVel = 0;
        onFloor = true;//set the sensical floor values
        List<Sprite> allSprites = object.getParentGame().getAllSprites();
        for(Sprite sprite:allSprites)
        {//go through all sprites
            while(!(sprite.equals(object))&&sprite.getNode().getBoundsInParent().intersects(object.getNode().getBoundsInParent()))//check collision
            {
                object.moveY(-1);//will move to top of whatever floor it's on.
            }
            object.moveY(1);//make look nice - sitting on floor
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
