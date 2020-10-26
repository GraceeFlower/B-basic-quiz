package com.example.demo.service;

import com.example.demo.controller.dto.UserRequestDTO;
import com.example.demo.exception.InvalidUserException;
import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.concurrent.atomic.AtomicLong;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final AtomicLong userIdSeq = new AtomicLong();

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User findUserById(Long userId) {
        return userRepository
                .findById(userId)
                .orElseThrow(() -> new InvalidUserException("User Not Found!"));
    }

    public User createUser(UserRequestDTO userRequestDTO) {
        User user = new User(
                userIdSeq.incrementAndGet(),
                userRequestDTO.getName(),
                userRequestDTO.getAge(),
                userRequestDTO.getAvatar(),
                userRequestDTO.getDescription()
        );

        userRepository.save(user);
        return user;
    }
}
