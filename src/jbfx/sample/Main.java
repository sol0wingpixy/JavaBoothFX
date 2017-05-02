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
        game.setHeight(500);
        game.setWidth(700);

        Player bob = new Player(new Rectangle(20,15,Color.STEELBLUE));
        Collectable coin = new Collectable(new Circle(10,Color.BLACK));
        Floor floor = new Floor(450,game);
        game.addSprite(bob);
        game.addSprite(coin);
        game.addSprite(floor);


        game.startGame();
    }
}
