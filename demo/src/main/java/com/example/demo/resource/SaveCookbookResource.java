package com.example.demo.resource;

import javax.persistence.Lob;
import javax.validation.constraints.NotNull;

public class SaveCookbookResource {

    @NotNull
    @Lob
    private String name;

    public String getName() {
        return name;
    }

    public SaveCookbookResource setName(String name) {
        this.name = name;
        return this;
    }

}
