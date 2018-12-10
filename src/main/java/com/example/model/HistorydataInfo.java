package com.example.model;

public class HistorydataInfo {
    public void setLng(String lng) {
        this.lng = lng;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public void setRF(String RF) {
        this.RF = RF;
    }

    public void setMpath(String mpath) {
        Mpath = mpath;
    }

    public String getLng() {
        return lng;
    }

    public String getLat() {
        return lat;
    }

    public String getRF() {
        return RF;
    }

    public String getMpath() {
        return Mpath;
    }

    private String lng;
    private String lat;
    private String RF;
    private String Mpath;
}
