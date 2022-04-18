package com.example.demo.domain.service;

import com.example.demo.domain.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface UserService  {

    Page<User> getAllUsers(Pageable pageable);

    User getUserById(Long userId);

    User createdUser(User user);

    User getUserByEmail(String email);

    User updateUser(Long userId, User userRequest);

    ResponseEntity<?> deleteUser(Long userId);
}
