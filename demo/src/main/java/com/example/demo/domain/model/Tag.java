package com.example.demo.domain.model;

import com.example.demo.domain.model.AuditModel;
import com.example.demo.domain.model.Recipe;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Table(name="tags")
public class Tag extends AuditModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String name;

    @ManyToMany(fetch = FetchType.LAZY,
            cascade={CascadeType.PERSIST,CascadeType.MERGE})
    @JoinTable(name="tag_recipe",
            joinColumns = {@JoinColumn(name="tag_id")},
            inverseJoinColumns = {@JoinColumn(name="recipe_id")})
    private List<Recipe> recipes;

    public Tag() {
    }

    public Long getId() {
        return id;
    }

    public Tag setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public Tag setName(String name) {
        this.name = name;
        return this;
    }

    public List<Recipe> getRecipes() {
        return recipes;
    }
}
