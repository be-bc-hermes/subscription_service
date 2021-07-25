package com.hedwig.subscriptionservice.repository;

import com.hedwig.subscriptionservice.entity.Subscription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubscriptionRepository extends JpaRepository<Subscription, Long> {

    List<Subscription> findByUserId(final Long userId);
    List<Subscription> findByProductId(final Long productId);
    List<Subscription> findByProductIdAndUserType(final Long productId, final String userType);

}