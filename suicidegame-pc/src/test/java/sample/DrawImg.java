package sample;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import lombok.extern.apachecommons.CommonsLog;

import java.net.URL;

@CommonsLog(topic = "CONSOLE")
public class DrawImg extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage theStage) throws Exception {

        //theStage.setTitle( "Canvas Example" );

        Group root = new Group();
        Scene theScene = new Scene( root );
        theStage.setScene( theScene );

        Canvas canvas = new Canvas( 400, 200 );
        root.getChildren().add( canvas );

        GraphicsContext gc = canvas.getGraphicsContext2D();

        gc.setFill( Color.RED );
        gc.setStroke( Color.BLACK );
        gc.setLineWidth(2);
        Font theFont = Font.font( "Times New Roman", FontWeight.BOLD, 48 );
        gc.setFont( theFont );
        //gc.fillText( "Potter!", 60, 50 );
        //gc.strokeText( "Help!", 60, 50 );

        String picture = "/Tridactyl_100px.png";
        URL resource = getClass().getResource(picture);
        if(null == resource){
            String errmsg = "load png fail: " + picture;
            log.error(errmsg);
            throw new Exception(errmsg);
        }
        Image earth = new Image(resource.toString());
        gc.drawImage( earth, 180, 100 );

        theStage.show();
    }
}
