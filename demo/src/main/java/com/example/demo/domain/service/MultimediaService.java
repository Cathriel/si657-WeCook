package com.example.demo.domain.service;

import com.example.demo.domain.model.Multimedia;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface MultimediaService {

    Page<Multimedia> getAllMultimedia(Pageable pageable);

    Multimedia getMultimediaById(Long multimediaId);

    Multimedia createMultimedia(Long recipeId,Multimedia multimedia);

    Multimedia updateMultimedia(Long multimediaId, Multimedia multimediaRequest);

    ResponseEntity<?> deleteMultimedia(Long multimediaId);

    Page<Multimedia> getAllMultimediaByRecipeId(Long recipeId, Pageable pageable);
}
