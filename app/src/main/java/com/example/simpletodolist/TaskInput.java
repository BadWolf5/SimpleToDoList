package com.example.simpletodolist;

import android.annotation.SuppressLint;
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
    private String taskName;
    private final String TAG = "BadWolf";

    @SuppressLint("SimpleDateFormat")
    private SimpleDateFormat dateFormat;


    //Allocate the Add Button and the rest of the things
    private Button addButton;
    private EditText taskInput;
    private ImageView calendarBtn;
    private ImageView reminderBtn;
    private ImageView repeatBtn;
    private TextView dueLabel;
    private TextView reminderLabel;
    private TextView repeatLabel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_input);

        //Declare the box
        taskBox = ObjectBox.getBoxStore().boxFor(Task.class);

        dateFormat = new SimpleDateFormat(getString(R.string.datePattern));

        //Allocate the Add Button and the rest of the things
        setAddButton(findViewById(R.id.addTaskButton));
        setTaskInput(findViewById(R.id.taskInputField));
        setCalendarBtn(findViewById(R.id.calendarButton));
        setReminderBtn(findViewById(R.id.reminderButton));
        setRepeatBtn(findViewById(R.id.repeatButton));

        setDueLabel(findViewById(R.id.calDate));
        setReminderLabel(findViewById(R.id.reminderDate));
        setRepeatLabel(findViewById(R.id.repeatDate));


//        Configuration of the textview
        getDueLabel().setVisibility(View.GONE);
        getDueLabel().setText(null);

        getReminderLabel().setVisibility(View.GONE);
        getReminderLabel().setText(null);

        getRepeatLabel().setVisibility(View.GONE);
        getRepeatLabel().setText(null);
//        Add Button Functionality
        getAddButton().setOnClickListener((View view) -> {
//            Get Values of textview
            taskName = String.valueOf(getTaskInput().getText());
            Date dueDate = getDataFor(dueLabel);
            Date remindDate = getDataFor(reminderLabel);
            String repeatDate = String.valueOf(repeatLabel.getText());

//            Set the values into the object
            task = new Task();
            task.setTaskName(taskName);
            task.setDueDate(dueDate);
            task.setRemindMe(remindDate);
            task.setRepeat(repeatDate);
            taskBox.put(task);
            finish();

        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }


    @Override
    public void onClick(View v) {
        String title;
        if (v.getId() == R.id.calendarButton) {
            setDueLabel(findViewById(R.id.calDate));
            title = "Set due date";
            openDialog(getDueLabel(), title);

        } else if (v.getId() == R.id.reminderButton) {
            title = "Set remainder";
            setReminderLabel(findViewById(R.id.reminderDate));
            openDialog(getReminderLabel(), title);
        } else if (v.getId() == R.id.repeatButton) {
            title = " Repeat task";
            setRepeatLabel(findViewById(R.id.repeatDate));
            openDialog(getRepeatLabel(), title);
        } else if (v.getId() == R.id.addTaskButton) {
// #Repair

        }

    }

    private void openDialog(TextView t, String title) {


//        Get the current date
        Calendar calendar = Calendar.getInstance();
        int currentYear = calendar.get(Calendar.YEAR);
        int currentMonth = calendar.get(Calendar.MONTH);
        int currentDay = calendar.get(Calendar.DAY_OF_MONTH);

//        Set the format in which to display date (01-01-1998)
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

    private Date getDataFor(TextView txtView) {
        Date date;
        if (!txtView.getText().toString().isEmpty() || !txtView.getText().equals("")) {
            try {
                date = dateFormat.parse(txtView.getText().toString());
                return date;
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }

        } else {
            return null;
        }
    }

    public Button getAddButton() {
        return addButton;
    }

    public void setAddButton(Button addButton) {
        this.addButton = addButton;
    }

    public EditText getTaskInput() {
        return taskInput;
    }

    public void setTaskInput(EditText taskInput) {
        this.taskInput = taskInput;
    }

    public ImageView getCalendarBtn() {
        return calendarBtn;
    }

    public void setCalendarBtn(ImageView calendarBtn) {
        this.calendarBtn = calendarBtn;
    }

    public ImageView getReminderBtn() {
        return reminderBtn;
    }

    public void setReminderBtn(ImageView reminderBtn) {
        this.reminderBtn = reminderBtn;
    }

    public ImageView getRepeatBtn() {
        return repeatBtn;
    }

    public void setRepeatBtn(ImageView repeatBtn) {
        this.repeatBtn = repeatBtn;
    }

    public TextView getDueLabel() {
        return dueLabel;
    }

    public void setDueLabel(TextView dueLabel) {
        this.dueLabel = dueLabel;
    }

    public TextView getReminderLabel() {
        return reminderLabel;
    }

    public void setReminderLabel(TextView reminderLabel) {
        this.reminderLabel = reminderLabel;
    }

    public TextView getRepeatLabel() {
        return repeatLabel;
    }

    public void setRepeatLabel(TextView repeatLabel) {
        this.repeatLabel = repeatLabel;
    }
}