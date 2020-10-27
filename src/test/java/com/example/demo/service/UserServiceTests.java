package com.example.demo.service;

import com.example.demo.controller.dto.UserRequestDTO;
import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.exception.InvalidUserException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserServiceTests {

    private UserService userService;

    @Mock
    private UserRepository userRepository;

    private User user;

    @BeforeEach
    void setUp() {
        userService = new UserService(userRepository);
        user = User.builder()
                .id(1L)
                .name("Grace")
                .age(18L)
                .avatar("http://grace.com")
                .description("Lovely")
                .build();
    }

    @Test
    public void should_create_a_new_user() {
        when(userRepository.save(user)).thenReturn(user);

        UserRequestDTO userRequestDTO = UserRequestDTO.builder()
                .age(user.getAge())
                .name(user.getName())
                .avatar(user.getAvatar())
                .description(user.getDescription())
                .build();
        User newUser = userService.createUser(userRequestDTO);

        assertThat(newUser).isEqualTo(user);
    }

    @Test
    public void should_return_user_when_userId_exists() {
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));

        User foundUser = userService.findUserById(1L);

        assertThat(foundUser).isEqualTo(user);
    }

    @Test
    void should_throw_exception_when_user_not_exist() {
        when(userRepository.findById(2L)).thenReturn(Optional.empty());

        InvalidUserException thrownException = assertThrows(InvalidUserException.class, () -> {
            userService.findUserById(2L);
        });

        assertThat(thrownException.getMessage()).containsSequence("User Not Found!");
    }
}
