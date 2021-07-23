package com.hedwig.subscriptionservice.controller;

import com.hedwig.subscriptionservice.common.SubscriptionServiceConstants;
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
    private final WebClient webClient;

    @Autowired
    public SubscriptionController(SubscriptionService subscriptionService){
        this.webClient = WebClient.create(); // Can adjust HTTP functions, this is the simplest form of webclient
        this.subscriptionService = subscriptionService;
    }

    //create sub
        //istek API gatewayden gelir
        //user ve product servicelere istek atılır.
        //confirmation alınır + user serviceden communication info alınır
        //kayıt atılır.
    @PostMapping("/")
    public ResponseEntity<String> createSubscription(@RequestParam Map<String, String> requestParams) {
        String userId = requestParams.get("userId");
        String productId = requestParams.get("productId");

        // First communicate with userId to get user information.

        //This statement will block until it gets a response.
        // It will also throw a WebClientException if the response status is 4xx is 5xx.
        String userResult = webClient.get()
                .uri("localhost:8081")
                .retrieve()
                .bodyToMono(String.class).block();

        // Now communicate with product to verify product ID
        String productResult = webClient.get()
                .uri("product URI")
                .retrieve()
                .bodyToMono(String.class).block();

        //TODO connect with the database and update it

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
    public ResponseEntity<?> getAllSubsForUser(@PathVariable(value = "userId") @NotNull final String userId){

        ResponseEntity<?> subscriptionResource = subscriptionService.getSubsForUser(userId);

        return ResponseEntity.status(HttpStatus.OK).body(subscriptionResource);
    }

    //get subs for productId
    @GetMapping("/product/{productId}")
    public ResponseEntity<?> getAllSubsForProduct(@PathVariable(value = "productId") final String productId){

        ResponseEntity<?> subscriptionResource = subscriptionService.getSubsForProduct(productId);

        return ResponseEntity.status(HttpStatus.OK).body(subscriptionResource);
    }



}
