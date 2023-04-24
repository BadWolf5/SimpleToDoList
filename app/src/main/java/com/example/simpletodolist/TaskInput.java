package com.example.simpletodolist;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import io.objectbox.Box;

public class TaskInput extends AppCompatActivity implements View.OnClickListener {
    //    Declare the variables
    private Box<Task> taskBox;
    private Task task;
    private Date date;
    private String text;
    private final String TAG = "BadWolf";

    //Allocate the Add Button and the rest of the things
    Button addButton;
    EditText taskInput;
    ImageView calendarBtn;
    ImageView reminderBtn;
    ImageView repeatBtn;
    TextView dueLabel;
    TextView reminderLabel;
    TextView repeatLabel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_input);

        //Declare the box
        taskBox = ObjectBox.getBoxStore().boxFor(Task.class);

        //Allocate the Add Button and the rest of the things
        addButton = findViewById(R.id.addTaskButton);
        taskInput = findViewById(R.id.taskInputField);
        calendarBtn = findViewById(R.id.calendarButton);
        reminderBtn = findViewById(R.id.reminderButton);
        repeatBtn = findViewById(R.id.repeatButton);

        dueLabel = findViewById(R.id.calDate);
        reminderLabel = findViewById(R.id.reminderDate);
        repeatLabel = findViewById(R.id.repeatDate);


//        Configuration of the textview
        dueLabel.setVisibility(View.GONE);
        dueLabel.setText(null);

        reminderLabel.setVisibility(View.GONE);
        reminderLabel.setText(null);

        repeatLabel.setVisibility(View.GONE);
        repeatLabel.setText(null);
//        Add Button Functionality
        addButton.setOnClickListener((View view) -> {
            text = String.valueOf(taskInput.getText());
            task = new Task();
            task.setTaskName(text);
            taskBox.put(task);
            finish();

        });
    }

    @Override
    public void onClick(View v) {
        String title;
        if (v.getId() == R.id.calendarButton) {
            dueLabel = findViewById(R.id.calDate);
            title = "Set due date";
            openDialog(dueLabel, title);

        } else if (v.getId() == R.id.reminderButton) {
            title = "Set remainder";
            reminderLabel = findViewById(R.id.reminderDate);
            openDialog(reminderLabel, title);
        } else if (v.getId() == R.id.repeatButton) {
            title = " Repeat task";
            repeatLabel = findViewById(R.id.repeatDate);
            openDialog(repeatLabel, title);
        } else if (v.getId() == R.id.addTaskButton) {



        }

    }

    private void openDialog(TextView t, String title) {

//        Get the current date
        Calendar calendar = Calendar.getInstance();
        int currentYear = calendar.get(Calendar.YEAR);
        int currentMonth = calendar.get(Calendar.MONTH);
        int currentDay = calendar.get(Calendar.DAY_OF_MONTH);

//        Set the format in which to display date (01-01-1998)
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        DatePickerDialog dateDialog = new DatePickerDialog(this, (DatePicker view, int day, int month, int year) -> {
//            Date Picked by the user and set it to the textview
            Calendar calendar1 = Calendar.getInstance();
            calendar1.set(day, month, year);
            String selectedDate = dateFormat.format(calendar1.getTime());
            t.setText(selectedDate);
            t.setVisibility(View.VISIBLE);

        }, currentYear, currentMonth, currentDay);
        dateDialog.setTitle(title);
//        When cancel is pressed, it would delete the text and make it invisible
        dateDialog.setOnCancelListener(dialog -> {
            t.setText("");
            t.setVisibility(View.GONE);

        });
        dateDialog.show();

    }

    private Date getDataFor(TextView txtView){
        Date date;
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        String taskName = String.valueOf(taskInput.getText());
        if (!txtView.getText().toString().isEmpty() || !txtView.getText().equals("")) {
            try {
                date = dateFormat.parse(txtView.getText().toString());
                return date;
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }


        } else {
            return date = null;
        }
    }
}