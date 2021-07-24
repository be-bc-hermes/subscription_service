package com.hedwig.subscriptionservice.amqp.messages;

import java.io.Serializable;

public class SubscriptionCommunicationInfo implements Serializable {
    private Long userId;
    private Long productId;
    private String email;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
