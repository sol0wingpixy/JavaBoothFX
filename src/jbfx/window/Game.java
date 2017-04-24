package jbfx.window;

import javafx.animation.AnimationTimer;
import javafx.scene.Group;
import javafx.stage.Stage;
import jbfx.sprite.Sprite;

import java.util.ArrayList;

/**
 * Created by miller5157r on 4/21/2017.
 */
public class Game {

    private double width;
    private double height;
    private ArrayList<Sprite> sprites;
    public Game()
    {
        sprites = new ArrayList<>();
        width = 600;
        height = 300;
    }
    public void startAnimation()
    {
        new AnimationTimer(){
            @Override
            public void handle(long now)
            {
                for(Sprite sprite:sprites)
                {
                    sprite.runPerTick(now);
                }
            }
        }.start();
    }
    public ArrayList<Sprite> getSprites()
    {
        return sprites;
    }
    public void addSprite(Sprite sprite)
    {
        sprites.add(sprite);
    }
    public double getWidth()
    {
        return width;
    }
    public double getHeight()
    {
        return height;
    }


    public static void makeScene(Game game, Group root, Stage stage)
    {
        for(Sprite sprite:game.getSprites())
        {
            root.getChildren().add(sprite.getNode());
        }
        stage.setWidth(game.getWidth());
        stage.setHeight(game.getHeight());
        game.startAnimation();
    }
}
