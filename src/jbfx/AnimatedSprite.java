package jbfx;

import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * A modified version of Sprite that animates with multiple nodes.
 */
public abstract class AnimatedSprite extends Sprite {
    private Map<String, List<Node>> animationMap;
    private List<Node> currentAnimation;
    private int currentAnimationFrameIndex;
    /* The next frame in an animation is displayed every animationFrameRefreshDelay times nextFrame() is called */
    private int animationFrameRefreshDelay;
    private int animationFrameRefreshDelayCounter;

    public AnimatedSprite(Sprite parent,List<Node> animationFrames) {
        this(parent,animationFrames, "default",0);
    }

    public AnimatedSprite(Sprite parent,List<Node> animationFrames, String animationName, double heading) {
        super(animationFrames.get(0), heading);
        animationMap = new HashMap<>();
        animationMap.put(animationName, animationFrames);
        currentAnimation = animationFrames;
        currentAnimationFrameIndex = 0;
        animationFrameRefreshDelay = 1;
        animationFrameRefreshDelayCounter = 1;

        move(parent.getX(),parent.getY());
        changeOffsetValues(parent.getOffsetX(),parent.getOffsetY());
        move(-currentAnimation.get(0).getLayoutBounds().getWidth()/2,-currentAnimation.get(0).getLayoutBounds().getHeight()/2);
    }

    public AnimatedSprite(List<Node> animationFrames, String animationName, double heading) {
        super(animationFrames.get(0), heading);
        animationMap = new HashMap<>();
        animationMap.put(animationName, animationFrames);
        currentAnimation = animationFrames;
        currentAnimationFrameIndex = 0;
        animationFrameRefreshDelay = 1;
        animationFrameRefreshDelayCounter = 1;
    }


    public AnimatedSprite(List<Node> animationFrames, String animationName) {
        this(animationFrames, animationName, 0.0);
    }

    public AnimatedSprite(List<Node> animationFrames) {
        this(animationFrames, "default");
    }



    /* Default AnimatedSprite is a Rectangle that changes color */
    public AnimatedSprite() {
        this(Arrays.asList(
                new Rectangle(10, 10, Color.TRANSPARENT),
                new Rectangle(10, 10, Color.RED),
                new Rectangle(10, 10, Color.GREEN),
                new Rectangle(10, 10, Color.YELLOW),
                new Rectangle(10, 10, Color.BLUE),
                new Rectangle(10, 10, Color.HOTPINK)));
    }
    @Override
    public void setOffset(double offX,double offY) {
        for (Map.Entry<String, List<Node>> entry : animationMap.entrySet()) {
            for (Node node : entry.getValue()) {
                node.relocate(node.getLayoutX()+offX-getOffsetX()+node.getBoundsInLocal().getMinX(),node.getLayoutY()+offY-getOffsetY()+node.getBoundsInLocal().getMinY());
            }
        }
        changeOffsetValues(offX,offY);
    }
    @Override
    public void rotate(double theta) {
        setHeading(getHeading() + theta);
        for (Map.Entry<String, List<Node>> entry : animationMap.entrySet()) {
            for (Node node : entry.getValue()) {
                node.setRotate(node.getRotate() + theta);
            }
        }
    }

    public void move(double xDist,double yDist){
        for(List<Node> allAnimationList:animationMap.values())
            for(Node node:allAnimationList)
                node.relocate(node.getLayoutX()+xDist+node.getBoundsInLocal().getMinX(),node.getLayoutY()+yDist+node.getBoundsInLocal().getMinY());
    }

    @Override
    public Node getNode() {
        return currentAnimation.get(currentAnimationFrameIndex%currentAnimation.size());
    }

    public void addAnimation(String animationName, List<Node> animationFrames) {
        animationMap.put(animationName, animationFrames);
    }

    public void setCurrentAnimation(String animationName) {
        currentAnimation = animationMap.get(animationName);
    }

    /* Returns the frame that should be displayed by the
       current animation (dependent on animationFPS).
       Called 60 times per second by Game.
     */
    public Node nextFrame() {
        if (animationFrameRefreshDelay == animationFrameRefreshDelayCounter++) {
            animationFrameRefreshDelayCounter = 1;
            int nextFrameIndex = (currentAnimationFrameIndex+1)%currentAnimation.size();
            //center image
            double xMove = currentAnimation.get(currentAnimationFrameIndex).getLayoutBounds().getWidth()/2-currentAnimation.get(nextFrameIndex).getLayoutBounds().getWidth()/2;
            double yMove = currentAnimation.get(currentAnimationFrameIndex).getLayoutBounds().getHeight()/2-currentAnimation.get(nextFrameIndex).getLayoutBounds().getHeight()/2;
            move(xMove,yMove);
            currentAnimationFrameIndex = nextFrameIndex;
        }
        return currentAnimation.get(currentAnimationFrameIndex);
    }

    /* Sets animationFrameRefreshDelay based on supplied input.
       Supplied animation FPS must be a factor of 60,
       else, do nothing.
     */
    public void setAnimationFPS(int fps) {
        if (60 % fps == 0) {
            animationFrameRefreshDelay = 60 / fps;
        } else {
            System.out.println("FPS must be a factor of 60. FPS value defaulted to 60.");
        }
    }
}
