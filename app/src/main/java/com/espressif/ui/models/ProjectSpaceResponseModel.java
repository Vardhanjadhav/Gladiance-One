package com.espressif.ui.models;

public class ProjectSpaceResponseModel {

    private boolean Successful;
    private String Message;
    private ProjectSpaceRequestModel Data;

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

    public ProjectSpaceRequestModel getData() {
        return Data;
    }

    public void setData(ProjectSpaceRequestModel data) {
        Data = data;
    }

    public ProjectSpaceResponseModel(boolean successful, String message, ProjectSpaceRequestModel data) {
        Successful = successful;
        Message = message;
        Data = data;
    }
}
