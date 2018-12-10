package com.example.model;

import java.util.List;

public class IDListInfo {
    private List id;
    private String device1IP;
    private String device2IP;

    public void setDevice2IP(String device2IP) {
        this.device2IP = device2IP;
    }

    public String getDevice2IP() {

        return device2IP;
    }

    public void setDevice1IP(String device1IP) {
        this.device1IP = device1IP;
    }

    public String getDevice1IP() {

        return device1IP;
    }

    public void setId(List id) {
        this.id = id;
    }

    public List getId() {
        return id;
    }
}
