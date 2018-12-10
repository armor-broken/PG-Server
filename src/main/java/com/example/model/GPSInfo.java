package com.example.model;

public class GPSInfo {
    public String getLongitude() {
        return longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public int getSatellitenum() {
        return satellitenum;
    }

    public String getAltitude() {
        return altitude;
    }

    public String getUTC() {
        return UTC;
    }

    public void setLongitude(String longitude) {

        this.longitude = longitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public void setSatellitenum(int satellitenum) {
        this.satellitenum = satellitenum;
    }

    public void setAltitude(String altitude) {
        this.altitude = altitude;
    }

    public void setUTC(String UTC) {
        this.UTC = UTC;
    }

    public void setPostionstate(String postionstate) {
        this.postionstate = postionstate;
    }

    public String getPostionstate() {

        return postionstate;
    }

    String longitude;
    String latitude;
    int satellitenum;
    String altitude;
    String postionstate;
    String UTC;
}
