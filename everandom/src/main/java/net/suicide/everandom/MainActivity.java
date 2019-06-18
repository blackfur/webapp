package net.suicide.everandom;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MainActivity extends Activity
{
    Warehouse wh;
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        //
         wh = new Warehouse(this);

        List<Map<String, Object>> list;
        try {
            list = wh.random();
        } catch (IOException e) {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG);
            Log.e(this.getClass().getSimpleName(), "Query Random fail.", e);
            return;
        }

        String[] strings = new String[list.size()];
         for(int i=0; i< list.size(); i++){
            strings[i] = (String)list.get(i).get("content");
         }
         ListView lv = findViewById(R.id.list);
        lv.setAdapter(new NotesAdapter(this, strings, list));
    }
}
