package net.suicide.everandom;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.content.FileProvider;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.microsoft.onedrivesdk.saver.ISaver;
import com.microsoft.onedrivesdk.saver.Saver;
import com.microsoft.onedrivesdk.saver.SaverException;

import java.io.File;
import java.io.IOException;

import static net.suicide.everandom.Hypnotic.prop;

public class UploadActivity extends FreakActivity{

    private ISaver mSaver;
    String ONEDRIVE_APP_ID = null;
    TextView logtxt;
    ProgressBar progress;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload);

        logtxt = findViewById(R.id.log);
        progress = findViewById(R.id.progress);

        try {
            ONEDRIVE_APP_ID = prop("onedrive.appid",scope());
            // Create the picker instance
            mSaver = Saver.createSaver(ONEDRIVE_APP_ID);

            //Uri bookshelfUri = Uri.parse("file://" + prop("bookshelf.file", this));
            File bookshelf = new File(prop("bookshelf.file", this));
            Uri bookshelfUri = Uri.fromFile(bookshelf);
            logtxt.append("\n\n" + bookshelfUri.toString());
            progress.setVisibility(View.VISIBLE);

            mSaver.startSaving(scope(), "bookshelf", bookshelfUri);
            /*
            Intent uploadReq= new Intent(Intent.ACTION_SEND)
            //.setType("text/*")
            .setPackage("com.microsoft.skydrive")
            .setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
            .putExtra(Intent.EXTRA_STREAM, FileProvider.getUriForFile(this, getClass().getCanonicalName(), bookshelf));
            startActivity(Intent.createChooser(uploadReq, Intent.ACTION_SEND));
             */
        } catch (IOException e) {
            String errmsg = "\n\nMissing onedrive.appid in assets/app.prop: " + e.getMessage();
            Log.e(getLocalClassName(), errmsg, e);
            logtxt.setText(errmsg);
        }

    }

    @Override
    protected void onActivityResult(final int requestCode, final int resultCode, final Intent data) {
        progress.setVisibility(View.GONE);
        // Check that we were able to save the file on OneDrive
        try {
            mSaver.handleSave(requestCode, resultCode, data);
            logtxt.append("\n\nSaved Successfully | bookshelf | " + requestCode + " | " + resultCode + " | ");
        } catch (final SaverException e) {
            Log.e(getLocalClassName(), e.getMessage(),e);
            logtxt.append("\n\n" + e.getErrorType().toString());
            Log.e(getLocalClassName(), e.getErrorType().toString());
            logtxt.append("\n\n" +e.getDebugErrorInfo());
            Log.e(getLocalClassName(), e.getDebugErrorInfo());
        }
    }


}
