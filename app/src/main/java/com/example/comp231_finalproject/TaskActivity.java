package com.example.comp231_finalproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.Date;

public class TaskActivity extends AppCompatActivity {

    RecyclerView todoRecyclerView, doneRecyclerView;
    ArrayList<TaskModel> taskModels = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task);

        todoRecyclerView = findViewById(R.id.TodoRecyclerView);
        doneRecyclerView = findViewById(R.id.DoneRecyclerView);
        SetupTaskModels();

        TaskAdapter adapter = new TaskAdapter(this, taskModels);
        todoRecyclerView.setAdapter(adapter);
        todoRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        doneRecyclerView.setAdapter(adapter);
        doneRecyclerView.setLayoutManager(new LinearLayoutManager(this));
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
}