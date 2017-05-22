package jbfx.sample;

import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import jbfx.Game;
import jbfx.Sprite;

import java.util.ArrayList;

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
        bob.move(0,-10);
        ArrayList<Sprite> floors = new ArrayList<>();

        floors.add(new Platform(0,0,1000,5));
        floors.add(new Platform(50,-50,100,5));
        floors.add(new Platform(150,-150,50,5));
        //Sprites must be put into the local game to exist - what the user sees.



        game.addSprite(bob);
        game.addSprites(floors);

        /* start testing block
        Bug omnom = new Bug(new Polygon(30,30,0,0,0,60),0);
        omnom.move(300,300);

        Flasher spicey = new Flasher(new Circle(100,Color.YELLOW));
        Collectable coin = new Collectable(new Circle(10,Color.BLACK));
        coin.move(300,200);

        game.addSprite(omnom);
        game.addSprite(coin);
        game.addSprite(spicey);

        // end testing block */
        //once everything's set, start the game.
        game.startGame();

    }
}
