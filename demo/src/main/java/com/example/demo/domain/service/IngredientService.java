package com.example.demo.domain.service;

import com.example.demo.domain.model.Ingredient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface IngredientService {
    Page<Ingredient> getAllIngredients(Pageable pageable);
    Ingredient getIngredientById(Long ingredientId);
    Ingredient createIngredient(Ingredient ingredient);
    Ingredient updateIngredient(Long ingredientId, Ingredient ingredientRequest);
    ResponseEntity<?> deleteIngredient(Long ingredientId);

    Ingredient getIngredientByName(String name);
    Page<Ingredient> getAllIngredientsByRecipeId(Long recipeId,Pageable pageable);
}
