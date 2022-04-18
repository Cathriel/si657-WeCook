package com.example.demo.domain.service;

import com.example.demo.domain.model.Profile;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface ProfileService {

    Page<Profile> getAllProfile(Pageable pageable);

    Profile getProfileById(Long profileId);

    Profile createProfile(Long userId, Profile profile);

    Profile updateProfile(Long profileId, Profile profileRequest);

    Profile getProfileByUserId(Long userId);

    ResponseEntity<?> deleteProfile(Long profileId);

    Profile getProfileByName(String name);

}
