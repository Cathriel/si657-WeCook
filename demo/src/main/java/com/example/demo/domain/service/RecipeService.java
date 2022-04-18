package com.example.demo.domain.service;

import com.example.demo.domain.model.Ingredient;
import com.example.demo.domain.model.Recipe;
import com.example.demo.domain.model.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface RecipeService {

    Page<Recipe> getAllRecipe(Pageable pageable);

    Recipe getRecipeById(Long recipeId);

    Recipe createRecipe(Long profileId, Recipe recipe);

    Recipe updateRecipe(Long recipeId, Recipe recipeRequest);

    ResponseEntity<?> deleteRecipe(Long recipeId);

    Page<Tag> getAllTagsByRecipeId(Long recipeId, Pageable pageable);

    Page<Ingredient> getAllIngredientsByRecipeId(Long recipeId, Pageable pageable);

    Recipe assignRecipeTag(Long recipeId, Long tagId);

    Recipe unassignRecipeTag(Long recipeId, Long tagId);

    Recipe assignRecipeIngredient(Long recipeId, Long ingredientId);

    Recipe unassignRecipeIngredient(Long recipeId, Long ingredientId);

    Recipe assignRecipeCookbook(Long recipeId, Long cookbookId);

    Recipe unassignRecipeCookbook(Long recipeId, Long cookbookId);
}

