package sample;

import javafx.scene.image.Image;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class SuicideUtil {
    /**
     * Load image file from resources folder
     * @return Image
     */
    public static Image img(String fileName) throws Exception {

        URL resource = SuicideUtil.class.getResource(fileName);
        if(null == resource){
            String errmsg = "load png fail: " + fileName;
            throw new Exception(errmsg);
        }
        return new Image(resource.toString());
    }

}
