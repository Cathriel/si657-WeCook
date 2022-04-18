package com.example.demo.resource;

import com.example.demo.domain.model.AuditModel;

public class CookbookResource extends AuditModel {

    private Long id;
    private String name;

    private ProfileResource profile;
    private boolean favourite;


    public Long getId() {
        return id;
    }

    public CookbookResource setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public CookbookResource setName(String name) {
        this.name = name;
        return this;
    }

    public ProfileResource getProfile() {
        return profile;
    }

    public CookbookResource setProfile(ProfileResource profile) {
        this.profile = profile;
        return this;
    }

    public boolean isFavourite() {
        return favourite;
    }

    public CookbookResource setFavourite(boolean favourite) {
        this.favourite = favourite;
        return this;
    }

}
