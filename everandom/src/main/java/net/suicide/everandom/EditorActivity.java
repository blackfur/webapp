package net.suicide.everandom;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Map;

import static net.suicide.everandom.Warehouse.KEY_CONTENT;
import static net.suicide.everandom.Warehouse.KEY_ID;

public class EditorActivity extends Activity {
    EditText input;
    Warehouse warehouse;
    Map<String, Object> item;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.editor);

        warehouse = new Warehouse(this);

        input = findViewById(R.id.editText);
    }
    protected void onStart() {
        String tmp = input.getText().toString();
        if(TextUtils.isEmpty(tmp)){
            Bundle extras = getIntent().getExtras();
            String id = extras.getString(KEY_ID);
            item = warehouse.selectOne(Integer.valueOf(id));
            Object content = item.get(KEY_CONTENT);
            input.setText(content.toString());
        }
        super.onStart();
    }

    @Override
    public void onBackPressed() {
        final String inputtxt = input.getText().toString();
        if(TextUtils.isEmpty(inputtxt)){
            Toast.makeText(this, "Should not be blank.", Toast.LENGTH_LONG).show();
        }else{
            new AlertDialog.Builder(this)
                    .setTitle("Save")
                    .setMessage("Are you sure you want to Save this entry?")
                    .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            item.put(KEY_CONTENT, inputtxt);
                            warehouse.save(item);
                            Toast.makeText(getApplicationContext(), "Saved.", Toast.LENGTH_LONG).show();
                            EditorActivity.super.onBackPressed();
                        }
                    })
                    .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            EditorActivity.super.onBackPressed();
                        }
                    })
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .show();
        }
    }
}
