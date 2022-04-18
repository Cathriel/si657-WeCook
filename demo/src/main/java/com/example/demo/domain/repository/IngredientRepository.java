package com.example.demo.domain.repository;

import com.example.demo.domain.model.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IngredientRepository extends JpaRepository<Ingredient, Long> {
    public Optional<Ingredient> findByName(String name);
}
