package com.hedwig.subscriptionservice.amqp.messages;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@Builder
public class Message implements Serializable {

    String id;
    Object message;
}