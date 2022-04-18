package com.example.demo.domain.service;

import com.example.demo.domain.model.Cookbook;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface CookbookService {
    Page<Cookbook> getAllCookbooksByProfileId(Long profileId, Pageable pageable);
    Cookbook getCookbookById(Long cookbookId);
    Cookbook createCookbook(Long profileId, Cookbook cookbook);
    Cookbook updateCookbook(Long cookbookId, Cookbook cookbookRequest);
    ResponseEntity<?> deleteCookbook(Long cookbookId);
    Cookbook getCookbookByName(String name);
}
