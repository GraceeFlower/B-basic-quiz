package com.example.demo.repository;

import com.example.demo.dataprovider.DataProvider;
import com.example.demo.model.User;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserRepository {

    private final List<User> userList = DataProvider.userList;

    public User findUserById(int userId) {
        //TODO GTB-工程实践: - UserRepository.java:15 可以直接返回 Optional，调用方也会更好写，可以尝试改进一下
        return userList.stream()
                .filter(user -> user.getId() == userId)
                .findFirst()
                .orElse(null);
    }

    public List<User> findAll() {
        return userList;
    }

    public void save(User user) {
        userList.add(user);
    }
}
