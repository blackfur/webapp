package net.suicide.everandom;

import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.RequiresApi;
import android.support.v4.util.Consumer;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;

public class RebornActivity extends FreakActivity{

    TextView logtxt;
    ProgressBar progress;
    Warehouse warehouse;
    Consumer onerror;

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reborn);

        logtxt = findViewById(R.id.log);
        progress = findViewById(R.id.progress);

        warehouse = new Warehouse(this);

        onerror = new Consumer() {
            public void accept(Object o) {
                int code = -1;
                String message = "";
                if(o instanceof JSONObject){
                    JSONObject error = (JSONObject)o;
                    try {
                        code = error.getInt("code");
                        message = error.getString("message");
                    } catch (JSONException e) {
                        Log.e(getClass().getCanonicalName(), "Read Response fail: ",e );
                    }
                }
                final String msg = "\n" + message;
                runOnUiThread(new Runnable() {
                    public void run() {
                        logtxt.append(msg);
                    }
                });
            }
        };

        if(null == savedInstanceState){
            final File src  = new File(Environment.getExternalStorageDirectory().getAbsolutePath()+"/" + getPackageName(), "bookshelf");
            logtxt.append("\n\nto Copy bookshelf file: " + src.getAbsolutePath());

            progress.setVisibility(View.VISIBLE);
            new Thread(new Runnable() {
                public void run() {
                    try {
                        warehouse.restore(src, onerror);
                        appendlogtxt("\n\nCopied: " + src.getAbsolutePath());
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
