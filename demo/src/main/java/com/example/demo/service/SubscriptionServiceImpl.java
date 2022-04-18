package com.example.demo.service;

import com.example.demo.domain.model.Profile;
import com.example.demo.domain.model.Subscription;
import com.example.demo.domain.repository.ProfileRepository;
import com.example.demo.domain.repository.SubscriptionRepository;
import com.example.demo.domain.service.SubscriptionService;
import com.example.demo.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class SubscriptionServiceImpl implements SubscriptionService {
    @Autowired
    SubscriptionRepository subscriptionRepository;
    @Autowired
    ProfileRepository profileRepository;


    @Override
    public Page<Subscription> getSubscriptions(Pageable pageable) {
        return subscriptionRepository.findAll(pageable);
    }

    @Override
    public Subscription createSubscription(Long chefId, Long readerId, Subscription subscription) {
        Profile chef = profileRepository.findById(chefId)
                .orElseThrow(()-> new ResourceNotFoundException("Chef","Id",chefId));
        Profile reader = profileRepository.findById(readerId)
                .orElseThrow(()-> new ResourceNotFoundException("Reader","Id",readerId));

        return subscriptionRepository.save(subscription.setChef(chef).setReader(reader));
    }

    @Override
    public Subscription getSubscriptionById(Long subscriptionId) {
        return subscriptionRepository.findById(subscriptionId)
                .orElseThrow(()->new ResourceNotFoundException("Subscription","Id",subscriptionId));
    }
}
