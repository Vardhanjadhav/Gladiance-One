package com.espressif.ui.models.arealandingmodel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Controls {
    @SerializedName("NodeId")
    @Expose
    private int nodeId;

    @SerializedName("DisplayOrder")
    @Expose
    private int displayOrder;

    @SerializedName("GAAProjectSpaceTypePlannedDeviceRef")
    @Expose
    private int gaaProjectSpaceTypePlannedDeviceRef;

    @SerializedName("GAAProjectSpaceTypePlannedDeviceName")
    @Expose
    private String gaaProjectSpaceTypePlannedDeviceName;

    @SerializedName("IsProvisioned")
    @Expose
    private boolean isProvisioned;

    public int getNodeId() {
        return nodeId;
    }

    public void setNodeId(int nodeId) {
        this.nodeId = nodeId;
    }

    public int getDisplayOrder() {
        return displayOrder;
    }

    public void setDisplayOrder(int displayOrder) {
        this.displayOrder = displayOrder;
    }

    public int getGaaProjectSpaceTypePlannedDeviceRef() {
        return gaaProjectSpaceTypePlannedDeviceRef;
    }

    public void setGaaProjectSpaceTypePlannedDeviceRef(int gaaProjectSpaceTypePlannedDeviceRef) {
        this.gaaProjectSpaceTypePlannedDeviceRef = gaaProjectSpaceTypePlannedDeviceRef;
    }

    public String getGaaProjectSpaceTypePlannedDeviceName() {
        return gaaProjectSpaceTypePlannedDeviceName;
    }

    public void setGaaProjectSpaceTypePlannedDeviceName(String gaaProjectSpaceTypePlannedDeviceName) {
        this.gaaProjectSpaceTypePlannedDeviceName = gaaProjectSpaceTypePlannedDeviceName;
    }

    public boolean isProvisioned() {
        return isProvisioned;
    }

    public void setProvisioned(boolean provisioned) {
        isProvisioned = provisioned;
    }

    public Controls(int nodeId, int displayOrder, int gaaProjectSpaceTypePlannedDeviceRef, String gaaProjectSpaceTypePlannedDeviceName, boolean isProvisioned) {
        this.nodeId = nodeId;
        this.displayOrder = displayOrder;
        this.gaaProjectSpaceTypePlannedDeviceRef = gaaProjectSpaceTypePlannedDeviceRef;
        this.gaaProjectSpaceTypePlannedDeviceName = gaaProjectSpaceTypePlannedDeviceName;
        this.isProvisioned = isProvisioned;
    }
}
