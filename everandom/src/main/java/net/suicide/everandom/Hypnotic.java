package net.suicide.everandom;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import java.io.*;
import java.util.Properties;

public class Hypnotic {
   static Properties properties;
    public static String prop(String key,Context context) throws IOException {
        loadProp(context);
      return properties.getProperty(key);
    }
    public static void prop(String key, String value,Context context) throws IOException {
        loadProp(context);
        properties.setProperty(key, value);
    }
    static void loadProp(Context context) throws IOException{
        if(null == properties){
            properties = new Properties();;
            AssetManager assetManager = context.getAssets();
            InputStream inputStream = assetManager.open("app.prop");
            properties.load(inputStream);
        }
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

    public static void toast(final Activity ctx, final String msg){
        ctx.runOnUiThread(new Runnable() {
            @Override
            public void run() {

                Toast.makeText(ctx, msg, Toast.LENGTH_LONG).show();
            }
        });
    }
    public static void alert(final Activity ctx, final String msg){
        ctx.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                new AlertDialog.Builder(ctx) .setMessage(msg) .setIcon(android.R.drawable.ic_dialog_alert) .show();
            }
        });
    }

    public static void copy(File src, File destination) throws Exception {
        FileInputStream input = null;
        FileOutputStream output = null;
        try{
            if(!destination.exists()){
                new File(destination.getParent()).mkdirs();
                destination.createNewFile();
            }

            input = new FileInputStream(src);
            output= new FileOutputStream(destination);
            byte[] buffer = new byte[1024];
            int size;
            while ( (size= input.read(buffer)) > 0) {
                output.write(buffer, 0, size);
            }
            output.flush();
            input.close();
            output.close();
        } catch (IOException e) {
            String err = "Can't access: \n"
                    + destination.getAbsolutePath()
                    + "\n" + src.getAbsolutePath()
                    + "\n" +e.getMessage();
            Log.e("Copy file: ", err, e);
            throw new Exception(err);
        }finally {
            if(null != input) {
                try {
                    input.close();
                } catch (IOException e) {
                }
            }

            if(null != output) {
                try {
                    output.close();
                } catch (IOException e) {
                }
            }
        }
    }

}
