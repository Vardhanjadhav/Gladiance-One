package com.espressif.ui.models;

import com.google.gson.annotations.SerializedName;

public class LogoutResponseModel {

    @SerializedName("Successful")
    private boolean successful;
    @SerializedName("Message")
    private String message;

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

    public LogoutResponseModel(boolean successful, String message) {
        this.successful = successful;
        this.message = message;
    }
}