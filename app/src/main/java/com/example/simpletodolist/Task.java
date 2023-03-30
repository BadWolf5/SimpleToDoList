package com.example.simpletodolist;

import android.util.Log;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import io.objectbox.annotation.Entity;
import io.objectbox.annotation.Id;

@Entity
public class Task {
    @Id private long id;
    private String taskName;
    private Date dueDate;
    private Date remindMe;
    private String repeat;
    private Boolean completed;
    private String list;


    /*private Date defaultDate(){
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        calendar.set(Calendar.MONTH, 0);
        calendar.set(Calendar.YEAR, 1111);
        return dateFormat.format(calendar.getTime());

    }*/
    //region Constructors //
    public Task(){
        this.taskName = "default";
        this.dueDate = null;
        this.remindMe = null;
        this.repeat = "no";
        this.completed = false;
        this.list = "default";

    }



    public String getList() {
        return list;
    }

    public void setList(String list) {
        this.list = list;
    }

    public Task(String taskName, Date dueDate, Date remindMe, String repeat, Boolean completed, String list) {
        this.taskName = taskName;
        this.dueDate = dueDate;
        this.remindMe = remindMe;
        this.repeat = repeat;
        this.completed = completed;
        this.list=list;
    }
    //endregion

    //region Getters and Setters
    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public Date getRemindMe() {
        return remindMe;
    }

    public void setRemindMe(Date remindMe) {
        this.remindMe = remindMe;
    }

    public String getRepeat() {
        return repeat;
    }

    public void setRepeat(String repeat) {
        this.repeat = repeat;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public Boolean getCompleted() {
        return completed;
    }

    public void setCompleted(Boolean completed) {
        this.completed = completed;
    }

    //endregion
}
