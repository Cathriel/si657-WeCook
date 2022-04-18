package com.example.demo.controller;


import com.example.demo.domain.model.Cookbook;
import com.example.demo.domain.service.CookbookService;
import com.example.demo.domain.service.ProfileService;
import com.example.demo.resource.CookbookResource;
import com.example.demo.resource.SaveCookbookResource;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/api")
public class CookbooksController {

    @Autowired
    private ModelMapper mapper;

    @Autowired
    private CookbookService cookbookService;

    @Autowired
    private ProfileService profileService;

    @GetMapping("/cookbooks/{cookbookId}")
    public CookbookResource getCookbookById(@PathVariable Long cookbookId) {
        return convertToResourceCookbook(cookbookService.getCookbookById(cookbookId));
    }

    //@GetMapping("/cookbooks/{cookbookId}/recipes")


    private Cookbook convertToEntityCookbook(SaveCookbookResource resource) { return mapper.map(resource, Cookbook.class); }
    private CookbookResource convertToResourceCookbook(Cookbook entity) { return mapper.map(entity, CookbookResource.class); }
}
