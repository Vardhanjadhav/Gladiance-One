package com.espressif.ui.models;

import android.os.Parcel;
import android.os.Parcelable;

public class EspOtaUpdate implements Parcelable {

    private String nodeId;
    private boolean otaAvailable;
    private String status;
    private String otaJobID;
    private String otaStatusDescription;
    private String fwVersion;
    private int fileSize;
    private String additionalInfo;
    private long timestamp;

    public EspOtaUpdate(String nodeId) {
        this.nodeId = nodeId;
    }

    public EspOtaUpdate(Parcel in) {
        nodeId = in.readString();
        otaAvailable = in.readByte() != 0;
        status = in.readString();
        otaJobID = in.readString();
        otaStatusDescription = in.readString();
        fwVersion = in.readString();
        fileSize = in.readInt();
        additionalInfo = in.readString();
        timestamp = in.readLong();
    }

    public static final Creator<EspOtaUpdate> CREATOR = new Creator<EspOtaUpdate>() {
        @Override
        public EspOtaUpdate createFromParcel(Parcel in) {
            return new EspOtaUpdate(in);
        }

        @Override
        public EspOtaUpdate[] newArray(int size) {
            return new EspOtaUpdate[size];
        }
    };

    public String getNodeId() {
        return nodeId;
    }

    public void setNodeId(String nodeId) {
        this.nodeId = nodeId;
    }

    public boolean isOtaAvailable() {
        return otaAvailable;
    }

    public void setOtaAvailable(boolean otaAvailable) {
        this.otaAvailable = otaAvailable;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getOtaJobID() {
        return otaJobID;
    }

    public void setOtaJobID(String otaJobID) {
        this.otaJobID = otaJobID;
    }

    public String getOtaStatusDescription() {
        return otaStatusDescription;
    }

    public void setOtaStatusDescription(String otaStatusDescription) {
        this.otaStatusDescription = otaStatusDescription;
    }

    public String getFwVersion() {
        return fwVersion;
    }

    public void setFwVersion(String fwVersion) {
        this.fwVersion = fwVersion;
    }

    public int getFileSize() {
        return fileSize;
    }

    public void setFileSize(int fileSize) {
        this.fileSize = fileSize;
    }

    public String getAdditionalInfo() {
        return additionalInfo;
    }

    public void setAdditionalInfo(String additionalInfo) {
        this.additionalInfo = additionalInfo;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(nodeId);
        dest.writeByte((byte) (otaAvailable ? 1 : 0));
        dest.writeString(status);
        dest.writeString(otaJobID);
        dest.writeString(otaStatusDescription);
        dest.writeString(fwVersion);
        dest.writeInt(fileSize);
        dest.writeString(additionalInfo);
        dest.writeLong(timestamp);
    }
}