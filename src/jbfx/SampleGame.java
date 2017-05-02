package jbfx;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.stage.Stage;

public class SampleGame extends Application {

    @Override
    public void start(Stage stage) {
        Canvas canvas = new Canvas(800, 600);
        Group root = new Group();
        root.getChildren().add(canvas);
        Scene scene = new Scene(root);

        GraphicsContext gc = canvas.getGraphicsContext2D();

        Sprite penguin = new Sprite();
        penguin.setImage("https://www.gstatic.com/webp/gallery3/2.sm.png");
        penguin.setPosition(0.0,0.0);

        new AnimationTimer() {
            @Override
            public void handle(long now) {
                penguin.render(gc);
            }
        }.start();

        stage.setTitle("My Game");
        stage.setScene(scene);
        stage.show();
    }
}