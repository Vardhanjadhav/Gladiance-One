package com.espressif.ui.activities;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import com.espressif.AppConstants;
import com.espressif.ui.models.Device;
import com.espressif.ui.models.Param;
import com.espressif.ui.models.Service;


import java.util.ArrayList;

@Entity(tableName = AppConstants.NODE_TABLE)
public class EspNode implements Parcelable {

    @PrimaryKey
    @NonNull
    private String nodeId;

    @Ignore
    private String userRole;

    @Ignore
    private String configVersion;

    @Ignore
    private long timeStampOfStatus; // timestamp of connectivity status

    @Ignore
    private String nodeName;

    @Ignore
    private String fwVersion;

    @Ignore
    private ArrayList<Device> devices;

    @Ignore
    private String nodeType;

    @Ignore
    private boolean isOnline;

    @Ignore
    private int scheduleMaxCnt;

    @Ignore
    private int sceneMaxCnt;

    @Ignore
    private ArrayList<Service> services;

    @Ignore
    private ArrayList<Param> attributes;

    @Ignore
    private int scheduleCurrentCnt;

    @Ignore
    private int sceneCurrentCnt;

    @ColumnInfo(name = "config_data")
    private String configData;

    @ColumnInfo(name = "param_data")
    private String paramData;

    public EspNode() {
    }

    public EspNode(String id) {
        nodeId = id;
    }

    public EspNode(EspNode node) {

        nodeId = node.getNodeId();
        userRole = node.getUserRole();
        configVersion = node.getConfigVersion();
        fwVersion = node.getFwVersion();
        devices = node.getDevices();

    }

    public static final Creator<EspNode> CREATOR = new Creator<EspNode>() {
        @Override
        public EspNode createFromParcel(Parcel in) {
            return new EspNode(in);
        }

        @Override
        public EspNode[] newArray(int size) {
            return new EspNode[size];
        }
    };

    public String getNodeId() {
        return nodeId;
    }

    public void setNodeId(String nodeId) {
        this.nodeId = nodeId;
    }

    public String getUserRole() {
        return userRole;
    }
    public void setNodeType(String nodeType) {
        this.nodeType = nodeType;
    }

    public boolean isOnline() {
        return isOnline;
    }

    public void setUserRole(String userRole) {
        this.userRole = userRole;
    }

    public String getFwVersion() {
        return fwVersion;
    }

    public String getConfigVersion() {
        return configVersion;
    }

    public void setConfigVersion(String configVersion) {
        this.configVersion = configVersion;
    }

    public void setTimeStampOfStatus(long timeStampOfStatus) {
        this.timeStampOfStatus = timeStampOfStatus;
    }

    public String getConfigData() {
        return configData;
    }

    public void setConfigData(String configData) {
        this.configData = configData;
    }

    public String getParamData() {
        return paramData;
    }

    public void setParamData(String paramData) {
        this.paramData = paramData;
    }

    public void setNodeName(String nodeName) {
        this.nodeName = nodeName;
    }

    public void setFwVersion(String fwVersion) {
        this.fwVersion = fwVersion;
    }

    public ArrayList<Device> getDevices() {
        return devices;
    }

    public void setDevices(ArrayList<Device> devices) {
        this.devices = devices;
    }

    public ArrayList<Param> getAttributes() {
        return attributes;
    }

    public void setAttributes(ArrayList<Param> attributes) {
        this.attributes = attributes;
    }

    public void setOnline(boolean online) {
        isOnline = online;
    }
    public int getScheduleMaxCnt() {
        return scheduleMaxCnt;
    }

    public void setScheduleMaxCnt(int scheduleMaxCnt) {
        this.scheduleMaxCnt = scheduleMaxCnt;
    }

    public int getSceneMaxCnt() {
        return sceneMaxCnt;
    }

    public void setSceneMaxCnt(int sceneMaxCnt) {
        this.sceneMaxCnt = sceneMaxCnt;
    }

    public ArrayList<Service> getServices() {
        return services;
    }

    public void setServices(ArrayList<Service> services) {
        this.services = services;
    }

    public int getScheduleCurrentCnt() {
        return scheduleCurrentCnt;
    }

    public void setScheduleCurrentCnt(int scheduleCurrentCnt) {
        this.scheduleCurrentCnt = scheduleCurrentCnt;
    }

    public int getSceneCurrentCnt() {
        return sceneCurrentCnt;
    }

    public void setSceneCurrentCnt(int sceneCurrentCnt) {
        this.sceneCurrentCnt = sceneCurrentCnt;
    }

    protected EspNode(Parcel in) {

        nodeId = in.readString();
        userRole = in.readString();
        configVersion = in.readString();
        nodeName = in.readString();
        fwVersion = in.readString();
        nodeType = in.readString();
        isOnline = in.readByte() != 0;
        timeStampOfStatus = in.readLong();
        devices = in.createTypedArrayList(Device.CREATOR);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

//        dest.writeString(nodeId);
//        dest.writeString(userRole);
//        dest.writeString(configVersion);
//        dest.writeString(nodeName);
//        dest.writeString(fwVersion);
//        dest.writeString(nodeType);
//        dest.writeByte((byte) (isOnline ? 1 : 0));
//        dest.writeLong(timeStampOfStatus);
        dest.writeTypedList(devices);

    }

}
