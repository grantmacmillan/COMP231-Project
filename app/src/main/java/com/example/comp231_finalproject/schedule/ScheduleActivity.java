package com.example.comp231_finalproject.schedule;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.print.PrintAttributes;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.comp231_finalproject.MainActivity;
import com.example.comp231_finalproject.R;
import com.example.comp231_finalproject.databinding.ActivityMainBinding;

public class ScheduleActivity extends AppCompatActivity {

    GridView gridViewDays, gridViewTime, gridViewItem;
    TextView textview;


    SharedPreferences myPref;
    SharedPreferences.Editor myEditor;

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
        gridViewItem = (GridView) findViewById(R.id.gridview2);


        String[] weekdays = {"Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun"};
        String[] time = {"6am", "7am", "8am", "9am", "10am", "11am", "12pm", "1pm", "2pm", "3pm", "4pm", "5pm", "6pm", "7pm", "8pm", "9pm", "10pm", "11pm", ""};
        String[] item = new String[126];



        myPref= getSharedPreferences("MySharedPref",MODE_PRIVATE);
        String[] startTime = new String[126];
        for (int j = 0; j <startTime.length; j++) {
            startTime[j] = myPref.getString("startTime" + j, "");

        }

        String[] dash = new String[126];
        //populate array with empty string
        for (int i = 0; i < dash.length; i++) {
            dash[i] = " - ";


            String[] endTime = new String[126];
            //populate array with empty string
            for (int k = 0; k < endTime.length; k++) {
                endTime[k] = myPref.getString("endTime"+k,"");
            }

            String[] className = new String[126];
            //populate array with empty string
            for (int n = 0; n < className.length; n++) {
                className[n] = myPref.getString("title"+n,"");

                if ( className[n].equals("")) {
                    textview = findViewById(R.id.txt_className);
                   //  textview.setBackgroundColor(Color.WHITE);   //doesn't work, app stops run
                }



            }


        GridAdapter gridAdapterDays = new GridAdapter(ScheduleActivity.this, weekdays);
        GridAdapter gridAdapterTime = new GridAdapter(ScheduleActivity.this, time);
      //     GridAdapter gridAdapterItem =new GridAdapter(ScheduleActivity.this,item);
        ItemGridAdapter gridAdapterItem = new ItemGridAdapter(ScheduleActivity.this, startTime, dash, endTime, className);

        gridViewDays.setAdapter(gridAdapterDays);
        gridViewTime.setAdapter(gridAdapterTime);
        gridViewItem.setAdapter(gridAdapterItem);


        gridViewItem.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(ScheduleActivity.this, "You clicked on" + i, Toast.LENGTH_SHORT).show();

                myPref = getSharedPreferences("MySharedPref",MODE_PRIVATE);
                myEditor = myPref.edit();
                //store a string in memory
                myEditor.putString("itemNumber", String.valueOf(i));
                myEditor.commit();

                startActivity(new Intent(ScheduleActivity.this, ScheduleItemActivity.class));

            }
        });


    }}
}