package com.example.comp231_finalproject.taskboard;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatDialogFragment;

import com.example.comp231_finalproject.R;

public class EditColumnDialog extends AppCompatDialogFragment {
    private EditText ETTaskName;
    private Column column;
    private EditColumnDialog.EditColumnDialogListener listener;


    @RequiresApi(api = Build.VERSION_CODES.N)
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.edit_column_dialog, null);

        builder.setView(view);
        AlertDialog dialog = builder.create();
        Button cancelButton = view.findViewById(R.id.deleteButton);
        Button addButton = view.findViewById(R.id.addButton);

        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.DeleteColumn(column);
                dialog.cancel();
            }
        });

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                column.getTitleTextView().setText(ETTaskName.getText().toString());
                column.name = ETTaskName.getText().toString();
                dialog.cancel();
            }
        });
        ETTaskName = view.findViewById(R.id.taskName);
        ETTaskName.setText(column.getName());

        return dialog;
    }

    public void SetColumn(Column column) {
        this.column = column;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        try {
            listener = (EditColumnDialog.EditColumnDialogListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + "must implement NewTaskDialogListener");
        }
    }

    public interface EditColumnDialogListener {
        void DeleteColumn(Column column);
    }
}
