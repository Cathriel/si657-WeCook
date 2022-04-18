package com.example.demo.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Table(name = "follows")
public class Follow extends AuditModel{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private Date followDate;

    @ManyToOne(fetch = FetchType.LAZY,optional = false)
    @JoinColumn(name = "profile_chef_id",nullable = false)
    @JsonIgnore
    private Profile chef;

    @ManyToOne(fetch = FetchType.LAZY,optional = false)
    @JoinColumn(name = "profile_reader_id",nullable = false)
    @JsonIgnore
    private Profile reader;

    public Long getId() {
        return id;
    }

    public Follow setId(Long id) {
        this.id = id;
        return this;
    }

    public Date getFollowDate() {
        return followDate;
    }

    public Follow setFollowDate(Date followDate) {
        this.followDate = followDate;
        return this;
    }

    public Profile getChef() {
        return chef;
    }

    public Follow setChef(Profile chef) {
        this.chef = chef;
        return this;
    }

    public Profile getReader() {
        return reader;
    }

    public Follow setReader(Profile reader) {
        this.reader = reader;
        return this;
    }
}
