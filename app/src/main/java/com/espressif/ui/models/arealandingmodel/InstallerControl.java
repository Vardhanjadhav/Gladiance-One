
package com.espressif.ui.models.arealandingmodel;

import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class InstallerControl {

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
