package com.hedwig.subscriptionservice.service;

import com.hedwig.subscriptionservice.amqp.MessageProducer;
import com.hedwig.subscriptionservice.amqp.messages.SubscriptionCommunicationInfo;
import com.hedwig.subscriptionservice.common.SubscriptionServiceConstants;
import com.hedwig.subscriptionservice.common.UserType;
import com.hedwig.subscriptionservice.entity.CommunicationInfo;
import com.hedwig.subscriptionservice.entity.Subscription;
import com.hedwig.subscriptionservice.entity.dto.CommunicationInfoDTO;
import com.hedwig.subscriptionservice.entity.dto.NotificationDTO;
import com.hedwig.subscriptionservice.repository.CommunicationInfoRepository;
import com.hedwig.subscriptionservice.repository.SubscriptionRepository;
import com.hedwig.subscriptionservice.resource.SubscriptionResource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Service
public class SubscriptionServiceImpl implements SubscriptionService{

    Logger logger = LoggerFactory.getLogger(SubscriptionServiceImpl.class);
    private final SubscriptionRepository subscriptionRepository;
    private final CommunicationInfoRepository communicationInfoRepository;
    private final MessageProducer messageProducer;
    private final WebClient webClient;

    @Autowired
    public SubscriptionServiceImpl(final SubscriptionRepository subscriptionRepository,
                                   final CommunicationInfoRepository communicationInfoRepository,
                                   final MessageProducer messageProducer){
        this.subscriptionRepository = subscriptionRepository;
        this.communicationInfoRepository = communicationInfoRepository;
        this.messageProducer = messageProducer;
        this.webClient = WebClient.create();
    }

    @Override
    public ResponseEntity<?> getSubsForUser(Long userId) {
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
    public ResponseEntity<?> getSubsForProduct(Long productId) {
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

    @Override
    public void changeCommunicationInfo(Long userId, String email) {
        CommunicationInfo communicationInfo = communicationInfoRepository.findByUserId(userId);
        communicationInfo.setEmail(email);
        communicationInfoRepository.save(communicationInfo);
    }


    @Override
    public void getSubscriberCommunicationInfoForProduct(NotificationDTO notificationDTO) {
        List<Subscription> subscriptions = subscriptionRepository.findByProductIdAndUserType(notificationDTO.getProductId(), notificationDTO.getPriceChannel());
        for(Subscription subscription : subscriptions){
            messageProducer.sendToQueue(prepareUserCommunicationInfo(subscription, notificationDTO.getId()));
        }
    }

    @Override
    public void createSubscription(Long userId, Long productId, UserType userType) {
        /*
        CommunicationInfoDTO userResult = webClient.get()
                .uri("localhost:8081")
                .retrieve()
                .bodyToMono(CommunicationInfoDTO.class).block();
        */

        CommunicationInfoDTO userResult = new CommunicationInfoDTO(userId,"ozan@gmail.com");

        if (userResult != null) {
            // First, create communication info for the user
            CommunicationInfo communicationInfo = new CommunicationInfo();
            communicationInfo.setEmail(userResult.getEmail());
            communicationInfo.setUserId(userId);
            communicationInfoRepository.save(communicationInfo);

            //Now, create sub info
            Subscription sub = new Subscription();
            sub.setUserId(userId);
            sub.setProductId(productId);
            sub.setCommunicationInfo(communicationInfo);
            subscriptionRepository.save(sub);
        }
    }

    private SubscriptionCommunicationInfo prepareUserCommunicationInfo(Subscription subscription, Long id) {
        SubscriptionCommunicationInfo subscriptionCommunicationInfo = new SubscriptionCommunicationInfo();

        subscriptionCommunicationInfo.setTargetAddress(subscription.getCommunicationInfo().getEmail());
        subscriptionCommunicationInfo.setTargetType(SubscriptionServiceConstants.EMAIL);
        subscriptionCommunicationInfo.setNotificationId(id);

        return subscriptionCommunicationInfo;
    }





    /*
    public SubscriptionResource convertSubscriptionListToResource(List<Subscription> subscriptions){
       SubscriptionResource subscriptionResource = new SubscriptionResource();
        return subscriptionResource;
    }

     */


}
