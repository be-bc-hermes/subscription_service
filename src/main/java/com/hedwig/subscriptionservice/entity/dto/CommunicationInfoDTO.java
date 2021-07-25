package com.hedwig.subscriptionservice.entity.dto;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@Builder
public class CommunicationInfoDTO implements Serializable {

    private Long id;
    private String email;

    public CommunicationInfoDTO(Long id, String email) {
        this.id= id;
        this.email = email;
    }
}