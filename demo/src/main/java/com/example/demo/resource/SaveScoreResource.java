package com.example.demo.resource;

import javax.validation.constraints.NotNull;

public class SaveScoreResource {

    @NotNull
    private int starQuantity;

    public int getStarQuantity() {
        return starQuantity;
    }

    public SaveScoreResource setStarQuantity(int starQuantity) {
        this.starQuantity = starQuantity;
        return this;
    }

}
