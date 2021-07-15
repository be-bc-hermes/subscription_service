package com.hedwig.subscriptionservice.service;

import com.hedwig.subscriptionservice.resource.SubscriptionResource;
import org.springframework.stereotype.Service;

@Service
public interface SubscriptionService {
    
    SubscriptionResource getSubsForUser(String userId);

    SubscriptionResource getSubsForProduct(String productId);
}
