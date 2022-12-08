package com.example.comp231_finalproject.taskboard;

import java.util.Date;
import java.util.UUID;

public class TaskModel {
    String title;
    Date dueDate;
    Long id;

    public TaskModel(String title, Date dueDate) {
        this.title = title;
        this.dueDate = dueDate;
        id = UUID.randomUUID().getMostSignificantBits() & Long.MAX_VALUE;
    }

    public String getTitle() {
        return title;
    }

    public Date getDueDate() {
        return dueDate;
    }
}
