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
        //TODO GTB-综合: - UserService.java:36 userId 是刚刚在外面自行生成，这里判断是否存在的意义是什么？防止并发时不同的请求生成了相同的 id 吗？还是什么原因？否则，这里用 id 进行判重，不是很有意义。
        if (userRepository.findUserById(user.getId()) != null) {
            throw new InvalidUserException("用户已存在");
        }
        userRepository.save(user);
    }
}
