package com.example.demo.domain.service;

import com.example.demo.domain.model.Score;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface ScoreService {
    Page<Score> getAllScoresByRecipeId(Long recipeId, Pageable pageable);
    Score getScoreById(Long scoreId);
    Score createScore(Long recipeId, Long profileId, Score score);
    Score updateScore(Long scoreId,Score scoreRequest);
    ResponseEntity<?> deleteScore(Long scoreId);
}
