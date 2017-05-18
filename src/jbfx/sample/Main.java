package jbfx.sample;

import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import jbfx.Game;
//user is required to understand:
//  JavaFX Nodes - shapes or images, really
//  Extending an abstract class (Sprite)
public class Main {
    //This is the class the user makes and runs to make a game.
    public static void main(String[] args) {
        // a 'Game' is what the user will work with, adding sprites.
        Game game = new Game();
        //setHeight - setWidth - obvious
        game.setHeight(700);
        game.setWidth(1000);

        //Sprites are implemented by the user - see Player, Collectable, Floor classes
        Rectangle bobBox = new Rectangle(20,15,Color.STEELBLUE);
        //The default Sprite class uses a JavaFX Node - simple option is javafx shape
        Player bob = new Player(bobBox);
        Collectable coin = new Collectable(new Circle(10,Color.BLACK));
        coin.move(300,200);
        Floor floor = new Floor(450,game);
        //Sprites must be put into the local game to exist - what the user sees.

        Bug omnom = new Bug(new Polygon(30,30,0,0,0,60),0);
        omnom.move(300,300);


        Flasher spicey = new Flasher(new Circle(100,Color.YELLOW));

        game.addSprite(bob);
        game.addSprite(coin);
        game.addSprite(floor);
        game.addSprite(omnom);
        game.addSprite(spicey);

        //once everything's set, start the game.
        game.startGame();

    }
}
