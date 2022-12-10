package com.example.comp231_finalproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import com.example.comp231_finalproject.calendar.CalendarActivity;
import com.example.comp231_finalproject.calendar.Event;
import com.example.comp231_finalproject.schedule.NewScheduleActivity;
import com.example.comp231_finalproject.schedule.ScheduleActivity;
import com.example.comp231_finalproject.taskboard.Column;
import com.example.comp231_finalproject.taskboard.TaskActivity;
import com.example.comp231_finalproject.taskboard.TaskModel;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        ImageView bellIcon = findViewById(R.id.bellIcon);

        bellIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showMenu(v);
            }
        });
    }

    private void showMenu(View v){
        Date currentTime = Calendar.getInstance().getTime();
        HashMap<String, Long> map = new HashMap<>();
        LinkedHashMap<String, Long> sortedMap = new LinkedHashMap<>();
        ArrayList<Long> list = new ArrayList<>();

        PopupMenu popupMenu = new PopupMenu(MainActivity.this, v);
        popupMenu.getMenuInflater().inflate(R.menu.popup_menu, popupMenu.getMenu());

        String json = MyJSON.getData(this, "columns.json");
        Gson gson = new Gson();
        ArrayList<ColumnJSON> columnJSONS = null;
        ColumnJSON[] array = gson.fromJson(json, ColumnJSON[].class);

        if(array != null) {
            columnJSONS = new ArrayList<>(Arrays.asList(array));

            for (int i = 0; i < columnJSONS.size(); i++) {
                String columnName = columnJSONS.get(i).getColumnName();
                ArrayList<TaskModel> tasks = columnJSONS.get(i).getTasks();

                for (int j = 0; j < tasks.size(); j++) {

                    long hours = getDateDiff(currentTime,tasks.get(j).getDueDate(),TimeUnit.HOURS);

                    if(hours < 48){
                        Log.e("hour:", String.valueOf(hours));
                        map.put(tasks.get(j).getTitle(), hours);
                        //popupMenu.getMenu().add(tasks.get(j).getTitle()+" in "+hours+" hours");

                    }
                }
            }
        }


        String json1 = MyJSON.getData(this, "events.json");

        Event[] array1 = gson.fromJson(json1, Event[].class);

        if(array1 != null) {
            List<Event> events = Arrays.asList(array1);

            for (int j = 0; j < events.size(); j++) {

                long hours = getDateDiff(currentTime,events.get(j).getDate().getTime(),TimeUnit.HOURS);

                if(hours < 48 && hours>0){
                    Log.e("hour:", String.valueOf(hours));
                    map.put(events.get(j).getTitle(), hours);
                    //popupMenu.getMenu().add(events.get(j).getTitle()+" in "+hours+" hours");

                }
            }
        }

        for (Map.Entry<String, Long> entry : map.entrySet()) {
            list.add(entry.getValue());
        }
        Collections.sort(list);
        for (long num : list) {
            for (Map.Entry<String, Long> entry : map.entrySet()) {
                if (entry.getValue().equals(num)) {
                    sortedMap.put(entry.getKey(), num);
                }
            }
        }

        //Sort By Hours
        for (Map.Entry<String, Long> entry : sortedMap.entrySet()) {
            String key = entry.getKey();
            Long value = entry.getValue();

            popupMenu.getMenu().add(key+" in "+value+" hours");
        }
        //System.out.println(sortedMap);

        //popupMenu.getMenu().add("Event 4");
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {

                return false;
            }
        });

        popupMenu.show();
    }

    public static long getDateDiff(Date date1, Date date2, TimeUnit timeUnit) {
        long diffInHours = date2.getTime() - date1.getTime();
        diffInHours = timeUnit.convert(diffInHours,TimeUnit.HOURS);
        return diffInHours/1000/60/60;
    }

    public void button1Clicked(View v) {
        startActivity(new Intent(MainActivity.this, TaskActivity.class));
        overridePendingTransition( R.anim.slide_in_up, R.anim.stay );
    }
    public void button2Clicked(View v){

            startActivity(new Intent(MainActivity.this, CalendarActivity.class));
        overridePendingTransition( R.anim.slide_in_up, R.anim.stay );

         
    }

    public void button3Clicked(View v){

            startActivity(new Intent(MainActivity.this, NewScheduleActivity.class));
        overridePendingTransition( R.anim.slide_in_up, R.anim.stay );

    }
}