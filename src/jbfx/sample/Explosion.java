package jbfx.sample;

import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.shape.Polygon;
import jbfx.AnimatedSprite;
import jbfx.Sprite;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by miller5157r on 5/24/2017.
 */
public class Explosion extends AnimatedSprite {
    private static final List<ImageView> explosionFrames = new ArrayList<>();
    static{
        explosionFrames.add(new ImageView("4percent.png"));
        explosionFrames.add(new ImageView("IMG_0106.JPG"));
    }
    private static List<Node> getExplosionCopy()
    {
        List<Node> copy = new ArrayList<>();
        for(ImageView frame:explosionFrames)
        {
            copy.add(new ImageView(frame.getImage()));
        }
        return copy;
    }

    private long initTime = -1;
    public Explosion(Sprite parent)
    {
        super(parent,Explosion.getExplosionCopy());
    }
    @Override
    public void runPerTick(long now) {
        if(initTime<0)
            initTime = now;
        if(now-initTime>2000)
            removeThis();
    }

    @Override
    public void whenKeyPressed(KeyEvent event) {

    }

    @Override
    public void collidesWith(Sprite other) {

    }
}
