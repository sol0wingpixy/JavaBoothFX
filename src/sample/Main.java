package sample;

import animation.FrameHandler;
import javafx.animation.AnimationTimer;
import sprite.Sprite;
import sprite.SpriteList;
import window.Window;

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
