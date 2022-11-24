package com.example.comp231_finalproject.taskboard;

import android.content.Context;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;


public class Column {
    String name;
    RecyclerView recyclerView;
    TextView titleTextView;
    ArrayList<TaskModel> tasks;
    TaskAdapter adapter;
    Context context;

    public Column(String name, RecyclerView recyclerView, TextView titleTextView, Context context) {
        this.name = name;
        this.recyclerView = recyclerView;
        this.titleTextView = titleTextView;
        this.context = context;
        this.tasks = new ArrayList<>();
        this.adapter = new TaskAdapter(context, tasks);
        recyclerView.setAdapter(this.adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.context));
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

    public RecyclerView getRecyclerView() {
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
        recyclerView.scrollToPosition(tasks.size() - 1);
    }
}
