package com.example.comp231_finalproject.schedule;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.comp231_finalproject.MainActivity;
import com.example.comp231_finalproject.R;
import com.example.comp231_finalproject.databinding.ActivityMainBinding;

public class ScheduleActivity extends AppCompatActivity {

    GridView gridViewDays,gridViewTime;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_schedule);

        ImageView homeIcon = findViewById(R.id.homeIcon);
        ImageView bellIcon = findViewById(R.id.bellIcon);
        TextView toolBarTitle = findViewById(R.id.toolBarTitle);

        homeIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ScheduleActivity.this, MainActivity.class));
            }
        });

        gridViewDays = (GridView) findViewById(R.id.gridview1);
        gridViewTime = (GridView) findViewById(R.id.gridview3);
        

        String[] weekdays = {"Mon", "Tue", "Wed", "Thu","Fri", "Sat","Sun"};
        String[] time = {"6am", "7am", "8am", "9am","10am", "11am","12pm","1pm","2pm","3pm","4pm","5pm","6pm","7pm","8pm","9pm","10pm","11pm",""};

        GridAdapter gridAdapterDays =new GridAdapter(ScheduleActivity.this,weekdays);
        GridAdapter gridAdapterTime =new GridAdapter(ScheduleActivity.this,time);
        gridViewDays.setAdapter(gridAdapterDays);
        gridViewTime.setAdapter(gridAdapterTime);








    }
}