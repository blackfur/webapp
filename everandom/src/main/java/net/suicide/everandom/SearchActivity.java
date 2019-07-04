package net.suicide.everandom;

import android.os.Bundle;
import android.widget.ListView;

import java.util.List;
import java.util.Map;

public class SearchActivity extends FreakActivity
{
    Warehouse wh;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search);

        //
         wh = new Warehouse(this);

    }

    void inflateList(final List<Map<String, Object>> list){
        runOnUiThread(new Runnable() {
            @Override
            public void run() {

            }
        });

    }

}
