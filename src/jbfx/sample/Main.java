package jbfx.sample;

import javafx.application.Application;
import jbfx.animation.FrameHandler;
import javafx.animation.AnimationTimer;
import jbfx.sprite.Sprite;
import jbfx.window.Window;

public class Main {
    public static void main(String[] args) {
        Sprite sprite = new Sprite();
        sprite.addFrameHandler(new FrameHandler() {
            private double velX;
            private double velY=1;
            @Override
            public void runPerTick(long now) {
                velX+=.1;
                velY+=.01;
                sprite.translateX(velX);
                sprite.translateY(velY);
                System.out.println("RunPerTick");
                System.out.println(sprite.getNode().translateXProperty());
                if(sprite.getNode().translateXProperty().greaterThan(1000).get())
                {
                    sprite.translateX(-1000);
                }
                if(sprite.getNode().translateYProperty().greaterThan(600).get())
                {
                    sprite.translateY(-800);
                }
            }
        });

        Window.addSprite(sprite);
        Window.setWidth(1000);
        Window.setHeight(600);
        System.out.println("Launching Application");
        Application.launch(Window.class,args);
    }
}
