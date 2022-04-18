package com.example.demo.domain.service;

import com.example.demo.domain.model.Follow;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface FollowService {
    Page<Follow> getAllFollows(Pageable pageable);

    Follow getFollowById(Long followId);

    Follow createFollow(Follow follow, Long chefId, Long readerId);

    ResponseEntity<?> deleteFollow(Long followId);
}
