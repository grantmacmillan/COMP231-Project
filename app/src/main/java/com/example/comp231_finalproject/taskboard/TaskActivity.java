package com.example.comp231_finalproject.taskboard;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.comp231_finalproject.ColumnJSON;
import com.example.comp231_finalproject.MainActivity;
import com.example.comp231_finalproject.MyJSON;
import com.example.comp231_finalproject.R;
import com.example.comp231_finalproject.drag.DragLinearLayout;
import com.google.gson.Gson;
import com.woxthebox.draglistview.DragListView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;


public class TaskActivity extends AppCompatActivity implements NewTaskDialog.NewTaskDialogListener, NewColumnDialog.NewColumnDialogListener,EditTaskDialog.EditTaskDialogListener, EditColumnDialog.EditColumnDialogListener, RecyclerViewInterface {
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
        DragListView recyclerView = newColumnLayout.findViewById(R.id.recyclerView);
        ImageView imageView = newColumnLayout.findViewById(R.id.imageView);
        TextView textView = newColumnLayout.findViewById(R.id.textView);
        ImageView editImageView = newColumnLayout.findViewById(R.id.editImageView);
        layout.setViewDraggable(newColumnLayout, textView);
        Column column = new Column(columnName, recyclerView, textView, this, this, newColumnLayout);
        columns.add(column);

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OpenNewTaskDialog(column);
            }
        });
        editImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OpenEditColumnDialog(column);
            }
        });
    }
    public Column LoadColumn(String columnName) {
        LinearLayout newColumnLayout = (LinearLayout) getLayoutInflater().inflate(R.layout.task_column, layout, false);
        layout.addView(newColumnLayout);
        //gets recycler view in the layout that was just added (the new column)
        DragListView recyclerView = newColumnLayout.findViewById(R.id.recyclerView);
        ImageView imageView = newColumnLayout.findViewById(R.id.imageView);
        TextView textView = newColumnLayout.findViewById(R.id.textView);
        ImageView editImageView = newColumnLayout.findViewById(R.id.editImageView);

        layout.setViewDraggable(newColumnLayout, textView);
        Column column = new Column(columnName, recyclerView, textView, this, this, newColumnLayout);
        columns.add(column);

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OpenNewTaskDialog(column);
            }
        });

        editImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OpenEditColumnDialog(column);
            }
        });
        return column;
    }

    private void OpenEditColumnDialog(Column column) {
        EditColumnDialog editColumnDialog = new EditColumnDialog();
        editColumnDialog.show(getSupportFragmentManager(), "edit column dialog");
        editColumnDialog.SetColumn(column);
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

    @Override
    public void OnItemClick(int position, Column column) {
        EditTaskDialog editTaskDialog = new EditTaskDialog();
        editTaskDialog.show(getSupportFragmentManager(), "edit task dialog");
        editTaskDialog.SetColumns(columns);
        editTaskDialog.SetTask(column.getTasks().get(position));
        editTaskDialog.SetColumn(column);
    }

    @Override
    public void EditTask(String taskName, Date taskDate, Column column, TaskModel taskModel) {
        column.adapter.taskModels.get(column.adapter.taskModels.indexOf(taskModel)).setTitle(taskName);
        column.adapter.taskModels.get(column.adapter.taskModels.indexOf(taskModel)).setDueDate(taskDate);
        column.adapter.notifyItemChanged(column.adapter.taskModels.indexOf(taskModel));
    }

    @Override
    protected void onStop() {
        super.onStop();
        Gson gson = new Gson();
        ArrayList<ColumnJSON> columnJSONS = new ArrayList<>();
        for(int i = 0; i < columns.size(); i++) {
            columnJSONS.add(new ColumnJSON(columns.get(i).getName(),columns.get(i).getTasks()));
        }
        String json = gson.toJson(columnJSONS);
        MyJSON.saveData(this, json, "columns.json");
    }

    @Override
    protected void onResume() {
        super.onResume();
        String json = MyJSON.getData(this, "columns.json");

        Gson gson = new Gson();
        ArrayList<ColumnJSON> columnJSONS = null;

        ColumnJSON[] array = gson.fromJson(json, ColumnJSON[].class);

        if(array != null) {
            columnJSONS = new ArrayList<>(Arrays.asList(array));


            for (int i = 0; i < columnJSONS.size(); i++) {
                String columnName = columnJSONS.get(i).getColumnName();
                ArrayList<TaskModel> tasks = columnJSONS.get(i).getTasks();

                Column column = LoadColumn(columnName);
                for (int j = 0; j < tasks.size(); j++) {
                    column.addTask(tasks.get(j));
                }
            }
        }
    }

    @Override
    public void DeleteColumn(Column column) {
        layout.removeView(column.getLinearLayout());
        columns.remove(column);

    }
}