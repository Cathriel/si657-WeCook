package com.example.demo.controller;

import com.example.demo.domain.model.Follow;
import com.example.demo.domain.service.FollowService;
import com.example.demo.resource.FollowResource;
import com.example.demo.resource.SaveFollowResource;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;


@RestController
@CrossOrigin
@RequestMapping("/api")
public class FollowsController {
    @Autowired
    private ModelMapper mapper;

    @Autowired
    private FollowService followService;

    @GetMapping("/follows")
    public Page<FollowResource> getAllFollows(Pageable pageable){
        Page<Follow> followPage = followService.getAllFollows(pageable);
        List<FollowResource> resources = followPage.getContent().stream().map(
                this::convertToResourceFollow).collect(Collectors.toList());
        return new PageImpl<>(resources,pageable,resources.size());
    }

    private Follow convertToEntityFollow(SaveFollowResource resource){return mapper.map(resource,Follow.class);}

    private FollowResource convertToResourceFollow(Follow entity){return mapper.map(entity,FollowResource.class);}
}
