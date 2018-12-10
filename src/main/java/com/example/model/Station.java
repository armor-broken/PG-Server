package com.example.model;

public class Station {
    /**
     * 台站id
     */
    private int id ;

    public int getId() {
        return id;
    }

    public int getTaskId() {
        return taskId;
    }

    public String getStationName() {
        return stationName;
    }

    public String getStationLon() {
        return stationLon;
    }

    public String getStationLat() {
        return stationLat;
    }

    public int getStationAlt() {
        return stationAlt;
    }

    public int getStationAntHei() {
        return stationAntHei;
    }

    public String getStationAntForm() {
        return stationAntForm;
    }

    public String getStationAntOri() {
        return stationAntOri;
    }

    public String getStationPower() {
        return stationPower;
    }

    public Boolean getSyn() {
        return isSyn;
    }

    public void setId(int id) {

        this.id = id;
    }

    public void setTaskId(int taskId) {
        this.taskId = taskId;
    }

    public void setStationName(String stationName) {
        this.stationName = stationName;
    }

    public void setStationLon(String stationLon) {
        this.stationLon = stationLon;
    }

    public void setStationLat(String stationLat) {
        this.stationLat = stationLat;
    }

    public void setStationAlt(int stationAlt) {
        this.stationAlt = stationAlt;
    }

    public void setStationAntHei(int stationAntHei) {
        this.stationAntHei = stationAntHei;
    }

    public void setStationAntForm(String stationAntForm) {
        this.stationAntForm = stationAntForm;
    }

    public void setStationAntOri(String stationAntOri) {
        this.stationAntOri = stationAntOri;
    }

    public void setStationPower(String stationPower) {
        this.stationPower = stationPower;
    }

    public void setSyn(Boolean syn) {
        isSyn = syn;
    }

    /**
     * 台站id
     */
    private int taskId;
    /**
     * 站点名称
     */
    private String stationName;
    /**
     * 经度
     */
    private String stationLon;
    /**
     *纬度
     */
    private String stationLat;
    /**
     * 海拔
     */
    private int stationAlt;
    /**
     *天线挂高
     */
    private int stationAntHei;
    /**
     * 天线形式
     */
    private String stationAntForm;
    /**
     *天线朝向
     */
    private String stationAntOri;
    /**
     *发射功率
     */
    private String stationPower;
    /**
     *调频同步广播
     */
    private Boolean isSyn;
}
