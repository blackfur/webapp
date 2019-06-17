package sample;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.effect.BlendMode;
import javafx.scene.effect.BoxBlur;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.StrokeType;
import javafx.stage.Stage;
import javafx.util.Duration;
import lombok.extern.apachecommons.CommonsLog;

import static java.lang.Math.random;
import static sample.SuicideUtil.img;

@CommonsLog(topic = "CONSOLE")
public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    public void start(Stage primaryStage) throws Exception {
        Group root = new Group();
        Scene scene = new Scene(root, 400, 300, Color.BLACK);
        primaryStage.setScene(scene);

        primaryStage.getIcons().add(img("/Tridactyl_100px.png"));

        menus(root, primaryStage);

        primaryStage.show();
    }

    static void menus(Group root, Stage primaryStage){
        MenuBar menuBar = new MenuBar();
        menuBar.prefWidthProperty().bind(primaryStage.widthProperty());
        root.getChildren().add(menuBar);

        // File menu - new, save, exit
        Menu fileMenu = new Menu("_File");
        MenuItem newMenuItem = new MenuItem("_New");
        MenuItem saveMenuItem = new MenuItem("_Save");
        saveMenuItem.setMnemonicParsing(true);
        saveMenuItem.setAccelerator(new KeyCodeCombination(KeyCode.S, KeyCombination.SHORTCUT_DOWN));
        MenuItem exitMenuItem = new MenuItem("_Exit");
        exitMenuItem.setOnAction(actionEvent -> Platform.exit());

        fileMenu.getItems().addAll(newMenuItem, saveMenuItem,
                new SeparatorMenuItem(), exitMenuItem);

        Menu webMenu = new Menu("_Web");
        CheckMenuItem htmlMenuItem = new CheckMenuItem("_HTML");
        htmlMenuItem.setSelected(true);
        webMenu.getItems().add(htmlMenuItem);

        CheckMenuItem cssMenuItem = new CheckMenuItem("_CSS");
        cssMenuItem.setSelected(true);
        webMenu.getItems().add(cssMenuItem);

        Menu sqlMenu = new Menu("_SQL");
        ToggleGroup tGroup = new ToggleGroup();
        RadioMenuItem mysqlItem = new RadioMenuItem("_MySQL");
        mysqlItem.setToggleGroup(tGroup);

        RadioMenuItem oracleItem = new RadioMenuItem("_Oracle");
        oracleItem.setToggleGroup(tGroup);
        oracleItem.setSelected(true);

        sqlMenu.getItems().addAll(mysqlItem, oracleItem,
                new SeparatorMenuItem());

        Menu tutorialManeu = new Menu("_Tutorial");
        tutorialManeu.getItems().addAll(
                new CheckMenuItem("_Java"),
                new CheckMenuItem("Java_FX"),
                new CheckMenuItem("_Swing"));

        sqlMenu.getItems().add(tutorialManeu);

        menuBar.getMenus().addAll(fileMenu, webMenu, sqlMenu);

        // Context Menus
        //ContextMenu  contextFileMenu = new ContextMenu(fileMenu);
        /*
        primaryStage.addEventHandler(MouseEvent.MOUSE_CLICKED,  (MouseEvent  me) ->  {
            if (me.getButton() == MouseButton.SECONDARY  || me.isControlDown())  {
                contextFileMenu.show(root, me.getScreenX(), me.getScreenY());
            }  else  {
                contextFileMenu.hide();
            }
        });
         */
    }
}
