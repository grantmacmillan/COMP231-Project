package com.example.comp231_finalproject.schedule;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.comp231_finalproject.ColumnJSON;
import com.example.comp231_finalproject.MainActivity;
import com.example.comp231_finalproject.MyJSON;
import com.example.comp231_finalproject.R;
import com.example.comp231_finalproject.calendar.CalendarActivity;
import com.example.comp231_finalproject.calendar.Event;
import com.example.comp231_finalproject.taskboard.Column;
import com.github.tlaabs.timetableview.Schedule;
import com.github.tlaabs.timetableview.Time;
import com.github.tlaabs.timetableview.TimetableView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class NewScheduleActivity extends AppCompatActivity implements NewScheduleItemDialog.NewScheduleItemDialogListener{

    TimetableView timetable;
    FloatingActionButton fab;
    ArrayList<Schedule> schedules = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_schedule);

        timetable = findViewById(R.id.timetable);
        fab = findViewById(R.id.fab);
        ImageView homeIcon = findViewById(R.id.homeIcon);

        homeIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(NewScheduleActivity.this, MainActivity.class));
            }
        });

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NewScheduleItemDialog newScheduleItemDialog = new NewScheduleItemDialog();
                newScheduleItemDialog.show(getSupportFragmentManager(), "new schedule dialog");
            }
        });
        timetable.setOnStickerSelectEventListener(new TimetableView.OnStickerSelectedListener() {
            @Override
            public void OnStickerSelected(int idx, ArrayList<Schedule> schedules) {
                AlertDialog.Builder alert = new AlertDialog.Builder(NewScheduleActivity.this);
                alert.setTitle("Delete entry");
                alert.setPositiveButton(R.string.delete, new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int which) {
                        timetable.remove(idx);
                    }
                });
                alert.setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // close dialog
                        dialog.cancel();
                    }
                });
                alert.show();
            }
        });

        LoadEvents();
    }

    @Override
    public void CreateScheduleItem(String eventTitle, int day, Time startTime, Time endTime) {
        Schedule schedule = new Schedule();
        schedule.setClassTitle(eventTitle);
        schedule.setDay(day);
        schedule.setStartTime(startTime);
        schedule.setEndTime(endTime);
        ArrayList<Schedule> scheduleToBeAdded = new ArrayList<>();
        schedules.add(schedule);
        timetable.add(schedules);
    }

    @Override
    protected void onStop() {
        super.onStop();
        Gson gson = new Gson();
        String json = timetable.createSaveData();
        MyJSON.saveData(this, json, "schedule.json");
    }

    private void LoadEvents() {
        Gson gson = new Gson();
        String json = MyJSON.getData(this, "schedule.json");
        if(json != null) {
            timetable.load(json);
        }
    }

}