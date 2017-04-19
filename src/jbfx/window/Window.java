package jbfx.window;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;
import jbfx.sprite.*;
import java.util.ArrayList;

/**
 * Created by miller5157r on 4/14/2017.
 */
public class Window extends Application {
    private static ArrayList<Sprite> sprites;
    static {
        sprites = new ArrayList<>();
    }
    public static void addSprite(Sprite sprite)
    {
        sprites.add(sprite);
    }

    public void start(Stage stage) {
        System.out.println("Starting Application");
        Group root = new Group();
        Scene scene = new Scene(root);
        for(Sprite s:sprites)
        {
            System.out.println("Starting FrameHandler");
            s.getFrameHandler().start();
            root.getChildren().add(s.getNode());
        }
        stage.setScene(scene);
        stage.show();
    }
}