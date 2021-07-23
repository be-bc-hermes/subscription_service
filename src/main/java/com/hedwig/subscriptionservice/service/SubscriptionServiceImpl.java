package com.hedwig.subscriptionservice.service;

import com.hedwig.subscriptionservice.entity.Subscription;
import com.hedwig.subscriptionservice.repository.SubscriptionRepository;
import com.hedwig.subscriptionservice.resource.SubscriptionResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class SubscriptionServiceImpl implements SubscriptionService{

    private final SubscriptionRepository subscriptionRepository;

    @Autowired
    public SubscriptionServiceImpl(final SubscriptionRepository subscriptionRepository){
        this.subscriptionRepository = subscriptionRepository;
    }

    @Override
    public ResponseEntity<?> getSubsForUser(String userId) {
        try {
            List<Subscription> subscriptions = subscriptionRepository.findByUserId(userId);
            ArrayList<SubscriptionResource> subscriptionResourcesList = new ArrayList<SubscriptionResource>();

            for (Subscription sub : subscriptions) {
                subscriptionResourcesList.add(new SubscriptionResource(sub.getUserId(), sub.getProductId()));
            }
            return new ResponseEntity<>(subscriptionResourcesList, HttpStatus.OK);
        }
        catch(EntityNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public ResponseEntity<?> getSubsForProduct(String productId) {
        try {
            List<Subscription> subscriptions = subscriptionRepository.findByProductId(productId);
            ArrayList<SubscriptionResource> subscriptionResourcesList = new ArrayList<SubscriptionResource>();

            for (Subscription sub : subscriptions) {
                subscriptionResourcesList.add(new SubscriptionResource(sub.getUserId(), sub.getProductId()));
            }
            return new ResponseEntity<>(subscriptionResourcesList, HttpStatus.OK);
        }
        catch(EntityNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    /*
    public SubscriptionResource convertSubscriptionListToResource(List<Subscription> subscriptions){
       SubscriptionResource subscriptionResource = new SubscriptionResource();
        return subscriptionResource;
    }

     */


}
