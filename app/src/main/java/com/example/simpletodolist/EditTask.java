package com.example.simpletodolist;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import io.objectbox.Box;

public class EditTask extends AppCompatActivity implements View.OnClickListener {
    private Box<Task> taskBox;
    private Task task;
    private final String TAG = "BadWolf";
    private EditText editedTaskName;
    private EditText editedDueDate;
    private EditText editedReminder;
    private Button saveButton;
    private Button cancelButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_task);

//        Initiate the box
        taskBox = ObjectBox.getBoxStore().boxFor(Task.class);

//        Get the value from the previous activity
        Bundle bundle = getIntent().getExtras();
        long currentID = bundle.getLong("currentID");

        task = taskBox.get(currentID);
//        Get the Views
        editedTaskName = findViewById(R.id.editTask);
        editedDueDate = findViewById(R.id.editDueDate);
        editedReminder = findViewById(R.id.editReminder);
        saveButton = findViewById(R.id.saveButton);
        cancelButton = findViewById(R.id.cancelButton);


//        Set the values to the views
        editedTaskName.setText(task.getTaskName());
        editedDueDate.setText(String.valueOf(task.getDueDate()));
        editedReminder.setText(String.valueOf(task.getRemindMe()));
        saveButton.setOnClickListener(this);
        cancelButton.setOnClickListener(this);


//        Get the values just in case
        Log.i(TAG, "onCreate:the values are: " + editedTaskName.getText());

    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.deleteButton) {
            Log.i(TAG, "onClick: delete button");

            // Delete the task
            taskBox.remove(task);
            Toast.makeText(this, "The task was deleted", Toast.LENGTH_SHORT).show();
            finish();

        } else if (v.getId() == R.id.saveButton) {
//            Get the values from the views
            String tName = String.valueOf(editedTaskName.getText());

            String tDueDate = String.valueOf(editedDueDate.getText());
            String tReminder = String.valueOf(editedReminder.getText());

            task.setTaskName(tName);
            taskBox.put(task);
            Toast.makeText(this, "The task was edited " + tName, Toast.LENGTH_SHORT).show();
            finish();


        } else if (v.getId() == R.id.cancelButton) {
            finish();
        }

    }
}