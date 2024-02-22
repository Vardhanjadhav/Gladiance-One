package com.espressif.ui.models;

import com.google.gson.annotations.SerializedName;

public class NodeResponseModel {

    @SerializedName("NodeId")
    private String nodeId;

    public String getNodeId() {
        return nodeId;
    }

    public void setNodeId(String nodeId) {
        this.nodeId = nodeId;
    }
}
