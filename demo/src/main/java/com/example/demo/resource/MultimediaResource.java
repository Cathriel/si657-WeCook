package com.example.demo.resource;

import com.example.demo.domain.model.AuditModel;

public class MultimediaResource extends AuditModel {

    private Long id;

    private String url;

    public Long getId() {
        return id;
    }

    public MultimediaResource setId(Long id) {
        this.id = id;
        return this;
    }

    public String getUrl() {
        return url;
    }

    public MultimediaResource setUrl(String url) {
        this.url = url;
        return this;
    }

}
