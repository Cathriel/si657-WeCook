package com.example.demo.resource;

import javax.persistence.Lob;
import javax.validation.constraints.NotNull;

public class SaveRecipeResource {

    @NotNull
    private String name;

    @NotNull
    @Lob
    private String preparation;

    @NotNull
    private boolean exclusive;

    @NotNull
    @Lob
    private String recommendation;

    public String getName() {
        return name;
    }

    public SaveRecipeResource setName(String name) {
        this.name = name;
        return this;
    }

    public String getPreparation() {
        return preparation;
    }

    public SaveRecipeResource setPreparation(String preparation) {
        this.preparation = preparation;
        return this;
    }

    public String getRecommendation() {
        return recommendation;
    }

    public SaveRecipeResource setRecommendation(String recommendation) {
        this.recommendation = recommendation;
        return this;
    }

    public boolean isExclusive() {
        return exclusive;
    }

    public SaveRecipeResource setExclusive(boolean exclusive) {
        this.exclusive = exclusive;
        return this;
    }
}
