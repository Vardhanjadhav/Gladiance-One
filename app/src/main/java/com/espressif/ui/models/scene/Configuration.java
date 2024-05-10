
package com.espressif.ui.models.scene;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class Configuration {

    @SerializedName("GAAProjectSceneRef")
    @Expose
    private Long gAAProjectSceneRef;
    @SerializedName("GAAProjectSpaceTypePlannedDeviceConnectionRef")
    @Expose
    private Long gAAProjectSpaceTypePlannedDeviceConnectionRef;
    @SerializedName("NodeConfigParamName")
    @Expose
    private String nodeConfigParamName;
    @SerializedName("Value")
    @Expose
    private String value;
    @SerializedName("GAAProjectSceneName")
    @Expose
    private String gAAProjectSceneName;
    @SerializedName("GAAProjectSceneCode")
    @Expose
    private String gAAProjectSceneCode;
    @SerializedName("GAAProjectSpaceTypeRef")
    @Expose
    private Long gAAProjectSpaceTypeRef;
    @SerializedName("GAAProjectSpaceTypeName")
    @Expose
    private String gAAProjectSpaceTypeName;
    @SerializedName("GAAProjectSpaceTypeAreaRef")
    @Expose
    private Long gAAProjectSpaceTypeAreaRef;
    @SerializedName("GAAProjectSpaceTypeAreaName")
    @Expose
    private String gAAProjectSpaceTypeAreaName;
    @SerializedName("GAAProjectSpaceTypePlannedDeviceRef")
    @Expose
    private Long gAAProjectSpaceTypePlannedDeviceRef;
    @SerializedName("GAAProjectSpaceTypePlannedDeviceName")
    @Expose
    private String gAAProjectSpaceTypePlannedDeviceName;
    @SerializedName("Label")
    @Expose
    private String label;
    @SerializedName("OutputDriverChannelRef")
    @Expose
    private Long outputDriverChannelRef;
    @SerializedName("OutputDriverChannelName")
    @Expose
    private String outputDriverChannelName;
    @SerializedName("GAAProjectRef")
    @Expose
    private Long gAAProjectRef;
    @SerializedName("GAAProjectName")
    @Expose
    private String gAAProjectName;

    public Long getgAAProjectSceneRef() {
        return gAAProjectSceneRef;
    }

    public void setgAAProjectSceneRef(Long gAAProjectSceneRef) {
        this.gAAProjectSceneRef = gAAProjectSceneRef;
    }

    public Long getgAAProjectSpaceTypePlannedDeviceConnectionRef() {
        return gAAProjectSpaceTypePlannedDeviceConnectionRef;
    }

    public void setgAAProjectSpaceTypePlannedDeviceConnectionRef(Long gAAProjectSpaceTypePlannedDeviceConnectionRef) {
        this.gAAProjectSpaceTypePlannedDeviceConnectionRef = gAAProjectSpaceTypePlannedDeviceConnectionRef;
    }

    public String getNodeConfigParamName() {
        return nodeConfigParamName;
    }

    public void setNodeConfigParamName(String nodeConfigParamName) {
        this.nodeConfigParamName = nodeConfigParamName;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getgAAProjectSceneName() {
        return gAAProjectSceneName;
    }

    public void setgAAProjectSceneName(String gAAProjectSceneName) {
        this.gAAProjectSceneName = gAAProjectSceneName;
    }

    public String getgAAProjectSceneCode() {
        return gAAProjectSceneCode;
    }

    public void setgAAProjectSceneCode(String gAAProjectSceneCode) {
        this.gAAProjectSceneCode = gAAProjectSceneCode;
    }

    public Long getgAAProjectSpaceTypeRef() {
        return gAAProjectSpaceTypeRef;
    }

    public void setgAAProjectSpaceTypeRef(Long gAAProjectSpaceTypeRef) {
        this.gAAProjectSpaceTypeRef = gAAProjectSpaceTypeRef;
    }

    public String getgAAProjectSpaceTypeName() {
        return gAAProjectSpaceTypeName;
    }

    public void setgAAProjectSpaceTypeName(String gAAProjectSpaceTypeName) {
        this.gAAProjectSpaceTypeName = gAAProjectSpaceTypeName;
    }

    public Long getgAAProjectSpaceTypeAreaRef() {
        return gAAProjectSpaceTypeAreaRef;
    }

    public void setgAAProjectSpaceTypeAreaRef(Long gAAProjectSpaceTypeAreaRef) {
        this.gAAProjectSpaceTypeAreaRef = gAAProjectSpaceTypeAreaRef;
    }

    public String getgAAProjectSpaceTypeAreaName() {
        return gAAProjectSpaceTypeAreaName;
    }

    public void setgAAProjectSpaceTypeAreaName(String gAAProjectSpaceTypeAreaName) {
        this.gAAProjectSpaceTypeAreaName = gAAProjectSpaceTypeAreaName;
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

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public Long getOutputDriverChannelRef() {
        return outputDriverChannelRef;
    }

    public void setOutputDriverChannelRef(Long outputDriverChannelRef) {
        this.outputDriverChannelRef = outputDriverChannelRef;
    }

    public String getOutputDriverChannelName() {
        return outputDriverChannelName;
    }

    public void setOutputDriverChannelName(String outputDriverChannelName) {
        this.outputDriverChannelName = outputDriverChannelName;
    }

    public Long getgAAProjectRef() {
        return gAAProjectRef;
    }

    public void setgAAProjectRef(Long gAAProjectRef) {
        this.gAAProjectRef = gAAProjectRef;
    }

    public String getgAAProjectName() {
        return gAAProjectName;
    }

    public void setgAAProjectName(String gAAProjectName) {
        this.gAAProjectName = gAAProjectName;
    }

    public Configuration(Long gAAProjectSceneRef, Long gAAProjectSpaceTypePlannedDeviceConnectionRef, String nodeConfigParamName, String value, String gAAProjectSceneName, String gAAProjectSceneCode, Long gAAProjectSpaceTypeRef, String gAAProjectSpaceTypeName, Long gAAProjectSpaceTypeAreaRef, String gAAProjectSpaceTypeAreaName, Long gAAProjectSpaceTypePlannedDeviceRef, String gAAProjectSpaceTypePlannedDeviceName, String label, Long outputDriverChannelRef, String outputDriverChannelName, Long gAAProjectRef, String gAAProjectName) {
        this.gAAProjectSceneRef = gAAProjectSceneRef;
        this.gAAProjectSpaceTypePlannedDeviceConnectionRef = gAAProjectSpaceTypePlannedDeviceConnectionRef;
        this.nodeConfigParamName = nodeConfigParamName;
        this.value = value;
        this.gAAProjectSceneName = gAAProjectSceneName;
        this.gAAProjectSceneCode = gAAProjectSceneCode;
        this.gAAProjectSpaceTypeRef = gAAProjectSpaceTypeRef;
        this.gAAProjectSpaceTypeName = gAAProjectSpaceTypeName;
        this.gAAProjectSpaceTypeAreaRef = gAAProjectSpaceTypeAreaRef;
        this.gAAProjectSpaceTypeAreaName = gAAProjectSpaceTypeAreaName;
        this.gAAProjectSpaceTypePlannedDeviceRef = gAAProjectSpaceTypePlannedDeviceRef;
        this.gAAProjectSpaceTypePlannedDeviceName = gAAProjectSpaceTypePlannedDeviceName;
        this.label = label;
        this.outputDriverChannelRef = outputDriverChannelRef;
        this.outputDriverChannelName = outputDriverChannelName;
        this.gAAProjectRef = gAAProjectRef;
        this.gAAProjectName = gAAProjectName;
    }
}
