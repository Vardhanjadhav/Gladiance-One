package com.espressif.ui.models.lnstallerlandingpage;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Data {
    @SerializedName("Version")
    @Expose
    private String version;
    @SerializedName("GAAProjectSpaceRef")
    @Expose
    private long gaaProjectSpaceRef;
    @SerializedName("GAAProjectSpaceName")
    @Expose
    private String gaaProjectSpaceName;
    @SerializedName("GAAProjectSpaceTypeAreaRef")
    @Expose
    private long gaaProjectSpaceTypeAreaRef;
    @SerializedName("GAAProjectSpaceTypeAreaName")
    @Expose
    private String gaaProjectSpaceTypeAreaName;
    @SerializedName("WifiSSID")
    @Expose
    private String wifiSSID;
    @SerializedName("WifiPassword")
    @Expose
    private String wifiPassword;
    @SerializedName("InstallerControls")
    @Expose
    private List<InstallerControl> installerControls;

    // Getters and setters

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public long getGaaProjectSpaceRef() {
        return gaaProjectSpaceRef;
    }

    public void setGaaProjectSpaceRef(long gaaProjectSpaceRef) {
        this.gaaProjectSpaceRef = gaaProjectSpaceRef;
    }

    public String getGaaProjectSpaceName() {
        return gaaProjectSpaceName;
    }

    public void setGaaProjectSpaceName(String gaaProjectSpaceName) {
        this.gaaProjectSpaceName = gaaProjectSpaceName;
    }

    public long getGaaProjectSpaceTypeAreaRef() {
        return gaaProjectSpaceTypeAreaRef;
    }

    public void setGaaProjectSpaceTypeAreaRef(long gaaProjectSpaceTypeAreaRef) {
        this.gaaProjectSpaceTypeAreaRef = gaaProjectSpaceTypeAreaRef;
    }

    public String getGaaProjectSpaceTypeAreaName() {
        return gaaProjectSpaceTypeAreaName;
    }

    public void setGaaProjectSpaceTypeAreaName(String gaaProjectSpaceTypeAreaName) {
        this.gaaProjectSpaceTypeAreaName = gaaProjectSpaceTypeAreaName;
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

    public List<InstallerControl> getInstallerControls() {
        return installerControls;
    }

    public void setInstallerControls(List<InstallerControl> installerControls) {
        this.installerControls = installerControls;
    }

}
