package com.espressif.ui.models.lnstallerlandingpage;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class InstallerControl {
    @SerializedName("ControlTypeName")
    @Expose
    private String controlTypeName;

    @SerializedName("Controls")
    @Expose
    private List<Controls> controls;

    // Getters and setters


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

    public InstallerControl(String controlTypeName, List<Controls> controls) {
        this.controlTypeName = controlTypeName;
        this.controls = controls;
    }
//    public static class Controls{
//
//        @SerializedName("NodeId")
//        @Expose
//        private String nodeId;
//
//        @SerializedName("DisplayOrder")
//        @Expose
//        private int displayOrder;
//
//        @SerializedName("GAAProjectSpaceTypePlannedDeviceRef")
//        @Expose
//        private Long gaaProjectSpaceTypePlannedDeviceRef;
//
//        @SerializedName("GAAProjectSpaceTypePlannedDeviceName")
//        @Expose
//        private String gaaProjectSpaceTypePlannedDeviceName;
//
//        @SerializedName("IsProvisioned")
//        @Expose
//        private boolean isProvisioned;
//
//        public String getNodeId() {
//            return nodeId;
//        }
//
//        public void setNodeId(String nodeId) {
//            this.nodeId = nodeId;
//        }
//
//        public int getDisplayOrder() {
//            return displayOrder;
//        }
//
//        public void setDisplayOrder(int displayOrder) {
//            this.displayOrder = displayOrder;
//        }
//
//        public Long getGaaProjectSpaceTypePlannedDeviceRef() {
//            return gaaProjectSpaceTypePlannedDeviceRef;
//        }
//
//        public void setGaaProjectSpaceTypePlannedDeviceRef(Long gaaProjectSpaceTypePlannedDeviceRef) {
//            this.gaaProjectSpaceTypePlannedDeviceRef = gaaProjectSpaceTypePlannedDeviceRef;
//        }
//
//        public String getGaaProjectSpaceTypePlannedDeviceName() {
//            return gaaProjectSpaceTypePlannedDeviceName;
//        }
//
//        public void setGaaProjectSpaceTypePlannedDeviceName(String gaaProjectSpaceTypePlannedDeviceName) {
//            this.gaaProjectSpaceTypePlannedDeviceName = gaaProjectSpaceTypePlannedDeviceName;
//        }
//
//        public boolean isProvisioned() {
//            return isProvisioned;
//        }
//
//        public void setProvisioned(boolean provisioned) {
//            isProvisioned = provisioned;
//        }
//
//        public Controls(String nodeId, int displayOrder, Long gaaProjectSpaceTypePlannedDeviceRef, String gaaProjectSpaceTypePlannedDeviceName, boolean isProvisioned) {
//            this.nodeId = nodeId;
//            this.displayOrder = displayOrder;
//            this.gaaProjectSpaceTypePlannedDeviceRef = gaaProjectSpaceTypePlannedDeviceRef;
//            this.gaaProjectSpaceTypePlannedDeviceName = gaaProjectSpaceTypePlannedDeviceName;
//            this.isProvisioned = isProvisioned;
//        }
//    }


}
