package com.example.demo.resource;

import com.example.demo.domain.model.AuditModel;

import java.util.Date;

public class CommentResource extends AuditModel {

    private Long id;
    private String text;
    private RecipeResource recipe;

    public Long getId() {
        return id;
    }

    public CommentResource setId(Long id) {
        this.id = id;
        return this;
    }

    public String getText() {
        return text;
    }

    public CommentResource setText(String text) {
        this.text = text;
        return this;
    }

    public RecipeResource getRecipe() {
        return recipe;
    }

    public CommentResource setRecipe(RecipeResource recipe) {
        this.recipe = recipe;
        return this;
    }


}
