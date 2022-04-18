package com.example.demo.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Table(name = "subscriptions")
public class Subscription extends AuditModel{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true,nullable = false)
    private Long id;

    @NotNull
    private double amount;

    @ManyToOne(fetch = FetchType.LAZY,optional = false)
    @JoinColumn(name = "profile_chef_id",nullable = false)
    @JsonIgnore
    private Profile chef;

    @ManyToOne(fetch = FetchType.LAZY,optional = false)
    @JoinColumn(name = "profile_reader_id",nullable = false)
    @JsonIgnore
    private Profile reader;


    @NotNull
    private Date endDate;

    @NotNull
    private Date subscriptionDate;

    public Long getId() {
        return id;
    }

    public Subscription setId(Long id) {
        this.id = id;
        return this;
    }

    public double getAmount() {
        return amount;
    }

    public Subscription setAmount(double amount) {
        this.amount = amount;
        return this;
    }

    public Profile getChef() {
        return chef;
    }

    public Subscription setChef(Profile chef) {
        this.chef = chef;
        return this;
    }

    public Profile getReader() {
        return reader;
    }

    public Subscription setReader(Profile reader) {
        this.reader = reader;
        return this;
    }

    public Date getEndDate() {
        return endDate;
    }

    public Subscription setEndDate(Date endDate) {
        this.endDate = endDate;
        return this;
    }

    public Date getSubscriptionDate() {
        return subscriptionDate;
    }

    public Subscription setSubscriptionDate(Date subscriptionDate) {
        this.subscriptionDate = subscriptionDate;
        return this;
    }
}
