package com.example.demo.domain.service;

import com.example.demo.domain.model.Recipe;
import com.example.demo.domain.model.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface TagService {

    Page<Tag> getAllTags(Pageable pageable);

    Tag getTagById(Long tagId);

    Tag createTag(Tag tag);

    Tag updateTag(Long tagId, Tag tagRequest);

    ResponseEntity<?> deleteTag(Long tagId);

    Page<Recipe> getAllRecipesByTagId(Long tagId, Pageable pageable);

    Tag getTagByName(String name);
}
