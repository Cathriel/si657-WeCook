package com.example.demo.resource;

import com.example.demo.domain.model.AuditModel;

import javax.validation.constraints.NotNull;

public class UserResource extends AuditModel {

    private Long id;

    private String email;

    private String password;

    private String token;

    public Long getId() {
        return id;
    }

    public UserResource setId(Long id) {
        this.id = id;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public UserResource setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public UserResource setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getToken() {
        return token;
    }

    public UserResource setToken(String token) {
        this.token = token;
        return this;
    }

}
