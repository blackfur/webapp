package net.suicide.everandom;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import android.util.Log;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertEquals;

@RunWith(AndroidJUnit4.class)
public class WarehouseTest {
    @Test
    public void sample() throws Exception {
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
}
