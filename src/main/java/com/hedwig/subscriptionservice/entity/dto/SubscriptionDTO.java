package com.hedwig.subscriptionservice.entity.dto;

import com.hedwig.subscriptionservice.common.UserType;
import lombok.Data;

import java.io.Serializable;

@Data
public class SubscriptionDTO implements Serializable {
    private Long userId;
    private Long productId;
    private UserType userType;
}
