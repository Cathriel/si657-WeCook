package com.example.demo.resource;

import javax.validation.constraints.NotNull;
import java.util.Date;

public class SaveFollowResource {

    @NotNull
    private Date followDate;

    public Date getFollowDate() {
        return followDate;
    }

    public SaveFollowResource setFollowDate(Date followDate) {
        this.followDate = followDate;
        return this;
    }

}
