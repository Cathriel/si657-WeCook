package com.example.demo.resource;

import javax.validation.constraints.NotNull;

public class SaveTipResource {

    @NotNull
    private double amount;

    public double getAmount() {
        return amount;
    }

    public SaveTipResource setAmount(double amount) {
        this.amount = amount;
        return this;
    }
}
