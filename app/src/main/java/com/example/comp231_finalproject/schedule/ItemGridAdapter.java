package com.example.comp231_finalproject.schedule;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.comp231_finalproject.R;

public class ItemGridAdapter  extends BaseAdapter {

    Context context;
    String[] item;
    String[] startTime;
    String[] dash;
    String[] endTime;
    String[] className;

    LayoutInflater inflater;

    public ItemGridAdapter(Context context,  String[] startTime,String[] dash, String[] endTime, String[] className) {
        this.context = context;
        this.startTime = startTime;
        this.endTime = endTime;
        this.className = className;
        this.dash =dash;
     //   this.item = item;
    }

    @Override
    public int getCount() {
        return className.length;
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
            view =inflater.inflate(R.layout.schedule_item, null);

        }

        TextView txtstartTime =view.findViewById(R.id.txt_startTime);
        txtstartTime.setText(startTime[i]);

        TextView txtendTime =view.findViewById(R.id.txt_endTime);
        txtendTime.setText(endTime[i]);

        TextView txtDash =view.findViewById(R.id.textdash);
        txtDash.setText(dash[i]);

        TextView txtClassName =view.findViewById(R.id.txt_className);
        txtClassName.setText(className[i]);

        return view;
    }
}
