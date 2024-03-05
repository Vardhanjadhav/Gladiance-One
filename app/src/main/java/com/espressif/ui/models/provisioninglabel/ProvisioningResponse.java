package com.espressif.ui.models.provisioninglabel;

import com.google.gson.annotations.SerializedName;

public class ProvisioningResponse {
    @SerializedName("Successful")
    private boolean successful;
    @SerializedName("Message")
    private String message;

    // Constructor, getters, and setters

    public ProvisioningResponse(boolean successful, String message) {
        this.successful = successful;
        this.message = message;
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
}
