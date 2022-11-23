package com.example.comp231_finalproject.taskboard;

import java.util.Date;

public class TaskModel {
    String title;
    Date dueDate;

    public TaskModel(String title, Date dueDate) {
        this.title = title;
        this.dueDate = dueDate;
    }

    public String getTitle() {
        return title;
    }

    public Date getDueDate() {
        return dueDate;
    }
}
