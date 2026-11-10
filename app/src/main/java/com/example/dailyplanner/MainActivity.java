package com.example.dailyplanner;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;
import java.util.ArrayList;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    Button completedButton;
    static ArrayList<String> completedTasks = new ArrayList<>();

    Button addButton;
    LinearLayout taskList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addButton = findViewById(R.id.addButton);
        taskList = findViewById(R.id.taskList);

        completedButton = findViewById(R.id.completedButton);

        completedButton.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this,
                    CompletedTasksActivity.class);

            intent.putExtra("completedTasks",
                    completedTasks.toArray(new String[0]));

            startActivity(intent);
        });

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(
                        MainActivity.this,
                        AddTaskActivity.class
                );

                startActivityForResult(intent, 1);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode,
                                    int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1 && resultCode == RESULT_OK) {

            String task = data.getStringExtra("task");
            String date = data.getStringExtra("date");
            String time = data.getStringExtra("time");

            addTaskCard(task, date, time);
        }
    }

    // Add task card to the screen
    private void addTaskCard(String task, String date, String time) {

        LinearLayout card = new LinearLayout(this);
        card.setOrientation(LinearLayout.VERTICAL);
        card.setPadding(20, 15, 20, 15);

        GradientDrawable background = new GradientDrawable();
        background.setColor(Color.WHITE);
        background.setCornerRadius(20);
        background.setStroke(2, Color.LTGRAY);

        card.setBackground(background);

        LinearLayout.LayoutParams cardParams =
                new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT
                );

        cardParams.setMargins(0, 10, 0, 10);
        card.setLayoutParams(cardParams);

        CheckBox checkBox = new CheckBox(this);
        checkBox.setText(task);
        checkBox.setTextSize(18);
        checkBox.setTextColor(Color.BLACK);
        checkBox.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                completedTasks.add(task + " | Date: " + date
                        + " | Time: " + time);

                taskList.removeView(card);
            }
        });

        TextView dateText = new TextView(this);
        dateText.setText("Date: " + date);
        dateText.setTextSize(14);
        dateText.setTextColor(Color.DKGRAY);

        TextView timeText = new TextView(this);
        timeText.setText("Time: " + time);
        timeText.setTextSize(14);
        timeText.setTextColor(Color.DKGRAY);

        Button deleteButton = new Button(this);
        deleteButton.setText("Delete");

        deleteButton.setOnClickListener(v -> {
            taskList.removeView(card);
        });

        card.addView(checkBox);
        card.addView(dateText);
        card.addView(timeText);
        card.addView(deleteButton);

        taskList.addView(card);
    }
}