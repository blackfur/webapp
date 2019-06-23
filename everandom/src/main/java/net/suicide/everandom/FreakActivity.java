package net.suicide.everandom;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

import static android.content.pm.PackageManager.GET_PERMISSIONS;
import static net.suicide.everandom.Hypnotic.alert;

public class FreakActivity extends Activity {

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // permissions
        String[] permissions = {Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE};
        List<String> denied = new ArrayList<>();
        for(int i=0; i< permissions.length; i++){
            int granted = checkSelfPermission(permissions[i]);
            if(granted == PackageManager.PERMISSION_GRANTED) {
                continue;
            }
            denied.add(permissions[i]);
        }
        if(denied.size() >0){
            String[] tmp = denied.toArray(new String[denied.size()]);
            requestPermissions(tmp,GET_PERMISSIONS);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        if (requestCode == GET_PERMISSIONS) {
            if (grantResults.length == 1 || grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                alert(scope(), "Permissions denied!");
            }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    protected Activity scope(){
        return this;
    }
}
