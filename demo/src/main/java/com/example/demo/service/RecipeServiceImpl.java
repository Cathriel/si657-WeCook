package com.example.demo.service;

import com.example.demo.domain.model.*;
import com.example.demo.domain.repository.*;
import com.example.demo.domain.service.RecipeService;
import com.example.demo.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecipeServiceImpl implements RecipeService {

    @Autowired
    private RecipeRepository recipeRepository;

    @Autowired
    private TagRepository tagRepository;

    @Autowired
    private MultimediaRepository multimediaRepository;

    @Autowired
    private IngredientRepository ingredientRepository;

    @Autowired
    private CookbookRepository cookbookRepository;

    @Autowired
    private ScoreRepository scoreRepository;

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private ProfileRepository profileRepository;

    @Override
    public Page<Recipe> getAllRecipe(Pageable pageable) {
        return recipeRepository.findAll(pageable);
    }

    @Override
    public Recipe getRecipeById(Long recipeId) {
        return recipeRepository.findById(recipeId)
                .orElseThrow(()->new ResourceNotFoundException("Recipe","Id",recipeId));
    }

    @Override
    public Recipe createRecipe(Long profileId, Recipe recipe) {
        Profile profile = profileRepository.findById(profileId)
                .orElseThrow(()->new ResourceNotFoundException("Profile","Id",profileId));
        return recipeRepository.save(recipe.setProfile(profile));
    }

    @Override
    public Recipe updateRecipe(Long recipeId, Recipe recipeRequest) {
        Recipe recipe=recipeRepository.findById(recipeId)
                .orElseThrow(()->new ResourceNotFoundException("Recipe","Id",recipeId));
        recipe.setName(recipeRequest.getName());

        return recipeRepository.save(recipe);
    }

    @Override
    public ResponseEntity<?> deleteRecipe(Long recipeId) {
        Recipe recipe=recipeRepository.findById(recipeId)
                .orElseThrow(()->new ResourceNotFoundException("Recipe","Id",recipeId));

        Pageable pageable= PageRequest.of(0,10000);
        Page <Score> scorePage=scoreRepository.findByRecipeId(recipeId,pageable);
        Page <Comment> commentPage=commentRepository.findByRecipeId(recipeId,pageable);

        List<Multimedia> multimediaList = recipe.getMultimedia();

        if(multimediaList!=null)
            multimediaList.forEach(multimedia -> {
                multimediaRepository.delete(multimedia);
            });


        if(scorePage!=null)
            scorePage.forEach(score -> {scoreRepository.delete(score);});

        if(commentPage!=null)
            commentPage.forEach(comment -> {commentRepository.delete(comment);});

        recipeRepository.delete(recipe);

        return ResponseEntity.ok().build();
    }

    @Override
    public Page<Tag> getAllTagsByRecipeId(Long recipeId, Pageable pageable) {
        return recipeRepository.findById(recipeId).map(
                recipe -> {
                    List<Tag> tags=recipe.getTags();
                    int tagCount=tags.size();
                    return new PageImpl<>(tags,pageable,tagCount);
                }
        ).orElseThrow(()->new ResourceNotFoundException("Recipe","Id",recipeId));
    }

    @Override
    public Page<Ingredient> getAllIngredientsByRecipeId(Long recipeId, Pageable pageable) {
        return recipeRepository.findById(recipeId).map(
                recipe -> {
                    List<Ingredient> ingredients=recipe.getIngredients();
                    int ingredientCount=ingredients.size();
                    return new PageImpl<>(ingredients,pageable,ingredientCount);
                }
        ).orElseThrow(()->new ResourceNotFoundException("Recipe","Id",recipeId));
    }

    @Override
    public Recipe assignRecipeTag(Long recipeId, Long tagId) {
        Tag tag=tagRepository.findById(tagId)
                .orElseThrow(()->new ResourceNotFoundException("Tag","Id",tagId));
        return recipeRepository.findById(recipeId).map(
                recipe -> recipeRepository.save(recipe.assignTag(tag))
        ).orElseThrow(()->new ResourceNotFoundException("Recipe","Id",recipeId));
    }

    @Override
    public Recipe unassignRecipeTag(Long recipeId, Long tagId) {
        Tag tag=tagRepository.findById(tagId)
                .orElseThrow(()->new ResourceNotFoundException("Tag","Id",tagId));
        return recipeRepository.findById(recipeId).map(
                recipe -> recipeRepository.save(recipe.unAssignTag(tag))
        ).orElseThrow(()->new ResourceNotFoundException("Recipe","Id",recipeId));
    }

    @Override
    public Recipe assignRecipeIngredient(Long recipeId, Long ingredientId) {
        Ingredient ingredient=ingredientRepository.findById(ingredientId)
                .orElseThrow(()->new ResourceNotFoundException("Ingredient","Id",ingredientId));
        return recipeRepository.findById(recipeId).map(
                recipe -> recipeRepository.save(recipe.assignIngredient(ingredient))
        ).orElseThrow(()->new ResourceNotFoundException("Recipe","Id",recipeId));
    }

    @Override
    public Recipe unassignRecipeIngredient(Long recipeId, Long ingredientId) {
        Ingredient ingredient=ingredientRepository.findById(ingredientId)
                .orElseThrow(()->new ResourceNotFoundException("Ingredient","Id",ingredientId));
        return recipeRepository.findById(recipeId).map(
                recipe -> recipeRepository.save(recipe.unassignIngredient(ingredient))
        ).orElseThrow(()->new ResourceNotFoundException("Recipe","Id",recipeId));
    }

    @Override
    public Recipe assignRecipeCookbook(Long recipeId, Long cookbookId) {
        Cookbook cookbook=cookbookRepository.findById(cookbookId)
                .orElseThrow(()->new ResourceNotFoundException("Cookbook","Id",cookbookId));
        return recipeRepository.findById(recipeId).map(
                recipe -> recipeRepository.save(recipe.assignCookbook(cookbook))
        ).orElseThrow(()->new ResourceNotFoundException("Recipe","Id",recipeId));
    }

    @Override
    public Recipe unassignRecipeCookbook(Long recipeId, Long cookbookId) {
        Cookbook cookbook=cookbookRepository.findById(cookbookId)
                .orElseThrow(()->new ResourceNotFoundException("Cookbook","Id",cookbookId));
        return recipeRepository.findById(recipeId).map(
                recipe -> recipeRepository.save(recipe.unassignCookbook(cookbook))
        ).orElseThrow(()->new ResourceNotFoundException("Recipe","Id",recipeId));
    }
}
