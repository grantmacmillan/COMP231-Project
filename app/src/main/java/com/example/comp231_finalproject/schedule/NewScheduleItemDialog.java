package com.example.comp231_finalproject.schedule;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TimePicker;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatDialogFragment;

import com.example.comp231_finalproject.R;
import com.github.tlaabs.timetableview.Time;

public class NewScheduleItemDialog extends AppCompatDialogFragment {
    private EditText title;
    private TimePicker startPicker, endPicker;
    private Spinner spinner;
    private NewScheduleItemDialog.NewScheduleItemDialogListener listener;


    @RequiresApi(api = Build.VERSION_CODES.N)
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.new_schedule_item_dialog, null);

        builder.setView(view);
        AlertDialog dialog = builder.create();
        Button cancelButton = view.findViewById(R.id.cancelButton);
        Button addButton = view.findViewById(R.id.addButton);
        title = view.findViewById(R.id.eventTitle);
        startPicker = view.findViewById(R.id.startTime);
        endPicker = view.findViewById(R.id.endTime);
        spinner = view.findViewById(R.id.daySpinner);
        startPicker.setIs24HourView(true);
        endPicker.setIs24HourView(true);

        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.cancel();
            }
        });

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String eventTitle = title.getText().toString();
                int day = spinner.getSelectedItemPosition();
                Time startTime = new Time(startPicker.getHour(), startPicker.getMinute());
                Time endTime = new Time(endPicker.getHour(), endPicker.getMinute());

                listener.CreateScheduleItem(eventTitle, day, startTime, endTime);
                dialog.cancel();
            }
        });


        return dialog;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        try {
            listener = (NewScheduleItemDialog.NewScheduleItemDialogListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + "must implement NewTaskDialogListener");
        }
    }

    public interface NewScheduleItemDialogListener {
        void CreateScheduleItem(String eventTitle, int day, Time startTime, Time endTime);
    }
}
