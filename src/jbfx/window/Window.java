package jbfx.window;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.stage.Stage;
import jbfx.sprite.*;
import java.util.ArrayList;

/**
 * Created by miller5157r on 4/14/2017.
 */
public class Window extends Application {
    private ArrayList<Sprite> sprites;

    public Window(SpriteList sprites) {
        this.sprites = sprites.getList();
        launch();
    }

    public void start(Stage stage) {
        for(Sprite s:sprites)
        {
            s.getFrameHandler().start();
        }
    }

    public void addSprite(Sprite sprite) {
        sprites.add(sprite);
    }
}
