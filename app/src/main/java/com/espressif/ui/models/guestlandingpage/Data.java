package com.espressif.ui.models.guestlandingpage;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Data {
    @SerializedName("Version")
    @Expose
    private String version;
    @SerializedName("GAAProjectSpaceRef")
    @Expose
    private Long gAAProjectSpaceRef;
    @SerializedName("GAAProjectSpaceName")
    @Expose
    private String gAAProjectSpaceName;
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
    private List<GuestControls> guestControls;

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public Long getgAAProjectSpaceRef() {
        return gAAProjectSpaceRef;
    }

    public void setgAAProjectSpaceRef(Long gAAProjectSpaceRef) {
        this.gAAProjectSpaceRef = gAAProjectSpaceRef;
    }

    public String getgAAProjectSpaceName() {
        return gAAProjectSpaceName;
    }

    public void setgAAProjectSpaceName(String gAAProjectSpaceName) {
        this.gAAProjectSpaceName = gAAProjectSpaceName;
    }

    public Long getgAAProjectSpaceTypeAreaRef() {
        return gAAProjectSpaceTypeAreaRef;
    }

    public void setgAAProjectSpaceTypeAreaRef(Long gAAProjectSpaceTypeAreaRef) {
        this.gAAProjectSpaceTypeAreaRef = gAAProjectSpaceTypeAreaRef;
    }

    public String getgAAProjectSpaceTypeAreaName() {
        return gAAProjectSpaceTypeAreaName;
    }

    public void setgAAProjectSpaceTypeAreaName(String gAAProjectSpaceTypeAreaName) {
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

    public List<GuestControls> getGuestControls() {
        return guestControls;
    }

    public void setGuestControls(List<GuestControls> guestControls) {
        this.guestControls = guestControls;
    }
}
