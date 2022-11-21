package com.example.comp231_finalproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageView homeIcon = findViewById(R.id.homeIcon);
        ImageView bellIcon = findViewById(R.id.bellIcon);
        TextView toolBarTitle = findViewById(R.id.toolBarTitle);

        homeIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Home button clicked", Toast.LENGTH_LONG).show();
            }
        });
    }

    public void button1Clicked(View v) {
        startActivity(new Intent(MainActivity.this, TaskActivity.class));
    }
    public void button2Clicked(View v){

            startActivity(new Intent(MainActivity.this, CalendarActivity.class));

         
    }

    public void button3Clicked(View v){

            startActivity(new Intent(MainActivity.this, ScheduleActivity.class));

    }
}