package com.example.demo.resource;

import javax.validation.constraints.NotNull;

public class SaveSubscriptionResource {

    @NotNull
    private double amount;

    public double getAmount() {
        return amount;
    }

    public SaveSubscriptionResource setAmount(double amount) {
        this.amount = amount;
        return this;
    }
}
