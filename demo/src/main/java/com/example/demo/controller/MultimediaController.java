package com.example.demo.controller;


import com.example.demo.domain.model.Multimedia;
import com.example.demo.domain.service.MultimediaService;
import com.example.demo.resource.MultimediaResource;
import com.example.demo.resource.SaveMultimediaResource;
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
public class MultimediaController {

    @Autowired
    private ModelMapper mapper;

    @Autowired
    private MultimediaService multimediaService;

    @GetMapping("/multimedia")
    public Page<MultimediaResource> getAllMultimedia(Pageable pageable){
        Page<Multimedia> multimediaPage = multimediaService.getAllMultimedia(pageable);
        List<MultimediaResource> resources = multimediaPage.getContent().stream().map(
                this::convertToResourceMultimedia).collect(Collectors.toList());
        return new PageImpl<>(resources,pageable,resources.size());
    }

    @GetMapping("/multimedia/{multimediaId}")
    public MultimediaResource getMultimediaById(@PathVariable Long multimediaId){
        return convertToResourceMultimedia(multimediaService.getMultimediaById(multimediaId));
    }


    @PutMapping("/multimedia/{multimediaId}")
    public MultimediaResource updateMultimedia(@PathVariable Long multimediaId,@Valid @RequestBody SaveMultimediaResource resource){
        return convertToResourceMultimedia(multimediaService.updateMultimedia(multimediaId,convertToEntityMultimedia(resource)));
    }


    @DeleteMapping("/multimedia/{multimediaId}")
    public ResponseEntity<?> deleteMultimedia(@PathVariable Long multimediaId) {
        return multimediaService.deleteMultimedia(multimediaId);
    }

    private Multimedia convertToEntityMultimedia(SaveMultimediaResource resource) {
        return mapper.map(resource, Multimedia.class);
    }

    private MultimediaResource convertToResourceMultimedia(Multimedia entity) {
        return mapper.map(entity, MultimediaResource.class);
    }
}
