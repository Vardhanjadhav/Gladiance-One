package com.espressif.ui.models;

import com.google.gson.annotations.SerializedName;

public class SpaceSpaceGroupResModel {

    @SerializedName("Successful")
    private boolean successful;

    @SerializedName("Message")
    private String message;

    @SerializedName("Data")
    private SpaceSpaceGroupReqModel Date;

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

    public SpaceSpaceGroupReqModel getDate() {
        return Date;
    }

    public void setDate(SpaceSpaceGroupReqModel date) {
        Date = date;
    }

    public SpaceSpaceGroupResModel(boolean successful, String message, SpaceSpaceGroupReqModel date) {
        this.successful = successful;
        this.message = message;
        Date = date;
    }
}
