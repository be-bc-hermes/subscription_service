package com.hedwig.subscriptionservice.service;

import com.hedwig.subscriptionservice.entity.dto.NotificationDTO;
import org.springframework.http.ResponseEntity;

public interface SubscriptionService {
    
    ResponseEntity<?> getSubsForUser(Long userId);

    ResponseEntity<?> getSubsForProduct(Long productId);

    void changeCommunicationInfo(Long userId, String email);

    void getSubscriberCommunicationInfoForProduct(NotificationDTO notificationDTO);
}
