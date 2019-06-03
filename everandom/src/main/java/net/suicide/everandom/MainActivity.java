package net.suicide.everandom;

import android.app.ListActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MainActivity extends ListActivity
{
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        //
         Warehouse wh = new Warehouse(this);
         wh.insert("", "apple");
         wh.insert("", "peer");
         wh.insert("", "strawberry");

        List<Map<String, Object>> list = null;
        try {
            list = wh.random();
        } catch (IOException e) {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG);
            Log.e(this.getClass().getSimpleName(), "Query Random fail.", e);
            return;
        }
        List<String> arr = new ArrayList();
         for(int i=0; i< list.size(); i++){
            arr.add((String) list.get(i).get("content"));
         }
         ListView lv = (ListView)findViewById(R.id.list);
        lv.setAdapter(new NotesAdapter(this, (String[]) arr.toArray(), list));
    }
}
