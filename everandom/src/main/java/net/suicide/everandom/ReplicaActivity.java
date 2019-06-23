package net.suicide.everandom;

import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.io.File;
import java.io.IOException;

import static net.suicide.everandom.Hypnotic.copy;
import static net.suicide.everandom.Hypnotic.prop;

public class ReplicaActivity extends FreakActivity{

    TextView logtxt;
    ProgressBar progress;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_replica);

        logtxt = findViewById(R.id.log);
        progress = findViewById(R.id.progress);

        if(null == savedInstanceState){
            // backup local bookshelf
            String filepath = null;
            try {
                filepath = prop("bookshelf.file", scope());
            } catch (IOException e) {
                String err = "Missing bookshelf.file property: " + e.getMessage();
                logtxt.append("\n\n" + err);
                return;
            }
            logtxt.append("\n\nto Copy bookshelf file: " + filepath);

            final File src  = new File(filepath);
            final File bookshelf= new File(Environment.getExternalStorageDirectory().getAbsolutePath()+"/" + getPackageName(), "bookshelf");
            progress.setVisibility(View.VISIBLE);
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        copy(src, bookshelf);
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
