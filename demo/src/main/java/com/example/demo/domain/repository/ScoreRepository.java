package com.example.demo.domain.repository;

import com.example.demo.domain.model.Score;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScoreRepository extends JpaRepository<Score, Long> {
    Page<Score> findByRecipeId(Long recipeId, Pageable pageable);
}
