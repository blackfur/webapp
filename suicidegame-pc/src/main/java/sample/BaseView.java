package sample;

import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.Map;

public abstract class BaseView {
    Scene scene = null;
    Stage primaryStage;

    public BaseView(Stage primaryStage){
        this.primaryStage = primaryStage;
        Class clss = getClass();
        putView(this);
    }
   abstract Scene decorate() throws IOException;

    public void display() throws IOException {
        if(null == scene) {
            scene = decorate();
        }
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    private static Map<String, BaseView> views = new HashMap<>();
    public static void display(Class clss, Stage primaryStage) throws Exception {
        String fullName = clss.getCanonicalName();
        BaseView v = views.get(fullName);
        if(null == v){
            //throw new Exception("View not found.");
            Class<?> clazz = Class.forName(fullName);
            Constructor<?> constructor = clazz.getConstructor(Stage.class);
            BaseView instance = (BaseView) constructor.newInstance(primaryStage);
            putView(instance);
            instance.display();
            return;
        }
        v.display();
    }

    public static void putView(BaseView view){
        Class clazz = view.getClass();
        String fullName = clazz.getCanonicalName();
        views.put(fullName, view);
    }
}
