package com.hedwig.subscriptionservice.service;

import com.hedwig.subscriptionservice.entity.Subscription;
import com.hedwig.subscriptionservice.repository.SubscriptionRepository;
import com.hedwig.subscriptionservice.resource.SubscriptionResource;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class SubscriptionServiceImpl implements SubscriptionService{

    private final SubscriptionRepository subscriptionRepository;

    @Autowired
    public SubscriptionServiceImpl(final SubscriptionRepository subscriptionRepository){
        this.subscriptionRepository = subscriptionRepository;
    }

    @Override
    public SubscriptionResource getSubsForUser(String userId) {
        List<Subscription> subscriptions = subscriptionRepository.findByUserId(userId);
        SubscriptionResource subscriptionResource = convertSubscriptionListToResource(subscriptions);
        return subscriptionResource;

    }

    @Override
    public SubscriptionResource getSubsForProduct(String productId) {
        List<Subscription> subscriptions = subscriptionRepository.findByProductId(productId);
        SubscriptionResource subscriptionResource = convertSubscriptionListToResource(subscriptions);
        return subscriptionResource;
    }

    public SubscriptionResource convertSubscriptionListToResource(List<Subscription> subscriptions){
       SubscriptionResource subscriptionResource = new SubscriptionResource();
        return subscriptionResource;
    }


}
