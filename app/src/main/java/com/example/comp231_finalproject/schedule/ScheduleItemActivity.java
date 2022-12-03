package com.example.comp231_finalproject.schedule;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.comp231_finalproject.R;

public class ScheduleItemActivity extends AppCompatActivity {
    SharedPreferences  myPref;
    SharedPreferences.Editor myEditor;
    String itemNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule_item);

        myPref= getSharedPreferences("MySharedPref",MODE_PRIVATE);
       itemNumber = myPref.getString("itemNumber", "");

        TextView SchClassName = findViewById(R.id.sch_item_title);
        TextView SchStartTime = findViewById(R.id.sch_item_starttime);
        TextView SchEndTime = findViewById(R.id.sch_item_endtime);


 TextView cancel = findViewById(R.id.tv_scedule_item_cancel);
 cancel.setOnClickListener(new View.OnClickListener() {
     @Override
     public void onClick(View view) {
         startActivity(new Intent(ScheduleItemActivity.this, ScheduleActivity.class));
     }
 });


        TextView save = findViewById(R.id.tv_schedule_item_save);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                myPref = getSharedPreferences("MySharedPref",MODE_PRIVATE);
                myEditor = myPref.edit();
                //store a string in memory
                myEditor.putString("title"+ itemNumber, SchClassName.getText().toString());
                myEditor.putString("startTime"+ itemNumber, SchStartTime.getText().toString());
                myEditor.putString("endTime"+ itemNumber, SchEndTime.getText().toString());
                myEditor.commit();

                startActivity(new Intent(ScheduleItemActivity.this, ScheduleActivity.class));
            }
        });






    }
}