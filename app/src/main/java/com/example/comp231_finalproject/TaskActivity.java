package com.example.comp231_finalproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Date;
import java.util.Dictionary;
import java.util.HashMap;
import java.util.Map;

public class TaskActivity extends AppCompatActivity {
    ArrayList<TaskModel> taskModels = new ArrayList<>();
    LinearLayout layout;
    ArrayList<RecyclerView> columnRecyclerViews;
    HashMap<String, RecyclerView> recyclerViewDictionary = new HashMap<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task);

        //todoRecyclerView = findViewById(R.id.TodoRecyclerView);
        //doneRecyclerView = findViewById(R.id.DoneRecyclerView);
        SetupTaskModels();

        TaskAdapter adapter = new TaskAdapter(this, taskModels);
        //todoRecyclerView.setAdapter(adapter);
        //todoRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        //doneRecyclerView.setAdapter(adapter);
        //doneRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        layout = (LinearLayout) findViewById (R.id.linearLayout);
        AddColumn();
        AddColumn();

    }

    private void SetupTaskModels() {
        //Placeholder test
        taskModels.add(new TaskModel("Task 1", new Date(2022, 11, 23)));
        taskModels.add(new TaskModel("Task 2", new Date(2022, 11, 25)));
        taskModels.add(new TaskModel("Task 3", new Date(2022, 11, 25)));
        taskModels.add(new TaskModel("Task 4", new Date(2022, 11, 25)));
        taskModels.add(new TaskModel("Task 5", new Date(2022, 11, 25)));
        taskModels.add(new TaskModel("Task 6", new Date(2022, 11, 25)));
        taskModels.add(new TaskModel("Task 7", new Date(2022, 11, 25)));
    }

    public void AddColumn() {
        LinearLayout newColumnLayout = (LinearLayout) getLayoutInflater().inflate(R.layout.task_column, layout, false);
        layout.addView(newColumnLayout);
        //gets recycler view in the layout that was just added (the new column)
        RecyclerView recyclerView = newColumnLayout.findViewById(R.id.recyclerView);
        ImageView imageView = newColumnLayout.findViewById(R.id.imageView);

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddTask(recyclerView);
            }
        });
        //key for dictionary = column name chosen by user
        //recyclerViewDictionary.put("Done", recyclerView);
    }

    private void AddTask(RecyclerView recyclerView) {
        Toast.makeText(this, recyclerView.toString(), Toast.LENGTH_SHORT).show();
    }
}