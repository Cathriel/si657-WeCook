package com.example.demo.resource;

import com.example.demo.domain.model.AuditModel;

public class IngredientResource extends AuditModel {

    private Long id;
    private String name;
    private double calories;
    private double price;

    public Long getId() {
        return id;
    }

    public IngredientResource setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public IngredientResource setName(String name) {
        this.name = name;
        return this;
    }

    public double getCalories() {
        return calories;
    }

    public IngredientResource setCalories(double calories) {
        this.calories = calories;
        return this;
    }

    public double getPrice() {
        return price;
    }

    public IngredientResource setPrice(double price) {
        this.price = price;
        return this;
    }

}
