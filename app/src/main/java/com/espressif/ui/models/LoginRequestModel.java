package com.espressif.ui.models;

import com.google.gson.annotations.SerializedName;

public class LoginRequestModel {

    @SerializedName("UserId")
    private String userId;

    @SerializedName("Password")
    private String password;

    @SerializedName("LoginDeviceId")
    private String loginDeviceId;

    public LoginRequestModel(String userId, String password, String loginDeviceId) {
        this.userId = userId;
        this.password = password;
        this.loginDeviceId = loginDeviceId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLoginDeviceId() {
        return loginDeviceId;
    }

    public void setLoginDeviceId(String loginDeviceId) {
        this.loginDeviceId = loginDeviceId;
    }
}
