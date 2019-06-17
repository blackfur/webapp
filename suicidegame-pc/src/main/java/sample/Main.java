package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.net.URL;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class Main extends Application {
    Log log = LogFactory.getLog(getClass());

    @Override
    public void start(Stage primaryStage) throws Exception{

        URL resource = getClass().getResource("/sample.fxml");
        if(null == resource){
            String errmsg = "file Not found.";
            log.error(errmsg);
            throw new Exception(errmsg);
        }
        Parent root = FXMLLoader.load(resource);
        primaryStage.setTitle("Welcome to Heaven.");
        primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.show();
        log.info("load Success.");
    }


    public static void main(String[] args) {
        launch(args);
    }
}
