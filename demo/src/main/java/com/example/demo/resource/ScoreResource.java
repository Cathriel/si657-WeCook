package com.example.demo.resource;

import com.example.demo.domain.model.AuditModel;

public class ScoreResource extends AuditModel {

    private Long id;
    private int starQuantity;

    public Long getId() {
        return id;
    }

    public ScoreResource setId(Long id) {
        this.id = id;
        return this;
    }

    public int getStarQuantity() {
        return starQuantity;
    }

    public ScoreResource setStarQuantity(int starQuantity) {
        this.starQuantity = starQuantity;
        return this;
    }


}
