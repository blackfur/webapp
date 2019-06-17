package sample;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginView extends BaseView{

    public LoginView(Stage primaryStage) {
        super(primaryStage);
    }

    @Override
    Scene decorate() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/login.fxml"));
        this.scene = new Scene(root,350,150);
        return scene;
    }
}
