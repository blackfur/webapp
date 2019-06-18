package net.suicide.everandom;
import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.os.Bundle;

import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.Properties;

public class Hypnotic {
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
    public static void go(Context ctx, Class clazz, Bundle parameters){
        Intent i = new Intent(ctx, clazz);
        if(null != parameters)
            i.putExtras(parameters);
        ctx.startActivity(i);
    }

    public static void go(Context ctx, Class clazz){
        go(ctx, clazz, null);
    }

}
