package jbfx.sample;

import jbfx.animation.FrameHandler;
import javafx.animation.AnimationTimer;
import jbfx.sprite.Sprite;
import jbfx.sprite.SpriteList;
import jbfx.window.Window;

public class Main {
    public static void main(String[] args) {
        Sprite sprite = new Sprite();
        sprite.addFrameHandler(new FrameHandler() {
            @Override
            public void runPerTick(long now) {

            }
        });
        SpriteList sprites = new SpriteList();
        sprites.add(sprite);
        Window screen = new Window(sprites);
    }
}
