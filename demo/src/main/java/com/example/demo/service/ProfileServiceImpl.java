package com.example.demo.service;

import com.example.demo.domain.model.Profile;
import com.example.demo.domain.model.User;
import com.example.demo.domain.repository.ProfileRepository;
import com.example.demo.domain.repository.UserRepository;
import com.example.demo.domain.service.ProfileService;
import com.example.demo.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class ProfileServiceImpl implements ProfileService {

    @Autowired
    private ProfileRepository profileRepository;
    @Autowired
    private UserRepository userRepository;

    @Override
    public Page<Profile> getAllProfile(Pageable pageable) {
        return profileRepository.findAll(pageable);
    }

    @Override
    public Profile getProfileById(Long profileId) {
        return profileRepository.findById(profileId)
                .orElseThrow(()->new ResourceNotFoundException("Profile","Id",profileId));
    }

    @Override
    public Profile createProfile(Long userId, Profile profile) {
        User user = userRepository.findById(userId)
                .orElseThrow(()->new ResourceNotFoundException("User","Id",userId));

        return profileRepository.save(profile.setUser(user));

    }

    @Override
    public Profile updateProfile(Long profileId, Profile profileRequest) {
        return null;
    }

    @Override
    public Profile getProfileByUserId(Long userId) {
        return profileRepository.findProfileByUserId(userId)
                .orElseThrow(()-> new ResourceNotFoundException("Profile","Id",userId));
    }

    @Override
    public ResponseEntity<?> deleteProfile(Long profileId) {
        return null;
    }

    @Override
    public Profile getProfileByName(String name) {
        return profileRepository.findProfileByName(name)
                .orElseThrow(()->new ResourceNotFoundException("Profile","Name",name));
    }
}
