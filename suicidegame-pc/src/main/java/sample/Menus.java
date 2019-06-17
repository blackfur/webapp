package sample;

import javafx.application.Platform;
import javafx.scene.Group;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.stage.Stage;
import lombok.extern.apachecommons.CommonsLog;

import static sample.BaseView.display;

@CommonsLog
public class Menus {

    static void mount(Group root, Stage primaryStage){
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

        MenuItem loginMenuItem = new MenuItem("_Login");
        loginMenuItem.setOnAction(actionEvent -> {
            try {
                display(LoginView.class, primaryStage);
            } catch (Exception e) {
                log.error("load view fail: " + LoginView.class.toString(), e);
            }
        });

        fileMenu.getItems().addAll(newMenuItem, saveMenuItem,
                new SeparatorMenuItem(), exitMenuItem, loginMenuItem);

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
