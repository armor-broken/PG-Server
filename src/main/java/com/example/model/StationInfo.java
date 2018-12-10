package com.example.model;

public class StationInfo {
    /**
     * 台站id
     */
    private int stationId ;
    /**
     * 台站联系人id
     */
    private int stationConId ;
    /**
     * 站点名称
     */
    private String stationName;
    /**
     * 频点
     */
    private int stationFre;
    /**
     *节目名称
     */
    private String stationProgram;
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

    public void setStationFre(int stationFre) {
        this.stationFre = stationFre;
    }

    public Boolean getStationSyn() {
        return stationSyn;
    }

    public void setStationProgram(String stationProgram) {
        this.stationProgram = stationProgram;
    }
    public String getStationProgram() {
        return stationProgram;
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

    public int getStationFre() {
        return stationFre;
    }
}
