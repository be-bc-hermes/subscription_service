package com.hedwig.subscriptionservice.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
public class CommunicationInfoDTO implements Serializable {

    private Long id;
    private String email;

    public CommunicationInfoDTO(Long id, String email) {
        this.id= id;
        this.email = email;
    }
}