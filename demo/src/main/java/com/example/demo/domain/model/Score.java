package com.example.demo.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name ="scores")
public class Score extends AuditModel{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private int starQuantity;

    @ManyToOne(fetch = FetchType.LAZY,optional = false)
    @JoinColumn(name="recipe_id",nullable = false)
    @JsonIgnore
    private Recipe recipe;

    @ManyToOne(fetch = FetchType.LAZY,optional = false)
    @JoinColumn(name="profile_id",nullable = false)
    @JsonIgnore
    private Profile profile;

    public Score() {
    }

    public Long getId() {
        return id;
    }

    public Score setId(Long id) {
        this.id = id;
        return this;
    }

    public int getStarQuantity() {
        return starQuantity;
    }

    public Score setStarQuantity(int starQuantity) {
        this.starQuantity = starQuantity;
        return this;
    }

    public Recipe getRecipe() {
        return recipe;
    }

    public Score setRecipe(Recipe recipe) {
        this.recipe = recipe;
        return this;
    }

    public Profile getProfile() {
        return profile;
    }

    public Score setProfile(Profile profile) {
        this.profile = profile;
        return this;
    }
}
