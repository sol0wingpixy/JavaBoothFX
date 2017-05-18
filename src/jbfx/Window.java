package jbfx;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;
//this is the JavaFX Application that runs - creates the window
public class Window extends Application {
    private static Game game;//game holds all actual variables
    public static void setGame(Game gameIn)
    {
        game = gameIn;
    }

    public void start(Stage stage) {
        System.out.println("Starting Application");//debug
        Group root = new Group();
        Group animatedGroup = new Group();
        Scene scene = new Scene(root);
        //get all sprites drawn to screen, timers, etc.
        game.makeScene(root,animatedGroup,stage);
        root.getChildren().add(animatedGroup);
        stage.setScene(scene);
        stage.show();
    }
}