package net.suicide.everandom;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.EditText;
import android.widget.Toast;

public class InsertActivity extends Activity {

    Warehouse warehouse;
    EditText input;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert);

        warehouse = new Warehouse(this);

        input = findViewById(R.id.editText);
    }
    @Override
    public void onBackPressed() {
        final String inputtxt = input.getText().toString();
        new AlertDialog.Builder(this)
                .setTitle("Save")
                .setMessage("Are you sure you want to Save this entry?")
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        if(TextUtils.isEmpty(inputtxt)){
                            Toast.makeText(getApplicationContext(), "Should not be blank.", Toast.LENGTH_LONG);
                        }else{
                            warehouse.insert("", inputtxt);
                            Toast.makeText(getApplicationContext(), "Saved.", Toast.LENGTH_LONG);
                            InsertActivity.super.onBackPressed();
                        }
                    }
                })
                .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        InsertActivity.super.onBackPressed();
                    }
                })
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
    }
}
