package com.espressif.ui.models.guestlandingpage;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Controls {
    @SerializedName("Label")
    @Expose
    private String label;
    @SerializedName("NodeId")
    @Expose
    private String nodeId;
    @SerializedName("InternalDeviceName")
    @Expose
    private String internalDeviceName;
    @SerializedName("DisplayOrder")
    @Expose
    private Integer displayOrder;
    @SerializedName("GAAProjectSpaceTypePlannedDeviceRef")
    @Expose
    private Long gAAProjectSpaceTypePlannedDeviceRef;

    @SerializedName("GAAProjectSpaceTypePlannedDeviceName")
    @Expose
    private String gAAProjectSpaceTypePlannedDeviceName;

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getNodeId() {
        return nodeId;
    }

    public void setNodeId(String nodeId) {
        this.nodeId = nodeId;
    }

    public String getInternalDeviceName() {
        return internalDeviceName;
    }

    public void setInternalDeviceName(String internalDeviceName) {
        this.internalDeviceName = internalDeviceName;
    }

    public Integer getDisplayOrder() {
        return displayOrder;
    }

    public void setDisplayOrder(Integer displayOrder) {
        this.displayOrder = displayOrder;
    }

    public Long getgAAProjectSpaceTypePlannedDeviceRef() {
        return gAAProjectSpaceTypePlannedDeviceRef;
    }

    public void setgAAProjectSpaceTypePlannedDeviceRef(Long gAAProjectSpaceTypePlannedDeviceRef) {
        this.gAAProjectSpaceTypePlannedDeviceRef = gAAProjectSpaceTypePlannedDeviceRef;
    }

    public String getgAAProjectSpaceTypePlannedDeviceName() {
        return gAAProjectSpaceTypePlannedDeviceName;
    }

    public void setgAAProjectSpaceTypePlannedDeviceName(String gAAProjectSpaceTypePlannedDeviceName) {
        this.gAAProjectSpaceTypePlannedDeviceName = gAAProjectSpaceTypePlannedDeviceName;
    }
}
