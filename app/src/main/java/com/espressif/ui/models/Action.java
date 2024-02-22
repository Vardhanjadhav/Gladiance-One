package com.espressif.ui.models;

import android.os.Parcel;
import android.os.Parcelable;

public class Action implements Parcelable, Comparable {

    private String nodeId;
    private Device device;

    public Action() {
    }

    protected Action(Parcel in) {
        nodeId = in.readString();
        device = in.readParcelable(Device.class.getClassLoader());
    }

    public String getNodeId() {
        return nodeId;
    }

    public void setNodeId(String nodeId) {
        this.nodeId = nodeId;
    }

    public Device getDevice() {
        return device;
    }

    public void setDevice(Device device) {
        this.device = device;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(nodeId);
        dest.writeParcelable(device, flags);
    }

    public static final Creator<Action> CREATOR = new Creator<Action>() {
        @Override
        public Action createFromParcel(Parcel in) {
            return new Action(in);
        }

        @Override
        public Action[] newArray(int size) {
            return new Action[size];
        }
    };

    @Override
    public int compareTo(Object o) {
        Action compare = (Action) o;
        if (compare.nodeId != null && this.nodeId != null && !compare.nodeId.equals(this.nodeId)) {
            return 1;
        } else if (compare.device != null && this.device != null && !compare.device.equals(this.device)) {
            return 1;
        }
        return 1;
    }
}