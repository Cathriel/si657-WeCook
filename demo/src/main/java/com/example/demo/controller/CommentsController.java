package com.example.demo.controller;

import com.example.demo.domain.model.Comment;
import com.example.demo.domain.service.CommentService;
import com.example.demo.resource.CommentResource;
import com.example.demo.resource.SaveCommentResource;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@CrossOrigin
@RequestMapping("/api")
public class CommentsController {
    @Autowired
    private ModelMapper mapper;

    @Autowired
    private CommentService commentService;


    @GetMapping("/comments/{commentId}")
    public CommentResource getCommentById(@PathVariable Long commentId){
        return convertToResource(commentService.getCommentById(commentId));
    }

    @PutMapping("/comments/{commentId}")
    public CommentResource updateComment(@PathVariable Long commentId, @Valid @RequestBody SaveCommentResource resource){
        return convertToResource(commentService.updateComment(commentId,convertToEntity(resource)));
    }

    @DeleteMapping("/comments/{commentId}")
    public ResponseEntity<?> deleteComment(@PathVariable Long commentId){
        return commentService.deleteComment(commentId);
    }


    private Comment convertToEntity(SaveCommentResource resource) {
        return mapper.map(resource, Comment.class);
    }

    private CommentResource convertToResource(Comment entity) {
        return mapper.map(entity, CommentResource.class);
    }

}

