package com.example.model;

import java.util.List;

/**
 * 任务表实体
 */
public class TaskInfo {
    private int id;
    private int catId;
    private String name;
    private String cat;
    private String date;

    public void setDate(String date) {
        this.date = date;
    }

    public String getDate() {
        return date;
    }

    public String getCat() {
        return cat;
    }

    public void setCat(String cat) {
        this.cat = cat;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setCatId(int catId) {
        this.catId = catId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public int getCatId() {
        return catId;
    }

    public String getName() {
        return name;
    }

    /**
     * 台站id
     */
    private int stationId;
    /**
     * 台站联系人id
     */
    private int stationConId;
    /**
     * 站点名称
     */
    private String stationName;

    public void setFreId(int freId) {
        this.freId = freId;
    }

    public int getFreId() {
        return freId;
    }

    private int freId;
    /**
     * 频点
     */
    private int taskFre;
    /**
     * 节目名称
     */
    private String taskProgram;
    /**
     * 经度
     */
    private String stationLon;
    /**
     * 纬度
     */
    private String stationLat;
    /**
     * 海拔
     */
    private int stationAlt;
    /**
     * 天线挂高
     */
    private int stationAntHei;
    /**
     * 天线形式
     */
    private String stationAntForm;
    /**
     * 天线朝向
     */
    private String stationAntOri;
    /**
     * 发射功率
     */
    private String stationPower;
    /**
     * 调频同步广播
     */
    private Boolean stationSyn;

    public int getStationId() {
        return stationId;
    }

    public void setStationId(int stationId) {
        this.stationId = stationId;
    }

    public void setStationConId(int stationConId) {
        this.stationConId = stationConId;
    }

    public int getStationConId() {
        return stationConId;
    }

    public void setStationName(String stationName) {
        this.stationName = stationName;
    }

    public String getStationName() {
        return stationName;
    }


    public Boolean getStationSyn() {
        return stationSyn;
    }

    public void settaskProgram(String taskProgram) {
        this.taskProgram = taskProgram;
    }

    public String gettaskProgram() {
        return taskProgram;
    }

    public void setStationLon(String stationLon) {
        this.stationLon = stationLon;
    }

    public String getStationLon() {
        return stationLon;
    }

    public void setStationLat(String stationLat) {
        this.stationLat = stationLat;
    }

    public String getStationLat() {
        return stationLat;
    }

    public void setStationAlt(int stationAlt) {
        this.stationAlt = stationAlt;
    }

    public int getStationAlt() {
        return stationAlt;
    }

    public void setStationAntForm(String stationAntForm) {
        this.stationAntForm = stationAntForm;
    }

    public String getStationAntForm() {
        return stationAntForm;
    }

    public void setStationAntHei(int stationAntHei) {
        this.stationAntHei = stationAntHei;
    }

    public int getStationAntHei() {
        return stationAntHei;
    }

    public void setStationAntOri(String stationAntOri) {
        this.stationAntOri = stationAntOri;
    }

    public String getStationAntOri() {
        return stationAntOri;
    }

    public void setStationPower(String stationPower) {
        this.stationPower = stationPower;
    }

    public String getStationPower() {
        return stationPower;
    }

    public void setStationSyn(Boolean stationSyn) {
        this.stationSyn = stationSyn;
    }

    public int gettaskFre() {
        return taskFre;
    }

    public void settaskFre(int taskFre) {
        this.taskFre = taskFre;
    }

}
