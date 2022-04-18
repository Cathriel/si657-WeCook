package com.example.demo.controller;

import com.example.demo.domain.model.Tag;
import com.example.demo.domain.service.TagService;
import com.example.demo.resource.SaveTagResource;
import com.example.demo.resource.TagResource;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;


@RestController
@CrossOrigin
@RequestMapping("/api")
public class TagsController {
    @Autowired
    private ModelMapper mapper;

    @Autowired
    private TagService tagService;

    @GetMapping("/tag")
    public Page<TagResource> getAllTag(Pageable pageable){
        Page<Tag> tagPage = tagService.getAllTags(pageable);
        List<TagResource> resources = tagPage.getContent().stream().map(
                this::convertToResourceTag).collect(Collectors.toList());
        return new PageImpl<>(resources,pageable,resources.size());
    }

    @GetMapping("/tag/{tagId}")
    public TagResource getTagById(@PathVariable Long tagId){
        return convertToResourceTag(tagService.getTagById(tagId));
    }

    @PostMapping("/tag")
    public TagResource createTag(@Valid @RequestBody SaveTagResource resource){
        return convertToResourceTag(tagService.createTag(convertToEntityTag(resource)));
    }

    @PutMapping("/tag/{tagId}")
    public TagResource updateTag(@PathVariable Long tagId,@Valid @RequestBody SaveTagResource resource){
        return convertToResourceTag(tagService.updateTag(tagId,convertToEntityTag(resource)));
    }


    @DeleteMapping("/tag/{tagId}")
    public ResponseEntity<?> deleteTag(@PathVariable Long tagId) {
        return tagService.deleteTag(tagId);
    }

    private Tag convertToEntityTag(SaveTagResource resource) {
        return mapper.map(resource, Tag.class);
    }

    private TagResource convertToResourceTag(Tag entity) {
        return mapper.map(entity, TagResource.class);
    }
}
