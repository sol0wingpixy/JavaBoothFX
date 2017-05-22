package jbfx;

import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.*;

public abstract class AnimatedSprite extends Sprite {
    private Map<String, List<Node>> animationMap;
    private List<Node> currentAnimation;
    private int currentAnimationFrameIndex;
    /* The next frame in an animation is displayed every animationFrameRefreshDelay times nextFrame() is called */
    private int animationFrameRefreshDelay;
    private int animationFrameRefreshDelayCounter;

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
                new Rectangle(10, 10, Color.RED),
                new Rectangle(10, 10, Color.GREEN),
                new Rectangle(10, 10, Color.BLUE)));
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

    @Override
    public Node getNode() {
        return currentAnimation.get(currentAnimationFrameIndex);
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
        currentAnimationFrameIndex %= currentAnimation.size(); //loops animation frame back to start, if applicable
        if (animationFrameRefreshDelay == animationFrameRefreshDelayCounter++) {
            animationFrameRefreshDelayCounter = 1;
            return currentAnimation.get(currentAnimationFrameIndex++);
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
