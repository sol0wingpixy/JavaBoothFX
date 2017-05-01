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
        Rectangle hitbox = new Rectangle(20,15,Color.STEELBLUE);
        Player bob = new Player(hitbox);
        Collectable coin = new Collectable(new Circle(10,Color.BLACK));
        coin.relocateY(20);
        game.addSprite(bob);
        game.addSprite(coin);

        game.startGame();
    }
}
