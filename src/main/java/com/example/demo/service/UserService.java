package com.example.demo.service;

import com.example.demo.exception.InvalidUserException;
import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User findUserById(int userId) {
        User user = userRepository.findUserById(userId);
        if (user == null) {
            throw new InvalidUserException("用户不存在");
        }
        return user;
    }

    public int getNewUserId() {
        return findAllUsers().size() + 1;
    }

    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    public void createUser(User user) {
        if (userRepository.findUserById(user.getId()) != null) {
            throw new InvalidUserException("用户已存在");
        }
        userRepository.save(user);
    }
}
