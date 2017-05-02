package jbfx;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.input.InputEvent;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

import java.util.ArrayList;

//This class is not to be seen by the user
public class Game {
    private double width;
    private double height;
    private ArrayList<Sprite> sprites;

    public Game() {
        sprites = new ArrayList<>();
        width = 600;
        height = 300;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public void startAnimation() {
        new AnimationTimer() {
            @Override
            public void handle(long now) {
                for (Sprite sprite : sprites) {
                    sprite.runPerTick(now);
                }
                //Could set up another AnimationTimer - should?
                for(int i=0;i<sprites.size();i++)
                {
                    for(int j = i+1;j<sprites.size();j++)
                    {
                        if(sprites.get(i).getNode().getBoundsInParent().intersects(sprites.get(j).getNode().getBoundsInParent()))//simple
                        {
                            sprites.get(i).collidesWith(sprites.get(j));
                            sprites.get(j).collidesWith(sprites.get(i));
                        }
                    }
                }
            }
        }.start();
    }

    public void startListening(Stage stage) {
        stage.addEventHandler(KeyEvent.KEY_PRESSED,new EventHandler<KeyEvent>() {
            public void handle(KeyEvent keyEvent) {
                for (Sprite sprite : sprites) {
                    sprite.whenKeyPressed(keyEvent);
                }
            }
        });
    }

    public ArrayList<Sprite> getSprites() {
        return sprites;
    }

    public void addSprite(Sprite sprite) {
        sprites.add(sprite);
    }

    public double getWidth() {
        return width;
    }

    public double getHeight() {
        return height;
    }

    public void startGame() {
        Window.setGame(this);
        Application.launch(Window.class);
    }

    public static void makeScene(Game game, Group root, Stage stage) {
        for (Sprite sprite : game.getSprites()) {
            root.getChildren().add(sprite.getNode());
        }
        stage.setWidth(game.getWidth());
        stage.setHeight(game.getHeight());
        game.startAnimation();
        game.startListening(stage);
    }
}
