package com.espressif.ui.models.provisioninglabel;

import com.google.gson.annotations.SerializedName;

public class ProvisioningRequest {
    @SerializedName("LoginToken")
    private String userId;
    @SerializedName("LoginDeviceId")
    private String loginDeviceId;
    @SerializedName("MACId")
    private String macId;
    @SerializedName("GAAProjectSpaceRef")
    private String gaaProjectSpaceRef;
    @SerializedName("GAAProjectSpaceTypePlannedDeviceRef")
    private Long gaaProjectSpaceTypePlannedDeviceRef;
//    @SerializedName("ControlTypeName")
//    private String controlTypeName;

    // Constructor, getters, and setters

    public ProvisioningRequest(String userId, String loginDeviceId, String macId, String gaaProjectSpaceRef, Long gaaProjectSpaceTypePlannedDeviceRef) {
        this.userId = userId;
        this.loginDeviceId = loginDeviceId;
        this.macId = macId;
        this.gaaProjectSpaceRef = gaaProjectSpaceRef;
        this.gaaProjectSpaceTypePlannedDeviceRef = gaaProjectSpaceTypePlannedDeviceRef;
//        this.controlTypeName = controlTypeName;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getLoginDeviceId() {
        return loginDeviceId;
    }

    public void setLoginDeviceId(String loginDeviceId) {
        this.loginDeviceId = loginDeviceId;
    }

    public String getMacId() {
        return macId;
    }

    public void setMacId(String macId) {
        this.macId = macId;
    }

    public String getGaaProjectSpaceRef() {
        return gaaProjectSpaceRef;
    }

    public void setGaaProjectSpaceRef(String gaaProjectSpaceRef) {
        this.gaaProjectSpaceRef = gaaProjectSpaceRef;
    }

    public Long getGaaProjectSpaceTypePlannedDeviceRef() {
        return gaaProjectSpaceTypePlannedDeviceRef;
    }

    public void setGaaProjectSpaceTypePlannedDeviceRef(Long gaaProjectSpaceTypePlannedDeviceRef) {
        this.gaaProjectSpaceTypePlannedDeviceRef = gaaProjectSpaceTypePlannedDeviceRef;
    }

//    public String getControlTypeName() {
//        return controlTypeName;
//    }
//
//    public void setControlTypeName(String controlTypeName) {
//        this.controlTypeName = controlTypeName;
//    }
}
