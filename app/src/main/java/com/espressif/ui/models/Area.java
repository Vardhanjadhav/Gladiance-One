package com.espressif.ui.models;

public class Area {
    private String GAAProjectSpaceTypeAreaRef;
    private String GAAProjectSpaceTypeAreaName;
    private String wifiSSID;
    private String wifiPassword;

    public String getGAAProjectSpaceTypeAreaRef() {
        return GAAProjectSpaceTypeAreaRef;
    }

    public void setGAAProjectSpaceTypeAreaRef(String GAAProjectSpaceTypeAreaRef) {
        this.GAAProjectSpaceTypeAreaRef = GAAProjectSpaceTypeAreaRef;
    }

    public String getGAAProjectSpaceTypeAreaName() {
        return GAAProjectSpaceTypeAreaName;
    }

    public void setGAAProjectSpaceTypeAreaName(String GAAProjectSpaceTypeAreaName) {
        this.GAAProjectSpaceTypeAreaName = GAAProjectSpaceTypeAreaName;
    }

    public String getWifiSSID() {
        return wifiSSID;
    }

    public void setWifiSSID(String wifiSSID) {
        this.wifiSSID = wifiSSID;
    }

    public String getWifiPassword() {
        return wifiPassword;
    }

    public void setWifiPassword(String wifiPassword) {
        this.wifiPassword = wifiPassword;
    }

    public Area(String GAAProjectSpaceTypeAreaRef, String GAAProjectSpaceTypeAreaName, String wifiSSID, String wifiPassword) {
        this.GAAProjectSpaceTypeAreaRef = GAAProjectSpaceTypeAreaRef;
        this.GAAProjectSpaceTypeAreaName = GAAProjectSpaceTypeAreaName;
        this.wifiSSID = wifiSSID;
        this.wifiPassword = wifiPassword;
    }
}
