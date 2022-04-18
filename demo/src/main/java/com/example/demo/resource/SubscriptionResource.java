package com.example.demo.resource;

import com.example.demo.domain.model.AuditModel;

import java.util.Date;

public class SubscriptionResource extends AuditModel {

    private Long id;

    private double amount;

    private Long chef_id;

    private Long reader_id;

    private Date subscriptionDate;

    public Long getId() {
        return id;
    }

    public SubscriptionResource setId(Long id) {
        this.id = id;
        return this;
    }

    public double getAmount() {
        return amount;
    }

    public SubscriptionResource setAmount(double amount) {
        this.amount = amount;
        return this;
    }

    public Long getChef_id() {
        return chef_id;
    }

    public SubscriptionResource setChef_id(Long chef_id) {
        this.chef_id = chef_id;
        return this;
    }

    public Long getReader_id() {
        return reader_id;
    }

    public SubscriptionResource setReader_id(Long reader_id) {
        this.reader_id = reader_id;
        return this;
    }

    public Date getSubscriptionDate() {
        return subscriptionDate;
    }

    public SubscriptionResource setSubscriptionDate(Date subscriptionDate) {
        this.subscriptionDate = subscriptionDate;
        return this;
    }
}
