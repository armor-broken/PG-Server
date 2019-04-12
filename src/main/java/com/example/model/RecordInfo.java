package com.example.model;

public class RecordInfo {
    public int getId() {
        return id;
    }

    public String getStartTime() {
        return startTime;
    }

    public String getRecordName() {
        return recordName;
    }

    public String getStopTime() {
        return stopTime;
    }

    public void setId(int id) {

        this.id = id;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public void setRecordName(String recordName) {
        this.recordName = recordName;
    }

    public void setStopTime(String stopTime) {
        this.stopTime = stopTime;
    }

    private int id;
    private String startTime;
    private String recordName;
    private String stopTime;
}
