package com.hedwig.subscriptionservice.amqp.messages;

import java.io.Serializable;

public class SubscriptionCommunicationInfo implements Serializable {
    private Long notificationId;
    private String targetAddress;
    private String targetType;

    public Long getNotificationId() {
        return notificationId;
    }

    public void setNotificationId(Long notificationId) {
        this.notificationId = notificationId;
    }

    public String getTargetAddress() {
        return targetAddress;
    }

    public void setTargetAddress(String targetAddress) {
        this.targetAddress = targetAddress;
    }

    public String getTargetType() {
        return targetType;
    }

    public void setTargetType(String targetType) {
        this.targetType = targetType;
    }
}
