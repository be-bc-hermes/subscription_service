package com.hedwig.subscriptionservice.controller;

import com.hedwig.subscriptionservice.common.SubscriptionServiceConstants;
import com.hedwig.subscriptionservice.resource.SubscriptionResource;
import com.hedwig.subscriptionservice.service.SubscriptionService;
import com.sun.istack.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    //update sub
        //communication info güncellenir ve amqp mesajı gelir
        //db check edilir
        //kayıt varsa güncelle

    //delete sub
        //API gatewayden gelecek
        //direkt delete


    //get subs for userId
    @GetMapping("/user/{userId}")
    public ResponseEntity<?> getAllSubsForUser(@PathVariable(value = "userId") @NotNull final String userId){

        SubscriptionResource subscriptionResource = subscriptionService.getSubsForUser(userId);

        return ResponseEntity.status(HttpStatus.OK).body(subscriptionResource);
    }

    //get subs for productId
    @GetMapping("/product/{productId}")
    public ResponseEntity<?> getAllSubsForProduct(@PathVariable(value = "productId") final String productId){

        SubscriptionResource subscriptionResource = subscriptionService.getSubsForProduct(productId);

        return ResponseEntity.status(HttpStatus.OK).body(subscriptionResource);
    }



}
