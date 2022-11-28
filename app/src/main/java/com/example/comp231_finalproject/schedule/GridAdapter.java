package com.example.comp231_finalproject.schedule;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.comp231_finalproject.R;

public class GridAdapter extends BaseAdapter {

    Context context;
    String[] weekdays;

    LayoutInflater inflater;

    public GridAdapter(Context context, String[] weekdays) {
        this.context = context;
        this.weekdays = weekdays;
    }

    @Override
    public int getCount() {
        return weekdays.length;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        if (inflater ==null)
            inflater=(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if (view ==null){
            view =inflater.inflate(R.layout.schedule_grid_item, null);

        }

        TextView textView =view.findViewById(R.id.item_name);
        textView.setText(weekdays[i]);
        
        return view;
    }
}
