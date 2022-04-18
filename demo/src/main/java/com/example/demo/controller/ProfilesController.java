package com.example.demo.controller;

import com.example.demo.domain.model.Profile;
import com.example.demo.domain.model.Recipe;
import com.example.demo.domain.model.Tip;
import com.example.demo.domain.service.RecipeService;
import com.example.demo.domain.service.TipService;
import com.example.demo.resource.*;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@CrossOrigin
@RequestMapping("/api")
public class ProfilesController {

    @Autowired
    private ModelMapper mapper;

    @Autowired
    private RecipeService recipeService;

    @Autowired
    private TipService tipService;

    @PostMapping("profiles/{profileId}/recipes")
    public RecipeResource createRecipe(@PathVariable Long profileId, @Valid @RequestBody SaveRecipeResource resource){
        return convertToResourceRecipe(recipeService.createRecipe(profileId,convertToEntityRecipe(resource)));
    }


    @PostMapping("profiles/{chefId}/profiles/{readerId}/tips")
    public TipResource createTip(@PathVariable Long chefId, @PathVariable Long readerId, @RequestBody SaveTipResource resource){
        return convertToResourceTip(tipService.createTip(chefId,readerId,convertToEntityTip(resource)));
    }

    private Recipe convertToEntityRecipe(SaveRecipeResource resource) {
        return mapper.map(resource, Recipe.class);
    }
    private RecipeResource convertToResourceRecipe(Recipe entity){
        return mapper.map(entity, RecipeResource.class);
    }

    private TipResource convertToResourceTip(Tip entity) {
        return mapper.map(entity, TipResource.class);
    }
    private Tip convertToEntityTip(SaveTipResource resource) {
        return mapper.map(resource, Tip.class);
    }

}
