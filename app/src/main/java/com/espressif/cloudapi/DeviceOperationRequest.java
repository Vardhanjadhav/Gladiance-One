package com.espressif.cloudapi;

import com.espressif.AppConstants;
import com.google.gson.annotations.SerializedName;

public class DeviceOperationRequest {
    @SerializedName(AppConstants.KEY_NODE_ID)
    private String nodeId;

    @SerializedName(AppConstants.KEY_SECRET_KEY)
    private String secretKey;

    @SerializedName(AppConstants.KEY_OPERATION)
    private String operation;

    public String getNodeId() {
        return nodeId;
    }

    public void setNodeId(String nodeId) {
        this.nodeId = nodeId;
    }

    public String getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }
}
