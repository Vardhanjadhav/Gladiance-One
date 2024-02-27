package com.espressif.ui.models;

public class ProjectSpaceGroupResModel {

    private boolean Successful;
    private String Message;
    private ProjectSpaceGroupReqModel Data;

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

    public ProjectSpaceGroupReqModel getData() {
        return Data;
    }

    public void setData(ProjectSpaceGroupReqModel data) {
        Data = data;
    }

    public ProjectSpaceGroupResModel(boolean successful, String message, ProjectSpaceGroupReqModel data) {
        Successful = successful;
        Message = message;
        Data = data;
    }
}
