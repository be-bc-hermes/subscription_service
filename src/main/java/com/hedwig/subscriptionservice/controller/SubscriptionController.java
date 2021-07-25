package com.hedwig.subscriptionservice.controller;

import com.hedwig.subscriptionservice.common.SubscriptionServiceConstants;
import com.hedwig.subscriptionservice.common.UserType;
import com.hedwig.subscriptionservice.entity.CommunicationInfo;
import com.hedwig.subscriptionservice.entity.Subscription;
import com.hedwig.subscriptionservice.entity.dto.CommunicationInfoDTO;
import com.hedwig.subscriptionservice.entity.dto.SubscriptionDTO;
import com.hedwig.subscriptionservice.resource.SubscriptionResource;
import com.hedwig.subscriptionservice.service.SubscriptionService;
import com.sun.istack.NotNull;
import io.netty.channel.ChannelOption;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.net.http.HttpClient;
import java.time.Duration;
import java.util.Map;
import java.util.function.Predicate;

@RestController
@RequestMapping(SubscriptionServiceConstants.SUBSCRIPTION_SERVICE_PATH)
public class SubscriptionController {

    private final SubscriptionService subscriptionService;


    @Autowired
    public SubscriptionController(SubscriptionService subscriptionService){
        this.subscriptionService = subscriptionService;
    }

    //create sub
        //istek API gatewayden gelir
        //user ve product servicelere istek atılır.
        //confirmation alınır + user serviceden communication info alınır
        //kayıt atılır.
    @PostMapping("/")
    public ResponseEntity<String> createSubscription(@RequestBody SubscriptionDTO subDTO) {

        subscriptionService.createSubscription(subDTO.getUserId(), subDTO.getProductId(), subDTO.getUserType());

        return new ResponseEntity<>("Subscription created!", HttpStatus.OK);

    }

    //TODO move to service
    //update sub
        //communication info güncellenir ve amqp mesajı gelir
        //db check edilir
        //kayıt varsa güncelle

    //delete sub
        //API gatewayden gelecek
        //direkt delete


    //get subs for userId
    @GetMapping("/user/{userId}")
    public ResponseEntity<?> getAllSubsForUser(@PathVariable(value = "userId") @NotNull final Long userId){

        ResponseEntity<?> subscriptionResource = subscriptionService.getSubsForUser(userId);

        return ResponseEntity.status(HttpStatus.OK).body(subscriptionResource);
    }

    //get subs for productId
    @GetMapping("/product/{productId}")
    public ResponseEntity<?> getAllSubsForProduct(@PathVariable(value = "productId") final Long productId){

        ResponseEntity<?> subscriptionResource = subscriptionService.getSubsForProduct(productId);

        return ResponseEntity.status(HttpStatus.OK).body(subscriptionResource);
    }



}
