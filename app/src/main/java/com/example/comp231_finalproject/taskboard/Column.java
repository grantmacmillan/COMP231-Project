package com.example.comp231_finalproject.taskboard;

import android.content.Context;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.comp231_finalproject.R;
import com.woxthebox.draglistview.DragListView;

import java.util.ArrayList;


public class Column {
    String name;
    DragListView recyclerView;
    TextView titleTextView;
    ArrayList<TaskModel> tasks;
    TaskAdapter adapter;
    Context context;
    LinearLayout linearLayout;

    public Column(String name, DragListView recyclerView, TextView titleTextView, Context context, RecyclerViewInterface recyclerViewInterface, LinearLayout linearLayout) {
        this.name = name;
        this.recyclerView = recyclerView;
        this.titleTextView = titleTextView;
        this.context = context;
        this.tasks = new ArrayList<>();
        this.adapter = new TaskAdapter(context, tasks, R.id.taskCV, true, recyclerViewInterface, this);
        this.linearLayout = linearLayout;
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

    public LinearLayout getLinearLayout() {
        return linearLayout;
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

    public void removeTask(TaskModel task) {
        tasks.remove(task);
        adapter.notifyDataSetChanged();
    }
}
