package com.hedwig.subscriptionservice.entity.dto;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

public class NotificationDTO implements Serializable {

    private Long id;
    private Long productId;
    private String priceChannel;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getPriceChannel() {
        return priceChannel;
    }

    public void setPriceChannel(String priceChannel) {
        this.priceChannel = priceChannel;
    }

    @Override
    public String toString() {
        return "NotificationDTO{" +
               "id=" + id +
               ", productId=" + productId +
               ", priceChannel='" + priceChannel + '\'' +
               '}';
    }
}
