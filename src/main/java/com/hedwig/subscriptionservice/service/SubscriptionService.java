package com.hedwig.subscriptionservice.service;

import org.springframework.http.ResponseEntity;

public interface SubscriptionService {
    
    ResponseEntity<?> getSubsForUser(String userId);

    ResponseEntity<?> getSubsForProduct(String productId);

    void changeCommunicationInfo(Long userId, String email);

    void getSubscriberCommunicationInfoForProduct(String productId);
}
