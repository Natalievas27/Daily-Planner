package com.example.dailyplanner;

import android.os.Bundle;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class CompletedTasksActivity extends AppCompatActivity {

    LinearLayout completedList;
    Button backButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_completed_tasks);

        completedList = findViewById(R.id.completedList);
        backButton = findViewById(R.id.backButton);

        backButton.setOnClickListener(v -> finish());

        String[] tasks = getIntent().getStringArrayExtra("completedTasks");

        if (tasks != null) {
            for (String task : tasks) {
                TextView textView = new TextView(this);
                textView.setText("✓ " + task);
                textView.setTextSize(18);
                textView.setPadding(10, 15, 10, 15);

                completedList.addView(textView);
            }
        }
    }
}