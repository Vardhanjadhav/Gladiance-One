package com.espressif.ui.activities;

public class ApiRequest {
    private int senderLoginToken;
    private String topic;
    private String message;
    private int qosLevel;

    public ApiRequest(int senderLoginToken, String topic, String message, int qosLevel) {
        this.senderLoginToken = senderLoginToken;
        this.topic = topic;
        this.message = message;
        this.qosLevel = qosLevel;
    }
}
