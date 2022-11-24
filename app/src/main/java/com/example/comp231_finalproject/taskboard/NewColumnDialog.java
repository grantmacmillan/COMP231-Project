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

public class NewColumnDialog extends AppCompatDialogFragment {
    private EditText ETTaskName;
    private NewColumnDialogListener listener;


    @RequiresApi(api = Build.VERSION_CODES.N)
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.new_column_dialog, null);

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
                String columnName = ETTaskName.getText().toString();
                listener.CreateColumn(columnName);
                dialog.cancel();
            }
        });
        ETTaskName = view.findViewById(R.id.taskName);

        return dialog;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        try {
            listener = (NewColumnDialogListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + "must implement NewTaskDialogListener");
        }
    }

    public interface NewColumnDialogListener {
        void CreateColumn(String columnName);
    }
}
