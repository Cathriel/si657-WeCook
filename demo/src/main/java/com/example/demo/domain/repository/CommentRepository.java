package com.example.demo.domain.repository;

import com.example.demo.domain.model.Comment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    Page<Comment> findByRecipeId(Long recipeId, Pageable pageable);
}
