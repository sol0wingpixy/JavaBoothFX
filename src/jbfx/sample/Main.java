package jbfx.sample;

import javafx.application.Application;
import jbfx.animation.FrameHandler;
import javafx.animation.AnimationTimer;
import jbfx.sprite.Sprite;
import jbfx.window.GameScene;
import jbfx.window.Window;

public class Main {
    public static void main(String[] args) {
        GameScene gameScene = new GameScene();

        System.out.println("Launching Application");
        Application.launch(Window.class,args);
    }
}
