package com.example.demo.service;

import com.example.demo.domain.model.Follow;
import com.example.demo.domain.model.Profile;
import com.example.demo.domain.repository.FollowRepository;
import com.example.demo.domain.repository.ProfileRepository;
import com.example.demo.domain.service.FollowService;
import com.example.demo.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class FollowServiceImpl implements FollowService {

    @Autowired
    private FollowRepository followRepository;

    @Autowired
    private ProfileRepository profileRepository;

    @Override
    public Page<Follow> getAllFollows(Pageable pageable) {
        return followRepository.findAll(pageable);
    }

    @Override
    public Follow getFollowById(Long followId) {
        return followRepository.findById(followId)
                .orElseThrow(()->new ResourceNotFoundException("Follow","Id",followId));
    }

    @Override
    public Follow createFollow(Follow follow, Long chefId, Long readerId) {
        Profile chef = profileRepository.findById(chefId)
                .orElseThrow(()-> new ResourceNotFoundException("Profile","Id",chefId));
        Profile reader = profileRepository.findById(readerId)
                .orElseThrow(()-> new ResourceNotFoundException("Profile","Id",readerId));

        return followRepository.save(follow.setChef(chef).setReader(reader));
    }

    @Override
    public ResponseEntity<?> deleteFollow(Long followId) {
        Follow follow = followRepository.findById(followId)
                .orElseThrow(()->new ResourceNotFoundException("Follow","Id",followId));
        followRepository.delete(follow);
        return ResponseEntity.ok().build();
    }
}
