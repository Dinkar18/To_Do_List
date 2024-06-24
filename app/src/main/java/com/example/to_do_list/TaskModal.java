package com.example.to_do_list;

public class TaskModal  {
    private int id;
    private String taskName, completionDate, completionTime;

    public TaskModal(int id, String taskName, String completionDate, String completionTime) {
        this.id = id;
        this.taskName = taskName;
        this.completionDate = completionDate;
        this.completionTime = completionTime;
    }

    public TaskModal(String taskName, String completionDate, String completionTime) {
        this.taskName = taskName;
        this.completionDate = completionDate;
        this.completionTime = completionTime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public String getCompletionDate() {
        return completionDate;
    }

    public void setCompletionDate(String completionDate) {
        this.completionDate = completionDate;
    }

    public String getCompletionTime() {
        return completionTime;
    }

    public void setCompletionTime(String completionTime) {
        this.completionTime = completionTime;
    }
}
