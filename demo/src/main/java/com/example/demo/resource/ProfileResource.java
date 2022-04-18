package com.example.demo.resource;

import com.example.demo.domain.model.AuditModel;
import com.example.demo.domain.model.User;

import javax.validation.constraints.NotNull;
import java.util.Date;

public class ProfileResource extends AuditModel {

    private Long id;

    private String name;

    private String profilePictureUrl;

    private Long DNI;

    private String gender;

    private Date birthdate;

    private User user;

    private double subscribersPrice;

    private Boolean subsOn;

    private Boolean tipsOn;

    public Long getId() {
        return id;
    }

    public ProfileResource setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public ProfileResource setName(String name) {
        this.name = name;
        return this;
    }

    public String getProfilePictureUrl() {
        return profilePictureUrl;
    }

    public ProfileResource setProfilePictureUrl(String profilePictureUrl) {
        this.profilePictureUrl = profilePictureUrl;
        return this;
    }

    public Long getDNI() {
        return DNI;
    }

    public ProfileResource setDNI(Long DNI) {
        this.DNI = DNI;
        return this;
    }

    public String getGender() {
        return gender;
    }

    public ProfileResource setGender(String gender) {
        this.gender = gender;
        return this;
    }

    public Date getBirthdate() {
        return birthdate;
    }

    public ProfileResource setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
        return this;
    }

    public User getUser() {
        return user;
    }

    public ProfileResource setUser(User user) {
        this.user = user;
        return this;
    }

    public double getSubscribersPrice() {
        return subscribersPrice;
    }

    public ProfileResource setSubscribersPrice(double subscribersPrice) {
        this.subscribersPrice = subscribersPrice;
        return this;
    }

    public Boolean getSubsOn() {
        return subsOn;
    }

    public ProfileResource setSubsOn(Boolean subsOn) {
        this.subsOn = subsOn;
        return this;
    }

    public Boolean getTipsOn() {
        return tipsOn;
    }

    public ProfileResource setTipsOn(Boolean tipsOn) {
        this.tipsOn = tipsOn;
        return this;
    }

}
