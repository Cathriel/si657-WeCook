package com.example.demo.resource;

import javax.validation.constraints.NotNull;

public class SaveIngredientResource {
    @NotNull
    private String name;

    @NotNull
    private double calories;

    @NotNull
    private double price;

    public String getName() {
        return name;
    }

    public SaveIngredientResource setName(String name) {
        this.name = name;
        return this;
    }

    public double getCalories() {
        return calories;
    }

    public SaveIngredientResource setCalories(double calories) {
        this.calories = calories;
        return this;
    }

    public double getPrice() {
        return price;
    }

    public SaveIngredientResource setPrice(double price) {
        this.price = price;
        return this;
    }

}
