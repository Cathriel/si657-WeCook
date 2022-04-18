package com.example.demo.domain.service;

import com.example.demo.domain.model.Comment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface CommentService {
    Page<Comment> getAllCommentsByRecipeId(Long recipeId, Pageable pageable);
    Comment getCommentById(Long commentId);
    Comment createComment(Long recipeId, Long profileId, Comment comment);
    Comment updateComment(Long commentId, Comment commentRequest);
    ResponseEntity<?> deleteComment(Long commentId);
}
