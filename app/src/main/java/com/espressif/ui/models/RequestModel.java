package com.espressif.ui.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RequestModel {

    @SerializedName("SenderLoginToken")
    @Expose
    private Integer senderLoginToken;
    @SerializedName("Topic")
    @Expose
    private String topic;
    @SerializedName("Message")
    @Expose
    private String message;
    @SerializedName("QOSLevel")
    @Expose
    private Integer qOSLevel;

    public Integer getSenderLoginToken() {
        return senderLoginToken;
    }

    public void setSenderLoginToken(Integer senderLoginToken) {
        this.senderLoginToken = senderLoginToken;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Integer getQOSLevel() {
        return qOSLevel;
    }

    public void setQOSLevel(Integer qOSLevel) {
        this.qOSLevel = qOSLevel;
    }

}