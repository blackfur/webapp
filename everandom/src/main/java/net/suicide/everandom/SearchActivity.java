package net.suicide.everandom;

import android.os.Bundle;
import android.support.v4.util.Consumer;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ProgressBar;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SearchActivity extends FreakActivity
{
    Warehouse wh;
    EditText box;
    NotesAdapter adapter;
    Consumer onerror;
    ProgressBar progress;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search);

        //
         wh = new Warehouse(this);

        adapter = new NotesAdapter(scope(), new ArrayList<Map<String, Object>>());
        ListView lv = findViewById(R.id.list);
        lv.setAdapter(adapter);

        progress = findViewById(R.id.progress);

        box = findViewById(R.id.words);
        box.addTextChangedListener(new AfterTxtChanged() {

            public void afterTextChanged(Editable s) {
                String words = s.toString();
                if(words.length() == 0){
                    List<Map<String, Object>> list = new ArrayList<>();
                    adapter.setList(list);
                    return;
                }

                progress.setVisibility(View.VISIBLE);
                wh.search(words, onerror);
            }
        });

        onerror = new Consumer() {
            public void accept(final Object o) {
                runOnUiThread(new Runnable() {
                    public void run() {
                        progress.setVisibility(View.GONE);

                        List<Map<String, Object>> list = null;
                        if(o instanceof JSONObject){
                            JSONObject error = (JSONObject) o;
                            try {
                                list = (List<Map<String, Object>>) error.get("payload");
                            } catch (JSONException e) {
                                Log.e(getClass().getCanonicalName(), "Get payload fail: ", e);
                                return;
                            }
                        }
                        if(null == list){
                            return;
                        }
                        adapter.setList(list);
                    }
                });
            }
        };
    }

}
