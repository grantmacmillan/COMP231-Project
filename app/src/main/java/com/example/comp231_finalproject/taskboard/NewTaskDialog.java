package com.example.comp231_finalproject.taskboard;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatDialogFragment;

import com.example.comp231_finalproject.R;

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
        View view = inflater.inflate(R.layout.new_task_dialog, null);

        builder.setView(view);
        AlertDialog dialog = builder.create();
        Button cancelButton = view.findViewById(R.id.cancelButton);
        Button addButton = view.findViewById(R.id.addButton);

        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.cancel();
            }
        });

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String taskName = ETTaskName.getText().toString();
                int day = DPDueDate.getDayOfMonth();
                int month = DPDueDate.getMonth();
                int year = DPDueDate.getYear();
                Calendar c = Calendar.getInstance();
                c.set(year, month, day);
                c.set(Calendar.HOUR_OF_DAY, 23);
                c.set(Calendar.MINUTE, 59);

                Date taskDate = c.getTime();
                listener.CreateTask(taskName, taskDate, column);
                dialog.cancel();
            }
        });
        ETTaskName = view.findViewById(R.id.taskName);
        DPDueDate = view.findViewById(R.id.taskDate);
        Calendar c = Calendar.getInstance();
        DPDueDate.init(c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DAY_OF_MONTH), null);


        return dialog;
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

    public interface NewTaskDialogListener {
        void CreateTask(String taskName, Date taskDate, Column column);
    }
}
