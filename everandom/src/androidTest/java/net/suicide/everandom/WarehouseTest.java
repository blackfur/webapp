package net.suicide.everandom;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import android.util.Log;
import org.junit.Test;
import org.junit.runner.RunWith;

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
}
