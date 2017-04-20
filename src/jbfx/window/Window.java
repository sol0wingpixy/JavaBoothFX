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
    private static ArrayList<Sprite> sprites = new ArrayList<>();
    private static double width = 100;
    private static double height = 100;
    public static void addSprite(Sprite sprite)
    {
        sprites.add(sprite);
    }
    public static void setWidth(double widthIn){
        width = widthIn;
    }
    public static void setHeight(double heightIn){
        height = heightIn;
    }
    public void start(Stage stage) {
        System.out.println("Starting Application");
        Group root = new Group();
        Scene scene = new Scene(root,width,height);
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