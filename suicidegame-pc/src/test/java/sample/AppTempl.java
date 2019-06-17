package sample;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import lombok.extern.apachecommons.CommonsLog;

@CommonsLog(topic = "CONSOLE")
public class AppTempl extends Application {
    public static void main(String[] args) {
        launch(args);
    }
    public void start(Stage theStage)
    {
        Group root = new Group();
        Scene theScene = new Scene( root );
        theStage.setScene( theScene );

        theStage.show();
    }
}
