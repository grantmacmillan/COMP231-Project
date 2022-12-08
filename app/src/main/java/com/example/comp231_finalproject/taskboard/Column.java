package com.example.comp231_finalproject.taskboard;

import android.content.Context;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.comp231_finalproject.R;
import com.woxthebox.draglistview.DragItem;
import com.woxthebox.draglistview.DragItemRecyclerView;
import com.woxthebox.draglistview.DragListView;

import java.util.ArrayList;


public class Column {
    String name;
    DragListView recyclerView;
    TextView titleTextView;
    ArrayList<TaskModel> tasks;
    TaskAdapter adapter;
    Context context;

    public Column(String name, DragListView recyclerView, TextView titleTextView, Context context, RecyclerViewInterface recyclerViewInterface) {
        this.name = name;
        this.recyclerView = recyclerView;
        this.titleTextView = titleTextView;
        this.context = context;
        this.tasks = new ArrayList<>();
        this.adapter = new TaskAdapter(context, tasks, R.id.taskCV, true, recyclerViewInterface, this);
        recyclerView.setAdapter(this.adapter, false);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.context));
        recyclerView.setDragEnabled(true);
        recyclerView.setCanDragVertically(true);
        this.titleTextView.setText(this.name);
    }

    public TaskAdapter getAdapter() {
        return adapter;
    }

    public Context getContext() {
        return context;
    }

    public String getName() {
        return name;
    }

    public DragListView getRecyclerView() {
        return recyclerView;
    }

    public ArrayList<TaskModel> getTasks() {
        return tasks;
    }

    public TextView getTitleTextView() {
        return titleTextView;
    }

    public void addTask(TaskModel task) {
        tasks.add(task);
        adapter.notifyItemChanged(tasks.size() - 1);
        //recyclerView.scrollToPosition(tasks.size() - 1);
    }
}
