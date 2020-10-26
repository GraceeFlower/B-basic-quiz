package com.example.demo.controller;

import com.example.demo.controller.dto.UserRequestDTO;
import com.example.demo.model.User;
import com.example.demo.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@Validated
@CrossOrigin("http://localhost:1234")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users/{userId}")
    public User findUserById (@PathVariable Long userId) {
        return userService.findUserById(userId);
    }

    @PostMapping("/users")
    @ResponseStatus(HttpStatus.CREATED)
    public User createUser(@RequestBody @Valid UserRequestDTO userRequestDTO) {
        return userService.createUser(userRequestDTO);
    }
}
