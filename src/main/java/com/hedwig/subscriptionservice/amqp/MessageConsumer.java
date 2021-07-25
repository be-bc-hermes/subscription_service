package com.hedwig.subscriptionservice.amqp;

import com.hedwig.subscriptionservice.entity.dto.CommunicationInfoDTO;
import com.hedwig.subscriptionservice.amqp.messages.Message;
import com.hedwig.subscriptionservice.entity.dto.NotificationDTO;
import com.hedwig.subscriptionservice.service.SubscriptionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MessageConsumer {

    Logger logger = LoggerFactory.getLogger(MessageConsumer.class);
    private final SubscriptionService subscriptionService;

    @Autowired
    public MessageConsumer(final SubscriptionService subscriptionService) {
        this.subscriptionService = subscriptionService;
    }

    @RabbitListener(queues = "hedwig.user.info")
    public void handleUserMessage(Message message){
        logger.trace("User communication info change event is received");
        if(message.getMessage() instanceof CommunicationInfoDTO){
            subscriptionService.changeCommunicationInfo(((CommunicationInfoDTO) message.getMessage()).getId(),
                    ((CommunicationInfoDTO) message.getMessage()).getEmail());
        }
    }


    @RabbitListener(queues = "product_price_change_notification__subscriber__q")
    public void handleNotificationMessage(NotificationDTO notificationDTO){
        logger.trace("User communication info demand is received");
        subscriptionService.getSubscriberCommunicationInfoForProduct(notificationDTO);

    }
}
