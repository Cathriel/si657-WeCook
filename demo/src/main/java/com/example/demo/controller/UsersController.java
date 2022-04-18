package com.example.demo.controller;

import com.example.demo.domain.model.Profile;
import com.example.demo.domain.model.User;
import com.example.demo.domain.service.ProfileService;
import com.example.demo.domain.service.UserService;
import com.example.demo.resource.ProfileResource;
import com.example.demo.resource.SaveProfileResource;
import com.example.demo.resource.SaveUserResource;
import com.example.demo.resource.UserResource;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@CrossOrigin
@RequestMapping("/api")
public class UsersController {

    @Autowired
    private ModelMapper mapper;

    @Autowired
    private UserService userService;

    @Autowired
    private ProfileService profileService;

    @PostMapping("users")
    public UserResource createUser(@Valid @RequestBody SaveUserResource resource){
        return convertToResourceUser(userService.createdUser(convertToEntityUser(resource)));
    }

    @PostMapping("users/{userId}/profiles")
    public ProfileResource createProfile(@PathVariable Long userId,@Valid @RequestBody SaveProfileResource resource){
        return convertToResourceProfile(profileService.createProfile(userId,convertToEntityProfile(resource)));
    }

    private User convertToEntityUser(SaveUserResource resource) {
        return mapper.map(resource, User.class);
    }

    private UserResource convertToResourceUser(User entity){
        return mapper.map(entity, UserResource.class);
    }

    private Profile convertToEntityProfile(SaveProfileResource resource) {
        return mapper.map(resource, Profile.class);
    }
    private ProfileResource convertToResourceProfile(Profile entity){
        return mapper.map(entity, ProfileResource.class);
    }
}
