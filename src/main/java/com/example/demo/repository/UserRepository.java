package com.example.demo.repository;

import com.example.demo.dataprovider.DataProvider;
import com.example.demo.model.User;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserRepository {

    private final List<User> userList = DataProvider.userList;

    public User findUserById(int userId) {
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
