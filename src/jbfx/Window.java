package jbfx;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.util.ArrayList;

public class Window extends Application {
    private static Game game;
    public static void setGame(Game gameIn)
    {
        game = gameIn;
    }

    public void start(Stage stage) {
        System.out.println("Starting Application");
        Group root = new Group();
        Scene scene = new Scene(root);
        Game.makeScene(game,root,stage);
        stage.setScene(scene);
        stage.show();
    }
}