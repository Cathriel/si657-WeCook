package com.example.demo.resource;

import com.example.demo.domain.model.AuditModel;

import java.util.List;

public class RecipeResource extends AuditModel {

    private Long id;

    private List<CookbookResource> cookbooks;

    private String name;


    private int views;

    private boolean exclusive;

    private List<TagResource> tags;

    private List<ScoreResource> scores;

    private List<IngredientResource> ingredients;

    private List<MultimediaResource> multimedia;

    private String preparation;

    private String recommendation;

    public Long getId() {
        return id;
    }

    public RecipeResource setId(Long id) {
        this.id = id;
        return this;
    }

    public List<CookbookResource> getCookbooks() {
        return cookbooks;
    }

    public RecipeResource setCookbooks(List<CookbookResource> cookbooks) {
        this.cookbooks = cookbooks;
        return this;
    }

    public String getName() {
        return name;
    }

    public RecipeResource setName(String name) {
        this.name = name;
        return this;
    }


    public int getViews() {
        return views;
    }

    public RecipeResource setViews(int views) {
        this.views = views;
        return this;
    }

    public boolean isExclusive() {
        return exclusive;
    }

    public RecipeResource setExclusive(boolean exclusive) {
        this.exclusive = exclusive;
        return this;
    }

    public List<TagResource> getTags() {
        return tags;
    }

    public RecipeResource setTags(List<TagResource> tags) {
        this.tags = tags;
        return this;
    }

    public List<ScoreResource> getScores() {
        return scores;
    }

    public RecipeResource setScores(List<ScoreResource> scores) {
        this.scores = scores;
        return this;
    }

    public List<IngredientResource> getIngredients() {
        return ingredients;
    }

    public RecipeResource setIngredients(List<IngredientResource> ingredients) {
        this.ingredients = ingredients;
        return this;
    }

    public List<MultimediaResource> getMultimedia() {
        return multimedia;
    }

    public RecipeResource setMultimedia(List<MultimediaResource> multimedia) {
        this.multimedia = multimedia;
        return this;
    }

    public String getPreparation() {
        return preparation;
    }

    public RecipeResource setPreparation(String preparation) {
        this.preparation = preparation;
        return this;
    }

    public String getRecommendation() {
        return recommendation;
    }

    public RecipeResource setRecommendation(String recommendation) {
        this.recommendation = recommendation;
        return this;
    }
}
