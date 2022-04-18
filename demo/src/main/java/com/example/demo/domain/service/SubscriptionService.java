package com.example.demo.domain.service;

import com.example.demo.domain.model.Profile;
import com.example.demo.domain.model.Subscription;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface SubscriptionService {
    
    Page<Subscription> getSubscriptions(Pageable pageable);

    Subscription createSubscription(Long chefId,Long readerId, Subscription subscription);

    Subscription getSubscriptionById(Long subscriptionId);
    
}
