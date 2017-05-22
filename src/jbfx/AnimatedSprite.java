package jbfx;

import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.*;

public abstract class AnimatedSprite extends Sprite {
    private Map<String, List<Node>> animationMap;
    private List<Node> currentAnimation;
    private int currentAnimationFrame;

    public AnimatedSprite(List<Node> animationFrames, String animationName, double heading) {
        super(animationFrames.get(0), heading);
        animationMap = new HashMap<>();
        animationMap.put(animationName, animationFrames);
        currentAnimation = animationFrames;
        currentAnimationFrame = 0;
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
        return currentAnimation.get(currentAnimationFrame);
    }

    public void addAnimation(String animationName, List<Node> animationFrames) {
        animationMap.put(animationName, animationFrames);
    }

    public void setCurrentAnimation(String animationName) {
        currentAnimation = animationMap.get(animationName);
    }

    public Node nextFrame() {
        currentAnimationFrame %= currentAnimation.size();
        return currentAnimation.get(currentAnimationFrame);
    }
}
