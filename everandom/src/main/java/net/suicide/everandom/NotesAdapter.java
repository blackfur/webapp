package net.suicide.everandom;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static net.suicide.everandom.Hypnotic.go;
import static net.suicide.everandom.Warehouse.KEY_ID;

public class NotesAdapter extends BaseAdapter {

    final Activity context;

    public void setList(List<Map<String, Object>> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    List<Map<String, Object>> list;

    public NotesAdapter(Activity context, List<Map<String, Object>> list) {
        this.context=context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
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
        if(position%2 == 1)
            content.setTextColor(context.getColor(R.color.pink));
        else
            content.setTextColor(context.getColor(R.color.white));
        content.setText(item.get("content").toString());
        content.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Object id = item.get(KEY_ID);
                Bundle bundle = new Bundle();
                bundle.putString(KEY_ID, id.toString());
                go(context, EditorActivity.class, bundle);
            }
        });

        return rowView;

    }
}
