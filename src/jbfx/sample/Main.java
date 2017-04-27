package jbfx.sample;

import javafx.application.Application;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import jbfx.Sprite;
import jbfx.Game;
import jbfx.Window;

public class Main {
    public static void main(String[] args) {
        Game game = new Game();
        game.addSprite(new Player(new Rectangle(20,10, Color.STEELBLUE)));

        game.startGame();
    }
}
