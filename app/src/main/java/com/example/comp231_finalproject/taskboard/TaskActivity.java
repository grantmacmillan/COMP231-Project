package com.example.comp231_finalproject.taskboard;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.comp231_finalproject.MainActivity;
import com.example.comp231_finalproject.R;
import com.example.comp231_finalproject.drag.DragLinearLayout;

import java.util.ArrayList;
import java.util.Date;


public class TaskActivity extends AppCompatActivity implements NewTaskDialog.NewTaskDialogListener, NewColumnDialog.NewColumnDialogListener {
    DragLinearLayout layout;
    ArrayList<Column> columns = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_task);
        layout = (DragLinearLayout) findViewById(R.id.linearLayout);
        ImageView homeIcon = findViewById(R.id.homeIcon);
        ImageView bellIcon = findViewById(R.id.bellIcon);
        TextView toolBarTitle = findViewById(R.id.toolBarTitle);
        ImageView addColumn = findViewById(R.id.addColumnIV);

        addColumn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NewColumnDialog newColumnDialog = new NewColumnDialog();
                newColumnDialog.show(getSupportFragmentManager(), "new column dialog");
            }
        });

        homeIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(TaskActivity.this, MainActivity.class));
            }
        });
    }

    public void AddColumn(String columnName) {
        LinearLayout newColumnLayout = (LinearLayout) getLayoutInflater().inflate(R.layout.task_column, layout, false);
        layout.addView(newColumnLayout);
        //gets recycler view in the layout that was just added (the new column)
        RecyclerView recyclerView = newColumnLayout.findViewById(R.id.recyclerView);
        ImageView imageView = newColumnLayout.findViewById(R.id.imageView);
        TextView textView = newColumnLayout.findViewById(R.id.textView);
        layout.setViewDraggable(newColumnLayout, textView);
        Column column = new Column(columnName, recyclerView, textView, this);
        columns.add(column);

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OpenNewTaskDialog(column);
            }
        });
    }

    private void OpenNewTaskDialog(Column column) {
        NewTaskDialog newTaskDialog = new NewTaskDialog();
        newTaskDialog.show(getSupportFragmentManager(), "new task dialog");
        newTaskDialog.SetColumn(column);
    }

    @Override
    public void CreateTask(String taskName, Date taskDate, Column column) {
        column.addTask(new TaskModel(taskName, taskDate));
    }

    @Override
    public void CreateColumn(String columnName) {
        AddColumn(columnName);
    }
}