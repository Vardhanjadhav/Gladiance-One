package com.espressif.ui.models;

import android.provider.ContactsContract;

import com.google.gson.annotations.SerializedName;

public class ProjectSpaceLandingResModel {

    private boolean Successful;
    private String Message;
    private ProjectSpaceLandingReqModel Data;

    public boolean isSuccessful() {
        return Successful;
    }

    public void setSuccessful(boolean successful) {
        Successful = successful;
    }

    public String getMessage() {
        return Message;
    }

    public void setMessage(String message) {
        Message = message;
    }

    public ProjectSpaceLandingReqModel getData() {
        return Data;
    }

    public void setData(ProjectSpaceLandingReqModel data) {
        Data = data;
    }

    public ProjectSpaceLandingResModel(boolean successful, String message, ProjectSpaceLandingReqModel data) {
        Successful = successful;
        Message = message;
        Data = data;
    }
}
