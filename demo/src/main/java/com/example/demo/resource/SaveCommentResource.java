package com.example.demo.resource;

import javax.persistence.Lob;
import javax.validation.constraints.NotNull;
import java.util.Date;

public class SaveCommentResource {

    @NotNull
    @Lob
    private String text;


    public String getText() {
        return text;
    }

    public SaveCommentResource setText(String text) {
        this.text = text;
        return this;
    }

}
