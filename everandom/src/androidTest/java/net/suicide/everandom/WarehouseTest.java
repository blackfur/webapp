package net.suicide.everandom;

import android.content.Context;
import android.os.Environment;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import android.util.Log;
import org.json.JSONException;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.File;
import java.io.IOException;
import java.util.Map;

import static org.junit.Assert.assertEquals;

@RunWith(AndroidJUnit4.class)
public class WarehouseTest {
    final String Tag = getClass().getSimpleName();
    @Test
    public void sample() {
        Context appContext = InstrumentationRegistry.getTargetContext();
        Warehouse wh = new Warehouse(appContext);
        wh.insert("apple", "apple");
        wh.insert("", "peer");
        wh.insert("", "strawberry");
        // Context of the app under test.
        //Log.d(getClass().getSimpleName(), "Sample Android Test.");

        Log.v(getClass().getSimpleName(),wh.selectOne("apple").get("content").toString());
       // assertEquals("net.suicide.everandom", appContext.getPackageName());
    }
    @Test
    public void should_Saved(){
        Context appContext = InstrumentationRegistry.getTargetContext();
        Warehouse wh = new Warehouse(appContext);

        wh.insert("apple", "apple");
        Map<String, Object> apple = wh.selectOne("apple");
        Log.i(Tag, "Before Saved: " + apple.get("content").toString());

        apple.put("content", "pineapple");
        wh.save(apple);
        apple = wh.selectOne("apple");
        String appletxt = apple.get("content").toString();
        Log.i(Tag, "After Saved: " + appletxt);

        assertEquals(appletxt, "pineapple");
    }

    @Test
    public void should_ListFiles(){
        String path = Environment.getExternalStorageDirectory().toString();

        //Context appContext = InstrumentationRegistry.getTargetContext();
        //String path = "/data/data/" + appContext.getPackageName() + "/database/";

        Log.d("Files", "Path: " + path);
        File directory = new File(path);
        File[] files = directory.listFiles();
        if(null == files){
            return;
        }
        Log.d("Files", "Size: "+ files.length);
        for (int i = 0; i < files.length; i++)
        {
            Log.d("Files", "FileName:" + files[i].getName());
        }
    }

    @Test
    public void should_exportBookshelf() throws IOException, JSONException {

        Context appContext = InstrumentationRegistry.getTargetContext();
        Warehouse wh = new Warehouse(appContext);
        wh.insert("apple", "apple");
        wh.insert("pear", "pear");
        final File bookshelf= new File(Environment.getExternalStorageDirectory().getAbsolutePath()+"/" + appContext.getPackageName(), "bookshelf");
        wh.export(bookshelf, null);
    }
    @Test
    public void should_restoreBookshelf() throws IOException, JSONException {

        Context appContext = InstrumentationRegistry.getTargetContext();
        Warehouse wh = new Warehouse(appContext);
        final File bookshelf= new File(Environment.getExternalStorageDirectory().getAbsolutePath()+"/" + appContext.getPackageName(), "bookshelf");
        wh.restore(bookshelf, null);
    }
}
