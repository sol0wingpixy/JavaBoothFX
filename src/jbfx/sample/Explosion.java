package jbfx.sample;

import javafx.scene.Node;
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
    private static final List<Node> explosionFrames = new ArrayList<>();
    static{
        explosionFrames.add(new Polygon(0,0,0,10,10,10,10,0));
        explosionFrames.add(new Polygon(5,0,10,5,5,10,0,5));
    }

    public Explosion(Sprite parent)
    {
        super(parent,new ArrayList<>(Explosion.explosionFrames));
    }
    @Override
    public void runPerTick(long now) {

    }

    @Override
    public void whenKeyPressed(KeyEvent event) {

    }

    @Override
    public void collidesWith(Sprite other) {

    }
}
