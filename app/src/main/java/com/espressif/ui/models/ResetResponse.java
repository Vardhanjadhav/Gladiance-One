package com.espressif.ui.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ResetResponse {

    @SerializedName("Successful")
    @Expose
    private boolean successful;
    @SerializedName("Message")
    @Expose
    private String message;

    public boolean getSuccessful() {
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

}
