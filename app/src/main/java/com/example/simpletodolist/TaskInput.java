package com.example.simpletodolist;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;
import java.util.Date;

import io.objectbox.Box;

public class TaskInput extends AppCompatActivity {
//    Declare the variables
    private Box<Task> taskBox;
    private Task task;
    private String text;
    private String TAG = "BadWolf";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_input);

        //Declare the box
        taskBox = ObjectBox.getBoxStore().boxFor(Task.class);

        //Allocate the Add Button and the rest of the things
        Button addButton = findViewById(R.id.addTaskButton);
        EditText taskInput = findViewById(R.id.taskInputField);


        addButton.setOnClickListener(view -> {
            text = String.valueOf(taskInput.getText());
            task = new Task();
            task.setTaskName(text);
            taskBox.put(task);
            finish();

        });
    }

}