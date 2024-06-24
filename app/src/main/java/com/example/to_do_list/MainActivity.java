package com.example.to_do_list;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity implements RecyclerAdapter.OnItemClickListener {

    EditText task1, date1, time1;
    ArrayList<TaskModal> taskModalArrayList;
    Button addTask1;
    RecyclerView recyclerView;
    RecyclerAdapter recyclerAdapter;
    DatabaseHelper databaseHelper;
    int editPosition = -1;

    @SuppressLint("ResourceAsColor")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        // Initialize DatabaseHelper
        databaseHelper = new DatabaseHelper(this);

        // initialization of edit texts
        task1 = findViewById(R.id.edTask1);
        date1 = findViewById(R.id.edDate1);
        time1 = findViewById(R.id.edTime1);

        // Ensure the EditTexts are not focusable but clickable
        date1.setFocusable(false);
        date1.setClickable(true);

        time1.setFocusable(false);
        time1.setClickable(true);

        // DatePicker and TimePicker should open after click
        date1.setOnClickListener(v -> showDatePickerDialog());
        time1.setOnClickListener(v -> showTimePickerDialog());

        // setting layout for RecyclerView
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // setting data on RecyclerView
        taskModalArrayList = databaseHelper.getAllTasks();
        recyclerAdapter = new RecyclerAdapter(this, taskModalArrayList, this);
        recyclerView.setAdapter(recyclerAdapter);

        // on addButton click
        addTask1 = findViewById(R.id.addTask);
        addTask1.setOnClickListener(v -> {
            // getting text
            String taskName = task1.getText().toString();
            String taskDate = date1.getText().toString();
            String taskTime = time1.getText().toString();

            if (!taskName.isEmpty() && !taskDate.isEmpty() && !taskTime.isEmpty()) {
                if (editPosition == -1) {
                    // Add new task to the list
                    TaskModal newTask = new TaskModal(taskName, taskDate, taskTime);
                    long id = databaseHelper.addTask(newTask);
                    newTask.setId((int) id);
                    taskModalArrayList.add(newTask);
                    recyclerAdapter.notifyItemInserted(taskModalArrayList.size() - 1);
                    Toast.makeText(MainActivity.this, "Task Added Successfully", Toast.LENGTH_SHORT).show();
                } else {
                    // Update the existing task
                    TaskModal taskModal = taskModalArrayList.get(editPosition);
                    taskModal.setTaskName(taskName);
                    taskModal.setCompletionDate(taskDate);
                    taskModal.setCompletionTime(taskTime);
                    databaseHelper.updateTask(taskModal.getId(), taskModal);
                    recyclerAdapter.notifyItemChanged(editPosition);
                    Toast.makeText(MainActivity.this, "Task Updated Successfully", Toast.LENGTH_SHORT).show();
                    editPosition = -1;
                }
                clearInputFields();
            } else {
                Toast.makeText(MainActivity.this, "Please Fill All Fields", Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public void onEditClick(int position) {
        TaskModal taskModal = taskModalArrayList.get(position);
        task1.setText(taskModal.getTaskName());
        date1.setText(taskModal.getCompletionDate());
        time1.setText(taskModal.getCompletionTime());
        editPosition = position;
    }

    private void showDatePickerDialog() {
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(
                MainActivity.this,
                (view, year1, monthOfYear, dayOfMonth) -> date1.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year1),
                year, month, day);
        datePickerDialog.show();
    }

    private void showTimePickerDialog() {
        final Calendar c = Calendar.getInstance();
        int hour = c.get(Calendar.HOUR_OF_DAY);
        int minute = c.get(Calendar.MINUTE);

        TimePickerDialog timePickerDialog = new TimePickerDialog(MainActivity.this,
                (view, hourOfDay, minute1) -> time1.setText(hourOfDay + ":" + minute1),
                hour, minute, false);
        timePickerDialog.show();
    }

    private void clearInputFields() {
        task1.setText("");
        date1.setText("");
        time1.setText("");
    }
}
