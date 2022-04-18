package com.example.demo.controller;

import com.example.demo.domain.model.Recipe;
import com.example.demo.domain.model.Subscription;
import com.example.demo.domain.service.SubscriptionService;
import com.example.demo.resource.RecipeResource;
import com.example.demo.resource.SubscriptionResource;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@RestController

@RequestMapping("/api")
public class SubscriptionsController {

    @Autowired
    private ModelMapper mapper;
    @Autowired
    SubscriptionService subscriptionService;

    @GetMapping("/subscriptions")
    public Page<SubscriptionResource> getAllSubscription(Pageable pageable){
        Page<Subscription> subscriptionPage=subscriptionService.getSubscriptions(pageable);
        List<SubscriptionResource> resources= subscriptionPage.getContent().stream().map(
                this::convertToResourceSubscription).collect(Collectors.toList());
        return new PageImpl<>(resources,pageable,resources.size());

    }

    @PostMapping("/subscriptions")
    public SubscriptionResource createSubscription(@PathVariable Long chefId, @PathVariable Long readerId,@RequestBody Subscription subscription){
        return convertToResourceSubscription(subscriptionService.createSubscription(chefId,readerId,subscription));
    }

    @GetMapping("subscriptions/{subscriptionId}")
    public SubscriptionResource getSubscriptionById(@PathVariable Long id){
        return convertToResourceSubscription(subscriptionService.getSubscriptionById(id));
    }

    private SubscriptionResource convertToResourceSubscription(Subscription entity) {
        return mapper.map(entity, SubscriptionResource.class);
    }


}
