package com.hedwig.subscriptionservice.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
public class CommunicationInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "userId")
    @NotNull
    private Long userId;

    @Column(name = "email")
    @NotNull
    private String email;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
