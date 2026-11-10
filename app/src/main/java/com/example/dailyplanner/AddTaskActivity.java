package com.example.dailyplanner;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;
import java.util.Locale;

public class AddTaskActivity extends AppCompatActivity {

    EditText taskInput;
    Button dateButton, timeButton, saveButton;
    TextView selectedDate, selectedTime;

    String date = "No date selected";
    String time = "No time selected";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);

        taskInput = findViewById(R.id.taskInput);
        dateButton = findViewById(R.id.dateButton);
        timeButton = findViewById(R.id.timeButton);
        saveButton = findViewById(R.id.saveButton);

        selectedDate = findViewById(R.id.selectedDate);
        selectedTime = findViewById(R.id.selectedTime);

        dateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Calendar calendar = Calendar.getInstance();

                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH);
                int day = calendar.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePicker = new DatePickerDialog(
                        AddTaskActivity.this,
                        (view, y, m, d) -> {

                            date = d + "/" + (m + 1) + "/" + y;
                            selectedDate.setText(date);
                        },
                        year, month, day
                );

                datePicker.show();
            }
        });

        timeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Calendar calendar = Calendar.getInstance();

                int hour = calendar.get(Calendar.HOUR_OF_DAY);
                int minute = calendar.get(Calendar.MINUTE);

                TimePickerDialog timePicker = new TimePickerDialog(
                        AddTaskActivity.this,
                        (view, h, m) -> {

                            time = String.format(
                                    Locale.getDefault(),
                                    "%02d:%02d", h, m
                            );

                            selectedTime.setText(time);
                        },
                        hour, minute, true
                );

                timePicker.show();
            }
        });

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String task = taskInput.getText().toString().trim();

                if (task.isEmpty()) {
                    Toast.makeText(AddTaskActivity.this,
                            "Please enter a task",
                            Toast.LENGTH_SHORT).show();
                } else {

                    getIntent().putExtra("task", task);
                    getIntent().putExtra("date", date);
                    getIntent().putExtra("time", time);

                    setResult(RESULT_OK, getIntent());
                    finish();
                }
            }
        });
    }
}