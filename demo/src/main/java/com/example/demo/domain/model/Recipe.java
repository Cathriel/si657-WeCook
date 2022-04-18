package com.example.demo.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Table(name = "recipes")
public class Recipe extends AuditModel{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @ManyToMany(fetch=FetchType.LAZY,
            cascade = {CascadeType.PERSIST,CascadeType.MERGE}
    ,mappedBy = "recipes")
    private List<Cookbook> cookbooks;

    @NotNull
    private String name;

    @NotNull
    private int views;

    @NotNull
    private boolean exclusive;

    @NotNull
    private String preparation;

    @NotNull
    private String recommendation;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "multimedia_id",nullable = false)
    private List<Multimedia> multimedia;

    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {CascadeType.PERSIST,CascadeType.MERGE},
            mappedBy = "recipes")
    private List<Tag> tags;

    @ManyToMany(fetch=FetchType.LAZY,
            cascade = {CascadeType.PERSIST,CascadeType.MERGE},
            mappedBy = "recipes")
    private List<Ingredient> ingredients;


    @ManyToOne(fetch = FetchType.LAZY,optional = false)
    @JoinColumn(name = "profile_id",nullable = false)
    @JsonIgnore
    private Profile profile;

    public List<Cookbook> getCookbooks() {
        return cookbooks;
    }
    public boolean isInCookbook(Cookbook cookbook){ return this.getCookbooks().contains(cookbook);}

    public Recipe assignCookbook(Cookbook cookbook){
        if(!this.isInCookbook(cookbook))
            this.getCookbooks().add(cookbook);
        return this;
    }

    public Recipe unassignCookbook(Cookbook cookbook){
        if(this.isInCookbook(cookbook))
            this.getCookbooks().remove(cookbook);
        return this;
    }

    public List<Ingredient> getIngredients() {
        return ingredients;
    }
    public boolean hasIngredient(Ingredient ingredient){ return this.getIngredients().contains(ingredient);}

    public Recipe assignIngredient(Ingredient ingredient){
        if(!this.hasIngredient(ingredient))
            this.getIngredients().add(ingredient);
        return this;
    }

    public Recipe unassignIngredient(Ingredient ingredient){
        if(this.hasIngredient(ingredient))
            this.getIngredients().remove(ingredient);
        return this;
    }

    public Long getId() {
        return Id;
    }

    public Recipe setId(Long id) {
        Id = id;
        return this;
    }

    public Recipe setCookbooks(List<Cookbook> cookbooks) {
        this.cookbooks = cookbooks;
        return this;
    }

    public String getName() {
        return name;
    }

    public Recipe setName(String name) {
        this.name = name;
        return this;
    }

    public int getViews() {
        return views;
    }

    public Recipe setViews(int views) {
        this.views = views;
        return this;
    }

    public boolean isExclusive() {
        return exclusive;
    }

    public Recipe setExclusive(boolean exclusive) {
        this.exclusive = exclusive;
        return this;
    }

    public String getPreparation() {
        return preparation;
    }

    public Recipe setPreparation(String preparation) {
        this.preparation = preparation;
        return this;
    }

    public String getRecommendation() {
        return recommendation;
    }

    public Recipe setRecommendation(String recommendation) {
        this.recommendation = recommendation;
        return this;
    }

    public List<Multimedia> getMultimedia() {
        return multimedia;
    }

    public Recipe setMultimedia(List<Multimedia> multimedia) {
        this.multimedia = multimedia;
        return this;
    }

    public List<Tag> getTags() {
        return tags;
    }

    public boolean isTagged(Tag tag){ return this.getTags().contains(tag);}

    public Recipe assignTag(Tag tag){
        if(!this.isTagged(tag))
            this.getTags().add(tag);
        return this;
    }

    public Recipe unAssignTag(Tag tag){
        if(this.isTagged(tag))
            this.getTags().remove(tag);
        return this;
    }

    public Profile getProfile() {
        return profile;
    }

    public Recipe setProfile(Profile profile) {
        this.profile = profile;
        return this;
    }

    public void addMultimedia(Multimedia multimedia){
        this.getMultimedia().add(multimedia);
    }
}
