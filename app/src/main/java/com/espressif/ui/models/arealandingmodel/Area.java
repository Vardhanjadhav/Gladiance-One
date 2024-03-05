
package com.espressif.ui.models.arealandingmodel;

import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class Area {

    @SerializedName("GAAProjectSpaceTypeAreaRef")
    @Expose
    private Long gAAProjectSpaceTypeAreaRef;
    @SerializedName("GAAProjectSpaceTypeAreaName")
    @Expose
    private String gAAProjectSpaceTypeAreaName;
    @SerializedName("WifiSSID")
    @Expose
    private String wifiSSID;
    @SerializedName("WifiPassword")
    @Expose
    private String wifiPassword;
    @SerializedName("GuestControls")
    @Expose
    private List<Object> guestControls;
    @SerializedName("InstallerControls")
    @Expose
    private List<InstallerControl> installerControls;



    public Long getGAAProjectSpaceTypeAreaRef() {
        return gAAProjectSpaceTypeAreaRef;
    }

    public void setGAAProjectSpaceTypeAreaRef(Long gAAProjectSpaceTypeAreaRef) {
        this.gAAProjectSpaceTypeAreaRef = gAAProjectSpaceTypeAreaRef;
    }

    public String getGAAProjectSpaceTypeAreaName() {
        return gAAProjectSpaceTypeAreaName;
    }

    public void setGAAProjectSpaceTypeAreaName(String gAAProjectSpaceTypeAreaName) {
        this.gAAProjectSpaceTypeAreaName = gAAProjectSpaceTypeAreaName;
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

    public List<Object> getGuestControls() {
        return guestControls;
    }

    public void setGuestControls(List<Object> guestControls) {
        this.guestControls = guestControls;
    }

    public List<InstallerControl> getInstallerControls() {
        return installerControls;
    }

    public void setInstallerControls(List<InstallerControl> installerControls) {
        this.installerControls = installerControls;
    }

    public Area(Long gAAProjectSpaceTypeAreaRef, String gAAProjectSpaceTypeAreaName, String wifiSSID, String wifiPassword, List<Object> guestControls, List<InstallerControl> installerControls) {
        this.gAAProjectSpaceTypeAreaRef = gAAProjectSpaceTypeAreaRef;
        this.gAAProjectSpaceTypeAreaName = gAAProjectSpaceTypeAreaName;
        this.wifiSSID = wifiSSID;
        this.wifiPassword = wifiPassword;
        this.guestControls = guestControls;
        this.installerControls = installerControls;
    }
}
