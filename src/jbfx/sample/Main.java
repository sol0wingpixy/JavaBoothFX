package jbfx.sample;

import javafx.application.Application;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import jbfx.Sprite;
import jbfx.Game;
import jbfx.Window;
//user is required to understand:
//  JavaFX Nodes - shapes or images, really
//  Extending an abstract class (Sprite)
public class Main {
    //This is the class the user makes and runs to make a game.
    public static void main(String[] args) {
        // a 'Game' is what the user will work with, adding sprites.
        Game game = new Game();
        //setHeight - setWidth - obvious
        game.setHeight(500);
        game.setWidth(700);

        //Sprites are implemented by the user - see Player, Collectable, Floor classes
        Rectangle bobBox = new Rectangle(20,15,Color.STEELBLUE);
        //The default Sprite class uses a JavaFX Node - simple option is javafx shape
        Player bob = new Player(bobBox);
        Collectable coin = new Collectable(new Circle(10,Color.BLACK));
        coin.move(300,200);
        Floor floor = new Floor(450,game);
        //Sprites must be put into the local game to exist - what the user sees.

        Bug omnom = new Bug(new Polygon(10,10,0,0,0,20),0);
        Bug omnom1 = new Bug(new Polygon(10,10,0,0,0,20),0);
        Bug omnom2 = new Bug(new Polygon(10,10,0,0,0,20),0);

        omnom.move(300,300);
        omnom1.move(200,200);
        omnom2.move(100,100);
        game.addSprite(bob);
        game.addSprite(coin);
        game.addSprite(floor);
        game.addSprite(omnom);
        game.addSprite(omnom1);
        game.addSprite(omnom2);

        //once everything's set, start the game.
        game.startGame();

    }
}
