package com.hedwig.subscriptionservice.repository;

import com.hedwig.subscriptionservice.entity.CommunicationInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommunicationInfoRepository extends JpaRepository<CommunicationInfo, Long> {

    CommunicationInfo findByUserId(Long userId);
}
