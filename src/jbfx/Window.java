package jbfx;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * This is the actual JavaFX Application that is run by Game
 */
public class Window extends Application {
    private static Game game;//game holds all actual variables
    static void setGame(Game gameIn)
    {
        game = gameIn;
    }

    /**
     * Only run by Game - Creates and displays the actual window
     * @param stage Default!
     */
    @Override
    public void start(Stage stage) {
        Group root = new Group();
        Group animatedGroup = new Group();
        Scene scene = new Scene(root);
        //get all sprites drawn to screen, timers, etc.
        game.makeScene(root,animatedGroup,stage);
        stage.setScene(scene);
        stage.show();
    }
}