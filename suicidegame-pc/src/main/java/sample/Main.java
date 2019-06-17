package sample;

import javafx.application.Application;
import javafx.stage.Stage;
import lombok.extern.apachecommons.CommonsLog;

import static sample.SuicideUtil.img;

@CommonsLog(topic = "CONSOLE")
public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    public void start(Stage primaryStage) throws Exception {

        Roof roof = new Roof(primaryStage);
        //LoginView loginView = new LoginView(primaryStage);

        primaryStage.getIcons().add(img("/Tridactyl_100px.png"));

        roof.display();
    }

}
