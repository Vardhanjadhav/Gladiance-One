package com.espressif.ui.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class LoginResponseModel {

    @SerializedName("Successful")
    private boolean successful;

    @SerializedName("Message")
    private String message;

    @SerializedName("LoginToken")
    private String loginToken;

    @SerializedName("UserTypes")
    private List<String> userTypes;

    @SerializedName("UserDisplayName")
    private String userDisplayName;

    public String getUserDisplayName() {
        return userDisplayName;
    }

    public void setUserDisplayName(String userDisplayName) {
        this.userDisplayName = userDisplayName;
    }

    public boolean isSuccessful() {
        return successful;
    }

    public void setSuccessful(boolean successful) {
        this.successful = successful;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getLoginToken() {
        return loginToken;
    }

    public void setLoginToken(String loginToken) {
        this.loginToken = loginToken;
    }

    public List<String> getUserTypes() {
        return userTypes;
    }

    public void setUserTypes(List<String> userTypes) {
        this.userTypes = userTypes;
    }

    public LoginResponseModel(boolean successful, String message, String loginToken, List<String> userTypes, String userDisplayName) {
        this.successful = successful;
        this.message = message;
        this.loginToken = loginToken;
        this.userTypes = userTypes;
        this.userDisplayName = userDisplayName;
    }
}
