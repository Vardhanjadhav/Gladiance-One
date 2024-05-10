package com.espressif.ui.models.guestlandingpage;

import com.espressif.ui.models.guestlandingpage.Controls;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GuestControls {
    @SerializedName("ControlTypeName")
    @Expose
    private String controlTypeName;
    @SerializedName("Controls")
    @Expose
    private List<Controls> controls;

    public String getControlTypeName() {
        return controlTypeName;
    }

    public void setControlTypeName(String controlTypeName) {
        this.controlTypeName = controlTypeName;
    }

    public List<Controls> getControls() {
        return controls;
    }

    public void setControls(List<Controls> controls) {
        this.controls = controls;
    }
}
