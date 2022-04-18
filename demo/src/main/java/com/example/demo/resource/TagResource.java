package com.example.demo.resource;

import com.example.demo.domain.model.AuditModel;

import java.util.List;

public class TagResource extends AuditModel {

    private Long id;

    private String name;

    private List<RecipeResource> recipes;

    public Long getId() {
        return id;
    }

    public TagResource setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public TagResource setName(String name) {
        this.name = name;
        return this;
    }

    public List<RecipeResource> getRecipes() {
        return recipes;
    }

}
