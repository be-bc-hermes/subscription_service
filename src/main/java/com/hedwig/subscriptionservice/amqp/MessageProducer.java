package com.hedwig.subscriptionservice.amqp;

import com.hedwig.subscriptionservice.amqp.messages.SubscriptionCommunicationInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class MessageProducer {

    Logger logger = LoggerFactory.getLogger(MessageProducer.class);

    @Value("${sr.rabbit.routing.name}")
    private String routingName;

    @Value("${sr.rabbit.exchange.name}")
    private String exchangeName;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void sendToQueue(SubscriptionCommunicationInfo subscriptionCommunicationInfo) {
        logger.trace("User communication info is sent for user with id : {}", subscriptionCommunicationInfo.getNotificationId());
        rabbitTemplate.convertAndSend(exchangeName, routingName, subscriptionCommunicationInfo);
    }
}
