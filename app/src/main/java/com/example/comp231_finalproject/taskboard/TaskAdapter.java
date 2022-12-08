package com.example.comp231_finalproject.taskboard;

import android.content.ClipData;
import android.content.Context;
import android.os.Build;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.util.Pair;
import androidx.recyclerview.widget.RecyclerView;

import com.example.comp231_finalproject.R;
import com.woxthebox.draglistview.DragItemAdapter;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class TaskAdapter extends DragItemAdapter<TaskModel, TaskAdapter.ViewHolder>{
    private final RecyclerViewInterface recyclerViewInterface;
    Context context;
    ArrayList<TaskModel> taskModels;
    private int mGrabHandleId;
    private boolean mDragOnLongPress;
    Column column;

    public TaskAdapter(Context context, ArrayList<TaskModel> taskModels, int grabHandleId, boolean dragOnLongPress, RecyclerViewInterface recyclerViewInterface, Column column) {
        this.context = context;
        this.taskModels = taskModels;
        this.mGrabHandleId = grabHandleId;
        this.mDragOnLongPress = dragOnLongPress;
        this.recyclerViewInterface = recyclerViewInterface;
        this.column = column;
        setItemList(taskModels);
    }

    @NonNull
    @Override
    public TaskAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.recycler_view_card, parent, false);
        return new TaskAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TaskAdapter.ViewHolder holder, int position) {
        super.onBindViewHolder(holder, position);
        holder.tvTitle.setText(taskModels.get(position).getTitle());
        Date date = taskModels.get(position).getDueDate();
        DateFormat dateFormat = new SimpleDateFormat("MM/dd");
        String strDate = dateFormat.format(date);
        holder.tvDueDate.setText(strDate);
    }


    @Override
    public long getUniqueItemId(int position) {
        return taskModels.get(position).id;
    }


    public int getItemCount() {
        return taskModels.size();
    }

    public class ViewHolder extends DragItemAdapter.ViewHolder {
        TextView tvTitle, tvDueDate;

        public ViewHolder(View itemView) {
            super(itemView, mGrabHandleId, mDragOnLongPress);
            tvTitle = itemView.findViewById(R.id.tvTitle);
            tvDueDate = itemView.findViewById(R.id.tvDate);

            itemView.findViewById(mGrabHandleId).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(recyclerViewInterface != null) {
                        int position = getAdapterPosition();
                        if(position != RecyclerView.NO_POSITION) {
                            recyclerViewInterface.OnItemClick(position, column);
                        }
                    }
                }
            });
        }
    }
}
