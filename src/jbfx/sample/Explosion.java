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
        explosionFrames.add(new ImageView("4percent.PNG"));
        explosionFrames.add(new ImageView("IMG_0106.JPG"));
        explosionFrames.add(new ImageView("arch.jpg"));
        explosionFrames.add(new ImageView("blueMotorcycle.jpg"));
        explosionFrames.add(new ImageView("koala.jpg"));
        explosionFrames.add(new ImageView("moon-surface.jpg"));
        explosionFrames.add(new ImageView("snowman.jpg"));
        explosionFrames.add(new ImageView("temple.jpg"));
        explosionFrames.add(new ImageView("wall.jpg"));
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
        if(now-initTime>1000000000)
            removeThis();
    }

    @Override
    public void whenKeyPressed(KeyEvent event) {

    }

    @Override
    public void collidesWith(Sprite other) {

    }
}
