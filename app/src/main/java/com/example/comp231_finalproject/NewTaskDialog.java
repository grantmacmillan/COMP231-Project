package com.example.comp231_finalproject;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatDialogFragment;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Calendar;
import java.util.Date;

public class NewTaskDialog extends AppCompatDialogFragment {
    private EditText ETTaskName;
    private DatePicker DPDueDate;
    private NewTaskDialogListener listener;
    private Column column;


    @RequiresApi(api = Build.VERSION_CODES.N)
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.newtask_dialog, null);

        builder.setView(view).setNegativeButton("cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        }).setPositiveButton("add", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String taskName = ETTaskName.getText().toString();
                int day = DPDueDate.getDayOfMonth();
                int month = DPDueDate.getMonth();
                int year = DPDueDate.getYear();
                Calendar c = Calendar.getInstance();
                c.set(year, month, day);
                Date taskDate = c.getTime();
                listener.CreateTask(taskName, taskDate, column);
            }
        });
        ETTaskName = view.findViewById(R.id.taskName);
        DPDueDate = view.findViewById(R.id.taskDate);
        Calendar c = Calendar.getInstance();
        DPDueDate.init(c.get(Calendar.YEAR),c.get(Calendar.MONTH), c.get(Calendar.DAY_OF_MONTH), null);

        return builder.create();
    }
    public void SetColumn(Column column) {
        this.column = column;
    }
    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        try {
            listener = (NewTaskDialogListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + "must implement NewTaskDialogListener");
        }
    }
    public interface NewTaskDialogListener{
        void CreateTask(String taskName, Date taskDate, Column column);
    }
}
