package jbfx;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;
import jbfx.FrameHandler;
import jbfx.Sprite;

import java.util.ArrayList;

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

    public static double getWidth() {
        return width;
    }

    public static double getHeight() {
        return height;
    }

    public void start(Stage stage) {
        System.out.println("Starting Application");
        Group root = new Group();
        Scene scene = new Scene(root,width,height);
        new FrameHandler() {
            @Override
            public void runPerTick(long now) {
                for(Sprite s:sprites)
                {
                    s.getFrameHandler().runPerTick(now);
                }
            }
        }.start();
        for(Sprite s:sprites)
        {
            root.getChildren().add(s.getNode());
        }
        stage.setScene(scene);
        stage.show();
    }
}