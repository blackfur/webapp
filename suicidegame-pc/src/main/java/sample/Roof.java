package sample;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Roof extends BaseView{
    public Roof(Stage primaryStage){
        super(primaryStage);
    }
    @Override
    Scene decorate() {
        Group root = new Group();
        Menus.mount(root, primaryStage);
        Scene scene = new Scene(root, 400, 300, Color.BLACK);
        return scene;
    }
}
