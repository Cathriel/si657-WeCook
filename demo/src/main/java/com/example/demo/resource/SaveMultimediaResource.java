package com.example.demo.resource;

import javax.validation.constraints.NotNull;

public class SaveMultimediaResource {

    @NotNull
    private String url;

    public String getUrl() {
        return url;
    }

    public SaveMultimediaResource setUrl(String url) {

        this.url = url;
        return this;
    }

}
