package com.example.demo.domain.repository;

import com.example.demo.domain.model.Cookbook;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CookbookRepository extends JpaRepository<Cookbook,Long> {
    Page<Cookbook> findByProfileId(Long profileId, Pageable pageable);
    public Optional<Cookbook> findByName(String name);
}