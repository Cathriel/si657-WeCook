package com.example.demo.resource;

import com.example.demo.domain.model.User;

import java.util.Date;

public class SaveProfileResource {

    private String name;

    private String profilePictureUrl;

    private Long DNI;

    private String gender;

    private Date birthdate;

    private double subscribersPrice;

    private Boolean subsOn;

    private Boolean tipsOn;

    public String getName() {
        return name;
    }

    public SaveProfileResource setName(String name) {
        this.name = name;
        return this;
    }

    public String getProfilePictureUrl() {
        return profilePictureUrl;
    }

    public SaveProfileResource setProfilePictureUrl(String profilePictureUrl) {
        this.profilePictureUrl = profilePictureUrl;
        return this;
    }

    public Long getDNI() {
        return DNI;
    }

    public SaveProfileResource setDNI(Long DNI) {
        this.DNI = DNI;
        return this;
    }

    public String getGender() {
        return gender;
    }

    public SaveProfileResource setGender(String gender) {
        this.gender = gender;
        return this;
    }

    public Date getBirthdate() {
        return birthdate;
    }

    public SaveProfileResource setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
        return this;
    }

    public double getSubscribersPrice() {
        return subscribersPrice;
    }

    public SaveProfileResource setSubscribersPrice(double subscribersPrice) {
        this.subscribersPrice = subscribersPrice;
        return this;
    }

    public Boolean getSubsOn() {
        return subsOn;
    }

    public SaveProfileResource setSubsOn(Boolean subsOn) {
        this.subsOn = subsOn;
        return this;
    }

    public Boolean getTipsOn() {
        return tipsOn;
    }

    public SaveProfileResource setTipsOn(Boolean tipsOn) {
        this.tipsOn = tipsOn;
        return this;
    }
}
