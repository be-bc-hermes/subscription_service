package com.hedwig.subscriptionservice.service;

import com.hedwig.subscriptionservice.resource.SubscriptionResource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public interface SubscriptionService {
    
    ResponseEntity<?> getSubsForUser(String userId);

    ResponseEntity<?> getSubsForProduct(String productId);
}
