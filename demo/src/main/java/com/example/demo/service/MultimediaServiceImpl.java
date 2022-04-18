package com.example.demo.service;

import com.example.demo.domain.model.Multimedia;
import com.example.demo.domain.model.Recipe;
import com.example.demo.domain.repository.MultimediaRepository;
import com.example.demo.domain.repository.RecipeRepository;
import com.example.demo.domain.service.MultimediaService;
import com.example.demo.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MultimediaServiceImpl implements MultimediaService {

    @Autowired
    private MultimediaRepository multimediaRepository;

    @Autowired
    private RecipeRepository recipeRepository;

    @Override
    public Page<Multimedia> getAllMultimedia(Pageable pageable) {
        return multimediaRepository.findAll(pageable);
    }

    @Override
    public Multimedia getMultimediaById(Long multimediaId) {
        return multimediaRepository.findById(multimediaId)
                .orElseThrow(()->new ResourceNotFoundException("Multimedia","Id",multimediaId));
    }

    @Override
    public Multimedia createMultimedia(Long recipeId, Multimedia multimedia) {
        Recipe recipe = recipeRepository.findById(recipeId)
                .orElseThrow(()->new ResourceNotFoundException("Recipe","Id",recipeId));
        recipe.addMultimedia(multimedia);
        return multimediaRepository.save(multimedia);
    }


    @Override
    public Multimedia updateMultimedia(Long multimediaId, Multimedia multimediaRequest) {
        Multimedia multimedia=multimediaRepository.findById(multimediaId)
                .orElseThrow(()->new ResourceNotFoundException("Multimedia","Id",multimediaId));
        multimedia.setUrl(multimediaRequest.getUrl());
        return multimediaRepository.save(multimedia);
    }

    @Override
    public ResponseEntity<?> deleteMultimedia(Long multimediaId) {
        Multimedia multimedia=multimediaRepository.findById(multimediaId)
                .orElseThrow(()->new ResourceNotFoundException("Multimedia","Id",multimediaId));
        multimediaRepository.delete(multimedia);
        return ResponseEntity.ok().build();
    }

    @Override
    public Page<Multimedia> getAllMultimediaByRecipeId(Long recipeId, Pageable pageable) {

        return recipeRepository.findById(recipeId).map(
                recipe -> {
                    List<Multimedia> multimediaList=recipe.getMultimedia();
                    int multimediaCount=multimediaList.size();
                    return new PageImpl<>(multimediaList,pageable,multimediaCount);
                }
        ).orElseThrow(()->new ResourceNotFoundException("Recipe","Id",recipeId));
    }

}
