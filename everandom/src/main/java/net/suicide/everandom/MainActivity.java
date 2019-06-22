package net.suicide.everandom;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;
import com.getbase.floatingactionbutton.FloatingActionButton;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import static net.suicide.everandom.Hypnotic.go;
import static net.suicide.everandom.Hypnotic.toast;

import android.view.View.OnClickListener;
import com.microsoft.onedrivesdk.picker.*;

public class MainActivity extends FreakActivity
{
    Warehouse wh;
    FloatingActionButton insertButton;
    FloatingActionButton randomButton;
    FloatingActionButton syncBtn;
    ProgressBar progress;

   private IPicker mPicker;
     String ONEDRIVE_APP_ID = null;


    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

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


        try {
            ONEDRIVE_APP_ID = Hypnotic.prop("onedrive.appid",scope());
            syncBtn = findViewById(R.id.sync);
            syncBtn.setOnClickListener(new View.OnClickListener() {
                public void onClick(final View v) {
                    mPicker = Picker.createPicker(ONEDRIVE_APP_ID);
                    mPicker.startPicking(scope(), LinkType.DownloadLink);
                }
            });
        } catch (IOException e) {
            Log.e(getLocalClassName(), "Missing onedrive.appid: " + e);
        }
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
                String[] strings = new String[list.size()];
                for(int i=0; i< list.size(); i++){
                    strings[i] = (String)list.get(i).get("content");
                }
                ListView lv = findViewById(R.id.list);
                lv.setAdapter(new NotesAdapter(scope(), strings, list));

            }
        });

    }

    @Override
    protected void onActivityResult(final int requestCode, final int resultCode, final Intent data) {
        // Get the results from the from the picker
        final IPickerResult result = mPicker.getPickerResult(requestCode, resultCode, data);
        Log.i(getLocalClassName(), result.getName()
                + " | " + result.getLinkType()
                + " | " + result.getLink()
                + " | " + result.getSize()
                + " | " + result.getThumbnailLinks().get("small")
                + " | " + result.getThumbnailLinks().get("medium")
                + " | " + result.getThumbnailLinks().get("large")
        );

        // Handle the case if nothing was picked
        if (result == null) {
            Toast.makeText(this, "Did not get a file from the picker!", Toast.LENGTH_LONG).show();
            return;
        }
    }


}
