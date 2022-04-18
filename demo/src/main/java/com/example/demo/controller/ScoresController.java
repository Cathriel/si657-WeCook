package com.example.demo.controller;

import com.example.demo.domain.model.Score;
import com.example.demo.domain.service.ScoreService;
import com.example.demo.resource.SaveScoreResource;
import com.example.demo.resource.ScoreResource;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@CrossOrigin
@RequestMapping("/api")
public class ScoresController {
    @Autowired
    private ModelMapper mapper;

    @Autowired
    private ScoreService scoreService;


    @GetMapping("/scores/{scoreId}")
    public ScoreResource getScoreById(@PathVariable Long scoreId){
        return convertToResource(scoreService.getScoreById(scoreId));
    }

    @PutMapping("/scores/{scoreId}")
    public ScoreResource updateScore(@PathVariable Long scoreId, @Valid @RequestBody SaveScoreResource resource){
        return convertToResource(scoreService.updateScore(scoreId,convertToEntity(resource)));
    }

    @DeleteMapping("/scores/{scoreId}")
    public ResponseEntity<?> deleteScore(@PathVariable Long scoreId){
        return scoreService.deleteScore(scoreId);
    }


    private Score convertToEntity(SaveScoreResource resource) {
        return mapper.map(resource, Score.class);
    }

    private ScoreResource convertToResource(Score entity) {
        return mapper.map(entity, ScoreResource.class);
    }

}

