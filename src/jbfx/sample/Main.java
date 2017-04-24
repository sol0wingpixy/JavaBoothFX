package jbfx.sample;

import javafx.application.Application;
import javafx.scene.shape.Circle;
import jbfx.sprite.Sprite;
import jbfx.window.Game;
import jbfx.window.Window;

public class Main {
    public static void main(String[] args) {
        Game game = new Game();
        game.addSprite(new Sprite());
        game.addSprite(new Sprite(new Circle(100,100,10)));

        Window.setGameScene(game);
        System.out.println("Launching Application");
        Application.launch(Window.class,args);
    }
}
