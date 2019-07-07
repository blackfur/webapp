package net.suicide.everandom;

import android.os.Bundle;
import android.os.Environment;
import android.support.v4.util.Consumer;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;

public class ReplicaActivity extends FreakActivity{

    TextView logtxt;
    ProgressBar progress;
    Warehouse warehouse;
    Consumer onerror;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_replica);

        logtxt = findViewById(R.id.log);
        progress = findViewById(R.id.progress);

        warehouse = new Warehouse(this);

        onerror = new Consumer() {
            @Override
            public void accept(Object o) {
                int code = -1;
                String message = "";
                if(o instanceof JSONObject){
                    JSONObject error = (JSONObject)o;
                    try {
                        code = error.getInt("code");
                        message = error.getString("message");
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                final String msg = message;
                runOnUiThread(new Runnable() {
                    public void run() {
                        logtxt.append(msg);
                    }
                });
            }
        };

        if(null == savedInstanceState){
            final File bookshelf= new File(Environment.getExternalStorageDirectory().getAbsolutePath()+"/" + getPackageName(), "bookshelf");
            progress.setVisibility(View.VISIBLE);
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        warehouse.export(bookshelf, onerror);
                        appendlogtxt("\n\nCopied to: " + bookshelf.getAbsolutePath());
                    }  catch (Exception e) {
                        appendlogtxt("\n\n" + e.getMessage());
                        return;
                    }finally {
                        dimiss();
                    }
                }
            }).start();
        }
    }
    void appendlogtxt(final String msg){
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                logtxt.append(msg);
            }
        });
    }
    void dimiss(){
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                progress.setVisibility(View.GONE);
            }
        });
    }

}
