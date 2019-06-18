package net.suicide.everandom;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static net.suicide.everandom.Hypnotic.go;
import static net.suicide.everandom.Warehouse.KEY_ID;

public class NotesAdapter extends ArrayAdapter<String> {

    final Activity context;
    List<Map<String, Object>> list;

    public NotesAdapter(Activity context,String[] arr, List<Map<String, Object>> list) {
        super(context, R.layout.list_item, arr);
        this.context=context;
        this.list = list;
    }

    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater=context.getLayoutInflater();
        View rowView;

        Map<Integer, View> viewHolder;
        if(null == view){
            rowView=inflater.inflate(R.layout.list_item, null,true);
            viewHolder = new HashMap<>();
            TextView content = rowView.findViewById(R.id.content);
            viewHolder.put(R.id.content, content);
            rowView.setTag(viewHolder);

        }else{
            rowView = view;
           viewHolder = (Map<Integer, View>) view.getTag();
        }


        final Map<String, Object> item = list.get(position);
        TextView content = (TextView) viewHolder.get(R.id.content);
        content.setText((String)item.get("content"));
        content.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Object id = item.get(KEY_ID);
                Bundle bundle = new Bundle();
                bundle.putString(KEY_ID, id.toString());
                go(getContext(), EditorActivity.class, bundle);
            }
        });

        return rowView;

    }
}
