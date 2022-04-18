package com.example.demo.service;

import com.example.demo.domain.model.Ingredient;
import com.example.demo.domain.repository.IngredientRepository;
import com.example.demo.domain.repository.RecipeRepository;
import com.example.demo.domain.service.IngredientService;
import com.example.demo.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IngredientServiceImpl implements IngredientService {

    @Autowired
    private IngredientRepository ingredientRepository;

    @Autowired
    private RecipeRepository recipeRepository;

    @Override
    public Page<Ingredient> getAllIngredients(Pageable pageable) {
        return ingredientRepository.findAll(pageable);
    }

    @Override
    public Ingredient getIngredientById(Long ingredientId) {
        return ingredientRepository.findById(ingredientId)
                .orElseThrow(()->new ResourceNotFoundException("Ingredient", "Id", ingredientId));
    }

    @Override
    public Ingredient createIngredient(Ingredient ingredient) {
        return ingredientRepository.save(ingredient);
    }

    @Override
    public Ingredient updateIngredient(Long ingredientId, Ingredient ingredientRequest) {
        Ingredient ingredient = ingredientRepository.findById(ingredientId)
                .orElseThrow(()->new ResourceNotFoundException("Ingredient", "Id", ingredientId));
        ingredient.setName(ingredientRequest.getName())
                .setCalories(ingredientRequest.getCalories())
                .setPrice(ingredientRequest.getPrice());
        return ingredientRepository.save(ingredient);
    }

    @Override
    public ResponseEntity<?> deleteIngredient(Long ingredientId) {
        Ingredient ingredient = ingredientRepository.findById(ingredientId)
                .orElseThrow(()->new ResourceNotFoundException("Ingredient", "Id", ingredientId));
        ingredientRepository.delete(ingredient);
        return ResponseEntity.ok().build();
    }

    @Override
    public Ingredient getIngredientByName(String name) {
        return ingredientRepository.findByName(name)
                .orElseThrow(()->new ResourceNotFoundException("Ingredient", "Name", name));
    }

    @Override
    public Page<Ingredient> getAllIngredientsByRecipeId(Long recipeId, Pageable pageable) {
        return recipeRepository.findById(recipeId).map(
                recipe -> {
                    List<Ingredient> ingredientList = recipe.getIngredients();
                    int ingredientsCount = ingredientList.size();
                    return new PageImpl<>(ingredientList, pageable, ingredientsCount);}
        ).orElseThrow(()->new ResourceNotFoundException("Recipe", "Id", recipeId));
    }
}
