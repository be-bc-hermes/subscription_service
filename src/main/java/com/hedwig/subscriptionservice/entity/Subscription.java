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
    private Long userId;

    @Column(name = "productId")
    @NotNull
    private Long productId;

    @Column(name = "userType")
    @NotNull
    private String userType;

    @OneToOne
    private CommunicationInfo communicationInfo;

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

    public CommunicationInfo getCommunicationInfo() {
        return communicationInfo;
    }

    public void setCommunicationInfo(CommunicationInfo communicationInfo) {
        this.communicationInfo = communicationInfo;
    }
}
