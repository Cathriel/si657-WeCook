package com.example.demo.service;

import com.example.demo.domain.model.Score;
import com.example.demo.domain.repository.ProfileRepository;
import com.example.demo.domain.repository.RecipeRepository;
import com.example.demo.domain.repository.ScoreRepository;
import com.example.demo.domain.service.ScoreService;
import com.example.demo.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class ScoreServiceImpl implements ScoreService {
    @Autowired
    private ScoreRepository scoreRepository;

    @Autowired
    private RecipeRepository recipeRepository;

    @Autowired
    private ProfileRepository profileRepository;

    @Override
    public Page<Score> getAllScoresByRecipeId(Long recipeId, Pageable pageable) {
        return scoreRepository.findByRecipeId(recipeId, pageable);
    }

    @Override
    public Score getScoreById(Long scoreId) {
        return scoreRepository.findById(scoreId)
                .orElseThrow(()->new ResourceNotFoundException("Score", "Id", scoreId));
    }

    @Override
    public Score createScore(Long recipeId, Long profileId, Score score) {
        return recipeRepository.findById(recipeId).map(
                recipe -> {score.setRecipe(recipe);
                    profileRepository.findById(profileId).map(
                                    profile -> {score.setProfile(profile);
                                        return scoreRepository.save(score);})
                            .orElseThrow(()->new ResourceNotFoundException("Profile", "Id", profileId));
                    return scoreRepository.save(score);}
        ).orElseThrow(()-> new ResourceNotFoundException("Recipe", "Id", recipeId));
    }

    @Override
    public Score updateScore(Long scoreId, Score scoreRequest) {
        Score score = scoreRepository.findById(scoreId)
                .orElseThrow(()->new ResourceNotFoundException("Score", "Id", scoreId));
        score.setStarQuantity(scoreRequest.getStarQuantity());
        return scoreRepository.save(score);
    }

    @Override
    public ResponseEntity<?> deleteScore(Long scoreId) {
        Score score = scoreRepository.findById(scoreId)
                .orElseThrow(()->new ResourceNotFoundException("Score", "Id", scoreId));
        scoreRepository.delete(score);
        return ResponseEntity.ok().build();
    }
}
