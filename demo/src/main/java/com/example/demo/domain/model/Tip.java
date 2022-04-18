package com.example.demo.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Table(name = "tips")
public class Tip extends AuditModel{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true,nullable = false)
    private Long id;

    @NotNull
    private double amount;

    @ManyToOne(fetch = FetchType.LAZY,optional = false)
    @JoinColumn(name = "profile_receiver_id",nullable = false)
    @JsonIgnore
    private Profile receiver;

    @ManyToOne(fetch = FetchType.LAZY,optional = false)
    @JoinColumn(name = "profile_sender_id",nullable = false)
    @JsonIgnore
    private Profile sender;


    public Long getId() {
        return id;
    }

    public Tip setId(Long id) {
        this.id = id;
        return this;
    }

    public double getAmount() {
        return amount;
    }

    public Tip setAmount(double amount) {
        this.amount = amount;
        return this;
    }

    public Profile getReceiver() {
        return receiver;
    }

    public Tip setReceiver(Profile receiver) {
        this.receiver = receiver;
        return this;
    }

    public Profile getSender() {
        return sender;
    }

    public Tip setSender(Profile sender) {
        this.sender = sender;
        return this;
    }

}
