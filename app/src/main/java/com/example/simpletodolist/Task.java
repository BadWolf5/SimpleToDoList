package com.example.simpletodolist;

import java.util.Date;

import io.objectbox.annotation.Entity;
import io.objectbox.annotation.Id;

@Entity
public class Task {
    @Id
    private long id;
    private String taskName;
    private Date dueDate;
    private Date remindMe;
    private String repeat;
    private Boolean completed;
    private String list;

    //region Constructors //
    public Task() {
        this.taskName = null;
        this.dueDate = null;
        this.remindMe = null;
        this.repeat = null;
        this.completed = false;
        this.list = null;

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
        this.list = list;
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
