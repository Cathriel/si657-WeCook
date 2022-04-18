package com.example.demo.service;

import com.example.demo.domain.model.User;
import com.example.demo.domain.repository.UserRepository;
import com.example.demo.domain.service.UserService;
import com.example.demo.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public Page<User> getAllUsers(Pageable pageable) {
        return userRepository.findAll(pageable);
    }

    @Override
    public User getUserById(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(()-> new ResourceNotFoundException("User","Id",userId));
    }

    @Override
    public User createdUser(User user) {
        Pageable pageable = PageRequest.of(0,10000);
        Page<User> userPage = this.getAllUsers(pageable);

        if(userPage!=null)
            userPage.forEach(user1 -> {
                if(user1.getEmail().equals(user.getEmail()))
                    throw new ResourceNotFoundException("There is already another user with the same email address");
            });

        return userRepository.save(user);
    }

    @Override
    public User getUserByEmail(String email) {
        return userRepository.findUserByEmail(email)
                .orElseThrow(()->new ResourceNotFoundException("User","Email",email));
    }

    @Override
    public User updateUser(Long userId, User userRequest) {
        User user1 = userRepository.findById(userId)
                .orElseThrow(()->new ResourceNotFoundException("User","Id",userId));
        user1.setPassword(userRequest.getPassword());

        return userRepository.save(user1);
    }

    @Override
    public ResponseEntity<?> deleteUser(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(()->new ResourceNotFoundException("User","Id",userId));
        userRepository.delete(user);
        return ResponseEntity.ok().build();
    }

}
