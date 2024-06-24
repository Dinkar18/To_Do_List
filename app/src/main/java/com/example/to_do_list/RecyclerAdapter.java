package com.example.to_do_list;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {
    private Context context;
    private ArrayList<TaskModal> taskModalArrayList;
    private OnItemClickListener onItemClickListener;
    private DatabaseHelper databaseHelper;

    //creating interface for edit task
    public interface OnItemClickListener {
        void onEditClick(int position);
    }

    public RecyclerAdapter(Context context, ArrayList<TaskModal> taskModalArrayList, OnItemClickListener onItemClickListener) {
        this.context = context;
        this.taskModalArrayList = taskModalArrayList;
        this.onItemClickListener = onItemClickListener;
        this.databaseHelper = new DatabaseHelper(context);
    }

    @NonNull
    @Override
    public RecyclerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_design_recycle, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerAdapter.ViewHolder holder, int position) {
        // Setting data on RecyclerView
        TaskModal task = taskModalArrayList.get(position);
        holder.taskRV.setText(task.getTaskName());
        holder.dateRv.setText(task.getCompletionDate());
        holder.timeRv.setText(task.getCompletionTime());

        // Delete button implementation
        holder.deleteButton.setOnClickListener(v -> {
            databaseHelper.deleteTask(task.getId());
            taskModalArrayList.remove(position);
            notifyItemRemoved(position);
            notifyItemRangeChanged(position, taskModalArrayList.size());
        });

        // Edit button implementation
        holder.editButton.setOnClickListener(v -> onItemClickListener.onEditClick(position));
    }

    @Override
    public int getItemCount() {
        return taskModalArrayList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView taskRV, dateRv, timeRv;
        TextView deleteButton, editButton;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            taskRV = itemView.findViewById(R.id.taskTextView1);
            dateRv = itemView.findViewById(R.id.textViewDate3);
            timeRv = itemView.findViewById(R.id.textViewTime2);
            deleteButton = itemView.findViewById(R.id.deleteLogo);
            editButton = itemView.findViewById(R.id.editLogo);
        }
    }
}
