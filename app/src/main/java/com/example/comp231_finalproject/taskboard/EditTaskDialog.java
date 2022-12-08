package com.example.comp231_finalproject.taskboard;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatDialogFragment;

import com.example.comp231_finalproject.R;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class EditTaskDialog extends AppCompatDialogFragment {
    private EditText ETTaskName;
    private DatePicker DPDueDate;
    private GridLayout gridLayout;
    private EditTaskDialog.EditTaskDialogListener listener;
    private ArrayList<Column> columns;
    private Column column;
    private TaskModel taskModel;
    private AlertDialog dialog;


    @RequiresApi(api = Build.VERSION_CODES.N)
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.edit_task_dialog, null);

        builder.setView(view);
        dialog = builder.create();
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
                Date taskDate = c.getTime();
                listener.EditTask(taskName, taskDate, column);
                dialog.cancel();
            }
        });
        ETTaskName = view.findViewById(R.id.taskName);
        DPDueDate = view.findViewById(R.id.taskDate);
        gridLayout = view.findViewById(R.id.gridLayout);
        Calendar c = Calendar.getInstance();
        DPDueDate.init(c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DAY_OF_MONTH), null);

        SetupButtons();


        return dialog;
    }

    private void SetupButtons() {
        for(int i = 0; i < columns.size(); i++) {
            if(columns.get(i).getName() != column.getName()) {
                Button button = (Button) getLayoutInflater().inflate(R.layout.column_button, gridLayout, false);
                button.setText(columns.get(i).getName());
                int finalI = i;
                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        columns.get(finalI).addTask(taskModel);
                        column.removeTask(taskModel);
                        dialog.dismiss();
                    }
                });
                gridLayout.addView(button);
            }
        }
    }

    public void SetColumn(Column column) {
        this.column = column;
    }

    public void SetColumns(ArrayList<Column> columns) {
        this.columns = columns;
    }

    public void SetTask(TaskModel taskModel) {
        this.taskModel = taskModel;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        try {
            listener = (EditTaskDialog.EditTaskDialogListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + "must implement EditTaskDialogListener");
        }
    }

    public interface EditTaskDialogListener {
        void EditTask(String taskName, Date taskDate, Column column);
    }
}
