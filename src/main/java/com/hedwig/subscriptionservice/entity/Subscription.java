package com.hedwig.subscriptionservice.entity;

import com.sun.istack.NotNull;

import javax.persistence.*;

@Entity
@Table(name= "subscription")
public class Subscription {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "userId")
    @NotNull
    private String userId;

    @Column(name = "productId")
    @NotNull
    private String productId;

    @OneToOne
    private CommunicationInfo communicationInfo;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public CommunicationInfo getCommunicationInfo() {
        return communicationInfo;
    }

    public void setCommunicationInfo(CommunicationInfo communicationInfo) {
        this.communicationInfo = communicationInfo;
    }
}
