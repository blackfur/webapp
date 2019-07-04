package net.suicide.everandom;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.ProgressBar;
import com.getbase.floatingactionbutton.FloatingActionButton;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static net.suicide.everandom.Hypnotic.go;
import static net.suicide.everandom.Hypnotic.toast;

public class MainActivity extends FreakActivity
{
    Warehouse wh;
    FloatingActionButton insertButton;
    FloatingActionButton randomButton;
    ProgressBar progress;
    NotesAdapter adapter;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        adapter = new NotesAdapter(scope(), new String[]{}, new ArrayList<Map<String, Object>>());
        ListView lv = findViewById(R.id.list);
        lv.setAdapter(adapter);

        progress = findViewById(R.id.pBar);

        //
         wh = new Warehouse(this);

         progress.setVisibility(View.VISIBLE);

        insertButton = findViewById(R.id.insert);
        insertButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               go(scope(), InsertActivity.class);
            }
        });


        randomButton = findViewById(R.id.random);
        randomButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               random();
            }
        });

        anchor(R.id.reborn, RebornActivity.class);
        anchor(R.id.replica, ReplicaActivity.class);
    }

   void anchor(int resid, final Class clazz){
       findViewById(resid).setOnClickListener(new View.OnClickListener() {
           public void onClick(View v) {
               go(scope(), clazz);
           }
       });
   }

    @Override
    protected void onStart(){
        super.onStart();
        random();
    }
    void random(){

        new Thread(new Runnable() {
            public void run() {
                try {
                    final List<Map<String, Object>>list = wh.random();
                    if(list.size() == 0){
                        toast(scope(), "Nothing!");
                        // Nothing but still inflate!
                    }
                    inflateList(list);
                } catch (final IOException e) {
                    toast(scope(), e.getMessage());
                    Log.e(this.getClass().getSimpleName(), "Query Random fail.", e);
                    return;
                }
            }
        }).start();

    }

    void inflateList(final List<Map<String, Object>> list){
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                progress.setVisibility(View.GONE);
                adapter.setList(list);

            }
        });

    }

}
