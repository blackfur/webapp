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

import static sample.SuicideUtil.img;

@CommonsLog(topic = "CONSOLE")
public class Gameloop extends Application {
    public static void main(String[] args) {
        launch(args);
    }
    public void start(Stage theStage) throws Exception {
        Group root = new Group();
        Scene theScene = new Scene( root );
        theStage.setScene( theScene );

        Canvas canvas = new Canvas( 400, 300);
        root.getChildren().add( canvas );

        final GraphicsContext gc = canvas.getGraphicsContext2D();

        final Image Tridactyl = img( "/Tridactyl_100px.png" );

        final long startNanoTime = System.nanoTime();

        new AnimationTimer()
        {
            public void handle(long currentNanoTime)
            {
                double t = (currentNanoTime - startNanoTime) / 1000000000.0;

                double x = 128 + 64* Math.cos(t);
                double y = 64 + 64* Math.sin(t);

                // background image clears canvas
                gc.drawImage( Tridactyl, x, y );
            }
        }.start();

        theStage.show();
    }
}
