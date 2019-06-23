package net.suicide.everandom;

import android.app.DownloadManager;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.widget.TextView;
import com.microsoft.onedrivesdk.picker.IPicker;
import com.microsoft.onedrivesdk.picker.IPickerResult;
import com.microsoft.onedrivesdk.picker.LinkType;
import com.microsoft.onedrivesdk.picker.Picker;

import java.io.File;
import java.io.IOException;

import static net.suicide.everandom.Hypnotic.copy;
import static net.suicide.everandom.Hypnotic.prop;

public class SyncActivity extends FreakActivity{

    private IPicker mPicker;
    String ONEDRIVE_APP_ID = null;
    TextView logtxt;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sync);

        logtxt = findViewById(R.id.log);

        if(null == savedInstanceState){
            try {
                logtxt.append("\nto Pick bookshelf file...");

                ONEDRIVE_APP_ID = prop("onedrive.appid",scope());
                mPicker = Picker.createPicker(ONEDRIVE_APP_ID);
                mPicker.startPicking(scope(), LinkType.DownloadLink);

            } catch (IOException e) {
                String errmsg = "Missing onedrive.appid: " + e.getMessage();
                Log.e(getLocalClassName(), errmsg, e);
                logtxt.append("\n" + errmsg);
            }
        }
    }

    @Override
    protected void onActivityResult(final int requestCode, final int resultCode, final Intent data) {
        // Get the results from the from the picker
        final IPickerResult result = mPicker.getPickerResult(requestCode, resultCode, data);
        // Handle the case if nothing was picked
        if (result == null) {
            logtxt.append("\n\nDid not get a file from the picker!");
            return;
        }
        String fileMsg = result.getName()
                + " | " + result.getLinkType()
                + " | " + result.getLink()
                + " | " + result.getSize()
                + " | " + result.getThumbnailLinks().get("small")
                + " | " + result.getThumbnailLinks().get("medium")
                + " | " + result.getThumbnailLinks().get("large");
        Log.i(getLocalClassName(), fileMsg);
        logtxt.append("\n\n" + fileMsg);

        // backup local bookshelf
        try {
            String filepath = prop("bookshelf.file", scope());
            File src  = new File(filepath);
            File bookshelf= new File(Environment.getExternalStorageDirectory().getAbsolutePath()+"/" + getPackageName(), "bookshelf");
            copy(src, bookshelf);
        } catch (IOException e) {
            String err = "Missing bookshelf.file property: " + e.getMessage();
            logtxt.append("\n\n" + err);
            return;
        } catch (Exception e) {
            logtxt.append("\n\n" + e.getMessage());
            return;
        }

        // download then overwrite local data
        final DownloadManager downloadManager = (DownloadManager) getSystemService(DOWNLOAD_SERVICE);
        final DownloadManager.Request request = new DownloadManager.Request(result.getLink());
        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
        downloadManager.enqueue(request);


    }

}
