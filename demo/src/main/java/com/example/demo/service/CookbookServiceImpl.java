package com.example.demo.service;

import com.example.demo.domain.model.Cookbook;
import com.example.demo.domain.model.Profile;
import com.example.demo.domain.repository.CookbookRepository;
import com.example.demo.domain.repository.ProfileRepository;
import com.example.demo.domain.service.CookbookService;
import com.example.demo.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class CookbookServiceImpl implements CookbookService {

    @Autowired
    private CookbookRepository cookbookRepository;

    @Autowired
    private ProfileRepository profileRepository;


    @Override
    public Page<Cookbook> getAllCookbooksByProfileId(Long profileId, Pageable pageable) {
        return cookbookRepository.findByProfileId(profileId, pageable);
    }

    @Override
    public Cookbook getCookbookById(Long cookbookId) {
        return cookbookRepository.findById(cookbookId).
                orElseThrow(()->new ResourceNotFoundException("Cookbook", "Id", cookbookId));
    }

    @Override
    public Cookbook createCookbook(Long profileId, Cookbook cookbook) {
        Profile profile=profileRepository.findById(profileId)
                .orElseThrow(()->new ResourceNotFoundException(
                "Profile", "Id", profileId));
        return cookbookRepository.save(cookbook.setProfile(profile));
    }

    @Override
    public Cookbook updateCookbook(Long cookbookId, Cookbook cookbookRequest) {
        Cookbook cookbook = cookbookRepository.findById(cookbookId)
                .orElseThrow(()->new ResourceNotFoundException("Cookbook", "Id", cookbookId));
        cookbook.setName(cookbookRequest.getName());
        return cookbookRepository.save(cookbook);
    }

    @Override
    public ResponseEntity<?> deleteCookbook(Long cookbookId) {
        Cookbook cookbook = cookbookRepository.findById(cookbookId)
                .orElseThrow(()->new ResourceNotFoundException("Cookbook", "Id", cookbookId));
        cookbookRepository.delete(cookbook);
        return ResponseEntity.ok().build();
    }

    @Override
    public Cookbook getCookbookByName(String name) {
        return cookbookRepository.findByName(name)
                .orElseThrow(()->new ResourceNotFoundException("Cookbook", "Name", name));
    }
}
