package com.example.demo.controller;


import com.example.demo.domain.model.Subscription;
import com.example.demo.domain.model.Tip;
import com.example.demo.domain.service.TipService;
import com.example.demo.resource.SaveTipResource;
import com.example.demo.resource.SubscriptionResource;
import com.example.demo.resource.TipResource;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;


@RestController
@CrossOrigin
@RequestMapping("/api")
public class TipsController {

    @Autowired
    private ModelMapper mapper;
    @Autowired
    TipService tipService;

    @GetMapping("/tips")
    public Page<TipResource> getAllSubscription(Pageable pageable){
        Page<Tip> tipPage=tipService.getTips(pageable);
        List<TipResource> resources= tipPage.getContent().stream().map(
                this::convertToResourceTip).collect(Collectors.toList());
        return new PageImpl<>(resources,pageable,resources.size());

    }

    @GetMapping("tips/{tipId}")
    public TipResource getTipById(@PathVariable Long id){
        return convertToResourceTip(tipService.getTipById(id));
    }

    private TipResource convertToResourceTip(Tip entity) {
        return mapper.map(entity, TipResource.class);
    }
    private Tip convertToEntityTip(SaveTipResource resource) {
        return mapper.map(resource, Tip.class);
    }

}
