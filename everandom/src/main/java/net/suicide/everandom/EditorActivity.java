package net.suicide.everandom;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import com.getbase.floatingactionbutton.FloatingActionButton;

import java.util.Map;

import static net.suicide.everandom.Warehouse.KEY_CONTENT;
import static net.suicide.everandom.Warehouse.KEY_ID;

public class EditorActivity extends FreakActivity {
    EditText input;
    Warehouse warehouse;
    Map<String, Object> item;
    FloatingActionButton delButton;
    int id;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.editor);

        warehouse = new Warehouse(this);

        input = findViewById(R.id.editText);

       // String tmp = input.getText().toString();
        //if(TextUtils.isEmpty(tmp)){
            Bundle extras = getIntent().getExtras();
            String idtxt = extras.getString(KEY_ID);
            id =  Integer.valueOf(idtxt);
            item = warehouse.selectOne(id);
            if(null == item){
                return;
            }
            Object content = item.get(KEY_CONTENT);
            input.setText(content.toString());
        //}

        delButton = findViewById(R.id.delete);
        delButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new AlertDialog.Builder(scope())
                        .setTitle("Delete")
                        .setMessage("Are you sure you want to delete this entry?")
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                warehouse.delete(id);
                                Toast.makeText(scope(), "deleted.", Toast.LENGTH_LONG).show();
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
        });
    }
    /*

    protected void onStart() {
        super.onStart();
    }
     */

    @Override
    public void onBackPressed() {
        if(null == item) {
            super.onBackPressed();
            return;
        }
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
