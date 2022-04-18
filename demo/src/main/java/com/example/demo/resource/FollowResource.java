package com.example.demo.resource;

import com.example.demo.domain.model.AuditModel;

import java.util.Date;

public class FollowResource extends AuditModel {

    private Long id;

    private Date followDate;

    public Long getId() {
        return id;
    }

    public FollowResource setId(Long id) {
        this.id = id;
        return this;
    }

    public Date getFollowDate() {
        return followDate;
    }

    public FollowResource setFollowDate(Date followDate) {
        this.followDate = followDate;
        return this;
    }

}
