package com.example.model;

import java.util.List;

/**
 * 初始化响应数据
 */
public class InitResponse {
    private String satelliteNum;
    private List<Integer> list;

    private String portState;
    private String satelliteState;
    private String deviceState;
    private String deviceIPState;

    public String getPortState() {
        return portState;
    }

    public String getSatelliteState() {
        return satelliteState;
    }

    public String getDeviceState() {
        return deviceState;
    }

    public String getDeviceIPState() {
        return deviceIPState;
    }

    public void setPortState(String portState) {
        this.portState = portState;
    }

    public void setSatelliteState(String satelliteState) {
        this.satelliteState = satelliteState;
    }

    public void setDeviceState(String deviceState) {
        this.deviceState = deviceState;
    }

    public void setDeviceIPState(String deviceIPState) {
        this.deviceIPState = deviceIPState;
    }

    public void setSatelliteNum(String satelliteNum) {
        this.satelliteNum = satelliteNum;
    }

    public String getSatelliteNum() {

        return satelliteNum;
    }



    public List<Integer> getList() {
        return list;
    }


    public void setList(List<Integer> list) {
        this.list = list;
    }


}
