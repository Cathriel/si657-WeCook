package com.example.demo.resource;

import javax.validation.constraints.NotNull;

public class SaveTagResource {

    @NotNull
    private String name;

    public String getName() {
        return name;
    }

    public SaveTagResource setName(String name) {
        this.name = name;
        return this;
    }
}
