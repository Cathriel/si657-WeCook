package com.example.demo.resource;

import com.example.demo.domain.model.AuditModel;
import com.example.demo.domain.model.Profile;

public class TipResource extends AuditModel {

    private Long id;

    private double amount;

    private ProfileResource sender;

    private ProfileResource receiver;

    public Long getId() {
        return id;
    }

    public TipResource setId(Long id) {
        this.id = id;
        return this;
    }

    public double getAmount() {
        return amount;
    }

    public TipResource setAmount(double amount) {
        this.amount = amount;
        return this;
    }

    public ProfileResource getSender() {
        return sender;
    }

    public TipResource setSender(ProfileResource sender) {
        this.sender = sender;
        return this;
    }

    public ProfileResource getReceiver() {
        return receiver;
    }

    public TipResource setReceiver(ProfileResource receiver) {
        this.receiver = receiver;
        return this;
    }
}
