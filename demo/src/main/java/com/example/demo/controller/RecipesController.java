package com.example.demo.controller;

import com.example.demo.domain.model.*;
import com.example.demo.domain.service.*;
import com.example.demo.resource.*;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;


@RestController
@CrossOrigin
@RequestMapping("/api")
public class RecipesController {
    @Autowired
    private ModelMapper mapper;

    @Autowired
    private RecipeService recipeService;

    @Autowired
    private MultimediaService multimediaService;

    @Autowired
    private CommentService commentService;

    @Autowired
    private IngredientService ingredientService;
    @Autowired
    private ScoreService scoreService;

    @GetMapping("/recipes")
    public Page<RecipeResource> getAllRecipe(Pageable pageable){
        Page<Recipe> recipePage = recipeService.getAllRecipe(pageable);
        List<RecipeResource> resources = recipePage.getContent().stream().map(
                this::convertToResourceRecipe).collect(Collectors.toList());
        return new PageImpl<>(resources,pageable,resources.size());
    }

    @GetMapping("/recipes/{recipeId}")
    public RecipeResource getRecipeById(@PathVariable Long recipeId){
        return convertToResourceRecipe(recipeService.getRecipeById(recipeId));
    }


    @PutMapping("/recipe/{recipeId}")
    public RecipeResource updateRecipe(@PathVariable Long recipeId,@Valid @RequestBody SaveRecipeResource resource){
        return convertToResourceRecipe(recipeService.updateRecipe(recipeId,convertToEntityRecipe(resource)));
    }


    @DeleteMapping("/recipes/{recipeId}")
    public ResponseEntity<?> deleteRecipe(@PathVariable Long recipeId) {
        return recipeService.deleteRecipe(recipeId);
    }


    @PostMapping("/recipes/{recipeId}/multimedia")
    public MultimediaResource createMultimedia(@PathVariable Long recipeId, @Valid @RequestBody SaveMultimediaResource resource){
        return convertToResourceMultimedia(multimediaService.createMultimedia(recipeId,convertToEntityMultimedia(resource)));
    }

    @GetMapping("/recipes/{recipeId}/ingredients")
    public Page<IngredientResource> getAllIngredientsByRecipeId(@PathVariable Long recipeId, Pageable pageable){
        List<IngredientResource> ingredients = ingredientService.getAllIngredientsByRecipeId(recipeId,pageable)
                .getContent().stream().map(this::convertToResourceIngredient)
                .collect(Collectors.toList());
        int ingredientsCount = ingredients.size();
        return new PageImpl<>(ingredients,pageable,ingredientsCount);
    }


    @PostMapping("/recipes/{recipeId}/profiles/{profileId}/comments")
    public CommentResource createComment(@PathVariable Long recipeId, @PathVariable Long profileId, @Valid @RequestBody SaveCommentResource resource){
        return convertToResourceComment(commentService.createComment(recipeId,profileId,convertToEntityComment(resource)));
    }


    @PostMapping("/recipes/{recipeId}/profiles/{profileId}/score")
    public ScoreResource createScore(@PathVariable Long recipeId, @PathVariable Long profileId, @Valid @RequestBody SaveScoreResource resource){
        return convertToResourceScore(scoreService.createScore(recipeId,profileId,convertToEntityScore(resource)));
    }

    private Recipe convertToEntityRecipe(SaveRecipeResource resource) {
        return mapper.map(resource, Recipe.class);
    }

    private RecipeResource convertToResourceRecipe(Recipe entity) {
        return mapper.map(entity, RecipeResource.class);
    }

    private Comment convertToEntityComment(SaveCommentResource resource) {
        return mapper.map(resource, Comment.class);
    }

    private CommentResource convertToResourceComment(Comment entity) {
        return mapper.map(entity, CommentResource.class);
    }

    private Multimedia convertToEntityMultimedia(SaveMultimediaResource resource) {
        return mapper.map(resource, Multimedia.class);
    }

    private MultimediaResource convertToResourceMultimedia(Multimedia entity) {
        return mapper.map(entity, MultimediaResource.class);
    }


    private IngredientResource convertToResourceIngredient(Ingredient entity) {
        return mapper.map(entity, IngredientResource.class);
    }


    private Score convertToEntityScore(SaveScoreResource resource) {
        return mapper.map(resource, Score.class);
    }

    private ScoreResource convertToResourceScore(Score entity) {
        return mapper.map(entity, ScoreResource.class);
    }

}

