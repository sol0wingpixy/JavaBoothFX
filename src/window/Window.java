package window;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.stage.Stage;
import sprite.*;

import java.util.ArrayList;

/**
 * Created by miller5157r on 4/14/2017.
 */
public class Window extends Application {
    private ArrayList<Sprite> sprites;
    private AnimationTimer ticker;
    public Window(AnimationTimer ticker, SpriteList sprites)
    {
        this.ticker = ticker;
        this.sprites = sprites.getList();
        launch();
    }
    public void start(Stage stage)
    {
        ticker.start();
    }
    public void addSprite(Sprite sprite)
    {

    }
}
