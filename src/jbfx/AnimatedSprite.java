package jbfx;


import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 * Created by miller5157r on 5/18/2017.
 */
public abstract class AnimatedSprite extends Sprite{
    public AnimatedSprite(Node inNode)
    {
        super(inNode);
    }
    public Node nextFrame()
    {
        if(Math.random()<.5)
        return super.getNode();
        return new Rectangle(100,100, Color.RED);
    }
    //Stuff
}
