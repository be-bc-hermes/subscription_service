package com.hedwig.subscriptionservice.controller;

import com.hedwig.subscriptionservice.common.SubscriptionServiceConstants;
import com.hedwig.subscriptionservice.entity.dto.SubscriptionDTO;
import com.hedwig.subscriptionservice.service.SubscriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.constraints.NotBlank;



@RestController
@RequestMapping(SubscriptionServiceConstants.SUBSCRIPTION_SERVICE_PATH)
public class SubscriptionController {

    private final SubscriptionService subscriptionService;


    @Autowired
    public SubscriptionController(SubscriptionService subscriptionService){
        this.subscriptionService = subscriptionService;
    }


    @PostMapping("/")
    public ResponseEntity<String> createSubscription(@RequestBody SubscriptionDTO subDTO) {

        boolean success = subscriptionService.createSubscription(subDTO.getUserId(), subDTO.getProductId(), subDTO.getUserType());

        if (success) {
            return new ResponseEntity<>("Subscription created!", HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>("Subscription failed", HttpStatus.INTERNAL_SERVER_ERROR);
        }



    }

    //TODO delete sub
        //API gatewayden gelecek
        //direkt delete

    //get subs for userId
    @GetMapping("/user/{userId}")
    public ResponseEntity<?> getAllSubsForUser(@PathVariable(value = "userId") @NotBlank final Long userId){

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
