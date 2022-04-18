package com.example.demo.service;

import com.example.demo.domain.model.Comment;
import com.example.demo.domain.model.Profile;
import com.example.demo.domain.model.Recipe;
import com.example.demo.domain.repository.CommentRepository;
import com.example.demo.domain.repository.ProfileRepository;
import com.example.demo.domain.repository.RecipeRepository;
import com.example.demo.domain.service.CommentService;
import com.example.demo.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private RecipeRepository recipeRepository;

    @Autowired
    private ProfileRepository profileRepository;

    @Override
    public Page<Comment> getAllCommentsByRecipeId(Long recipeId, Pageable pageable) {
        return commentRepository.findByRecipeId(recipeId, pageable);
    }

    @Override
    public Comment getCommentById(Long commentId) {
        return commentRepository.findById(commentId)
                .orElseThrow(()->new ResourceNotFoundException("Comment", "Id", commentId));
    }

    @Override
    public Comment createComment(Long recipeId, Long profileId, Comment comment) {
        Recipe recipe = recipeRepository.findById(recipeId)
                .orElseThrow(()->new ResourceNotFoundException("Recipe","Id",recipeId));
        Profile profile = profileRepository.findById(profileId)
                .orElseThrow(()->new ResourceNotFoundException("Profile","Id",profileId));
        return commentRepository.save(comment.setRecipe(recipe).setProfile(profile));
    }

    @Override
    public Comment updateComment(Long commentId, Comment commentRequest) {
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(()->new ResourceNotFoundException("Comment", "Id", commentId));
        comment.setText(commentRequest.getText());
        return commentRepository.save(comment);
    }

    @Override
    public ResponseEntity<?> deleteComment(Long commentId) {
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(()->new ResourceNotFoundException("Comment", "Id", commentId));
        commentRepository.delete(comment);
        return ResponseEntity.ok().build();
    }
}
