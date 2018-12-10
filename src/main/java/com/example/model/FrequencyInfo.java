package com.example.model;

public class FrequencyInfo {


    public void setTaskId(int taskId) {
        this.taskId = taskId;
    }

    public void setTaskFre(int taskFre) {
        this.taskFre = taskFre;
    }

    public void setTaskProgram(String taskProgram) {
        this.taskProgram = taskProgram;
    }

    public int getTaskId() {
        return taskId;
    }

    public int getTaskFre() {
        return taskFre;
    }

    public String getTaskProgram() {
        return taskProgram;
    }


    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    private int id;
    private int taskId;
    private int taskFre;
    private String taskProgram;


}
