package com.espressif.ui.models;

import com.google.gson.annotations.SerializedName;

public class LogoutRequestModel {

    @SerializedName("LoginToken")
    private String userId;
    @SerializedName("LoginDeviceId")
    private String loginDeviceId;

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

    public LogoutRequestModel(String userId, String loginDeviceId) {
        this.userId = userId;
        this.loginDeviceId = loginDeviceId;
    }
}