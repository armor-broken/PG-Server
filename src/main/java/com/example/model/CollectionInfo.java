package com.example.model;

public class CollectionInfo {


    public CollectionInfo(String clcFre, String clcRf, String clcMpath, String clcLon, String clcLat, String clcAlt, int clcSatNum, String clcUTC) {
        this.clcFre = clcFre;
        this.clcRf = clcRf;
        this.clcMpath = clcMpath;
        this.clcLon = clcLon;
        this.clcLat = clcLat;
        this.clcAlt = clcAlt;
        this.clcSatNum = clcSatNum;
        this.clcUTC = clcUTC;
    }

    public CollectionInfo(){}

    private int clcId;
    private String clcFre;
    private String clcRf;
    private String clcMpath;
    private String clcLon;
    private String clcLat;
    private String clcAlt;
    private int clcSatNum;
    private String clcUTC;
    private String clcIdentify;
    private String clcDev;

    public void setClcIdentify(String clcIdentify) {
        this.clcIdentify = clcIdentify;
    }

    public String getClcIdentify() {
        return clcIdentify;
    }

    public void setClcFre(String clcFre) {
        this.clcFre = clcFre;
    }

    public String getClcFre() {
        return clcFre;
    }

    public int getClcId() {
        return clcId;
    }

    public void setClcId(int clcId) {
        this.clcId = clcId;
    }

    public String getClcRf() {
        return clcRf;
    }

    public void setClcRf(String clcRf) {
        this.clcRf = clcRf;
    }

    public String getClcMpath() {
        return clcMpath;
    }

    public void setClcMpath(String clcMpath) {
        this.clcMpath = clcMpath;
    }

    public String getClcLon() {
        return clcLon;
    }

    public void setClcLon(String clcLon) {
        this.clcLon = clcLon;
    }

    public String getClcLat() {
        return clcLat;
    }

    public void setClcLat(String clcLat) {
        this.clcLat = clcLat;
    }

    public String getClcAlt() {
        return clcAlt;
    }

    public void setClcAlt(String clcAlt) {
        this.clcAlt = clcAlt;
    }

    public int getClcSatNum() {
        return clcSatNum;
    }

    public void setClcSatNum(int clcSatNum) {
        this.clcSatNum = clcSatNum;
    }

    public String getClcUTC() {
        return clcUTC;
    }

    public void setClcUTC(String clcUTC) {
        this.clcUTC = clcUTC;
    }

    public String getClcDev() {
        return clcDev;
    }

    public void setClcDev(String clcDev) {
        this.clcDev = clcDev;
    }

}
