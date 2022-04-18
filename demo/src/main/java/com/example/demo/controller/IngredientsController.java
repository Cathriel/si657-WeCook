package com.example.demo.controller;


import com.example.demo.domain.model.Ingredient;
import com.example.demo.domain.model.Recipe;
import com.example.demo.domain.service.IngredientService;
import com.example.demo.domain.service.RecipeService;
import com.example.demo.resource.IngredientResource;
import com.example.demo.resource.RecipeResource;
import com.example.demo.resource.SaveIngredientResource;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@CrossOrigin
@RequestMapping("/api")
public class IngredientsController {
    @Autowired
    private ModelMapper mapper;

    @Autowired
    private IngredientService ingredientService;

    @Autowired
    private RecipeService recipeService;

    @PostMapping("/ingredients")
    public IngredientResource createIngredient(@Valid @RequestBody SaveIngredientResource resource){
        return convertToResourceIngredient(ingredientService.createIngredient(convertToEntityIngredient(resource)));
    }

    @PutMapping("/ingredients/{ingredientId}")
    public IngredientResource updateIngredient(@PathVariable Long ingredientId, @Valid @RequestBody SaveIngredientResource resource){
        return convertToResourceIngredient(ingredientService.updateIngredient(ingredientId,convertToEntityIngredient(resource)));
    }

    @DeleteMapping("/ingredients/{ingredientId}")
    public ResponseEntity<?> deleteIngredient(@PathVariable Long ingredientId){
        return ingredientService.deleteIngredient(ingredientId);
    }

    private Ingredient convertToEntityIngredient(SaveIngredientResource resource){return mapper.map(resource,Ingredient.class);}
    private IngredientResource convertToResourceIngredient(Ingredient entity){return mapper.map(entity,IngredientResource.class);}


}
