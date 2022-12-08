package com.example.comp231_finalproject;

import com.example.comp231_finalproject.taskboard.TaskModel;

import java.util.ArrayList;

public class ColumnJSON {
    String columnName;
    ArrayList<TaskModel> tasks;

    public ColumnJSON(String columnName, ArrayList<TaskModel> tasks) {
        this.columnName = columnName;
        this.tasks = tasks;
    }
}
