package net.suicide.everandom;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;
import java.util.Map;

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
        View rowView=inflater.inflate(R.layout.list_item, null,true);

        TextView content = (TextView) rowView.findViewById(R.id.content);

        content.setText((String)list.get(position).get("content"));

        return rowView;

    };
}
