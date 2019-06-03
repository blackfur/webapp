package net.suicide.everandom;
import android.content.Context;
import android.content.res.AssetManager;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Appliance {
   static Properties properties;
    public static String prop(String key,Context context) throws IOException {
       if(null == properties){
         properties = new Properties();;
         AssetManager assetManager = context.getAssets();
         InputStream inputStream = assetManager.open("app.prop");
         properties.load(inputStream);
       }
      return properties.getProperty(key);
    }
}
