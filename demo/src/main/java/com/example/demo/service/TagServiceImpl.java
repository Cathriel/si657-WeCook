package com.example.demo.service;

import com.example.demo.domain.model.Recipe;
import com.example.demo.domain.model.Tag;
import com.example.demo.domain.repository.TagRepository;
import com.example.demo.domain.service.TagService;
import com.example.demo.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TagServiceImpl implements TagService {

    @Autowired
    private TagRepository tagRepository;

    @Override
    public Page<Tag> getAllTags(Pageable pageable) {
        return tagRepository.findAll(pageable);
    }

    @Override
    public Tag getTagById(Long tagId) {
        return tagRepository.findById(tagId)
                .orElseThrow(()->new ResourceNotFoundException("Tag","Id",tagId));
    }

    @Override
    public Tag createTag(Tag tag) {
        return tagRepository.save(tag);
    }

    @Override
    public Tag updateTag(Long tagId, Tag tagRequest) {
        Tag tag=tagRepository.findById(tagId)
                .orElseThrow(()->new ResourceNotFoundException("Tag","Id", tagId));
        tag.setName(tagRequest.getName());
        return tagRepository.save(tag);
    }

    @Override
    public ResponseEntity<?> deleteTag(Long tagId) {
        Tag tag=tagRepository.findById(tagId)
                .orElseThrow(()->new ResourceNotFoundException("Tag","Id",tagId));

        tagRepository.delete(tag);

        return ResponseEntity.ok().build();
    }

    @Override
    public Page<Recipe> getAllRecipesByTagId(Long tagId, Pageable pageable) {
        return tagRepository.findById(tagId).map(
                tag->{ List<Recipe> recipes=tag.getRecipes();
                int recipesCount=recipes.size();
                return new PageImpl<>(recipes,pageable,recipesCount);
                }).orElseThrow(()->new ResourceNotFoundException("Tag","Id",tagId));
    }


    @Override
    public Tag getTagByName(String name) {
        return tagRepository.findByName(name)
                .orElseThrow(()->new ResourceNotFoundException("Tag","Name",name));
    }
}
