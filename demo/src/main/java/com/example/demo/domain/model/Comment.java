package com.example.demo.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Table(name ="comments")
public class Comment extends AuditModel{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Lob
    private String text;


    @ManyToOne(fetch = FetchType.LAZY,optional = false)
    @JoinColumn(name="recipe_id",nullable = false)
    @JsonIgnore
    private Recipe recipe;

    @ManyToOne(fetch = FetchType.LAZY,optional = false)
    @JoinColumn(name="profile_id",nullable = false)
    @JsonIgnore
    private Profile profile;

    public Comment() {
    }

    public Long getId() {
        return id;
    }

    public Comment setId(Long id) {
        this.id = id;
        return this;
    }

    public String getText() {
        return text;
    }

    public Comment setText(String text) {
        this.text = text;
        return this;
    }

    public Recipe getRecipe() {
        return recipe;
    }

    public Comment setRecipe(Recipe recipe) {
        this.recipe = recipe;
        return this;
    }

    public Profile getProfile() {
        return profile;
    }

    public Comment setProfile(Profile profile) {
        this.profile = profile;
        return this;
    }
}

