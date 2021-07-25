package com.hedwig.subscriptionservice.amqp.messages;

import com.hedwig.subscriptionservice.entity.dto.CommunicationInfoDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Message implements Serializable {

    String id;
    CommunicationInfoDTO message;
}