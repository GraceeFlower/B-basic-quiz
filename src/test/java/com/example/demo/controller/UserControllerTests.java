package com.example.demo.controller;

import com.example.demo.controller.dto.UserRequestDTO;
import com.example.demo.model.User;
import com.example.demo.exception.InvalidUserException;
import com.example.demo.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(UserController.class)
@AutoConfigureMockMvc
public class UserControllerTests {

    @MockBean
    UserService userService;

    @Autowired
    private MockMvc mockMvc;

    private User firstUser;

    private User newUser;

    @BeforeEach
    public void beforeEach() {
        firstUser = User.builder()
                .id(1L)
                .name("Grace")
                .age(18L)
                .avatar("http://grace.com")
                .description("Lovely")
                .build();

        newUser = User.builder()
                .id(2L)
                .name("newUser")
                .age(19L)
                .avatar("http://new.com")
                .description("new user")
                .build();
    }

    @AfterEach
    public void afterEach() {
        Mockito.reset(userService);
    }

    @Test
    public void should_return_user_by_id_with_jsonPath() throws Exception {
        when(userService.findUserById(1L)).thenReturn(firstUser);

        mockMvc.perform(get("/users/{id}", 1L))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.name", is("Grace")));

        verify(userService, times(1)).findUserById(1L);
    }

    @Test
    public void should_return_404_when_userId_not_exist() throws Exception {
        when(userService.findUserById(1L)).thenThrow(new InvalidUserException("User Not Found"));

        mockMvc.perform(get("/users/{id}", 1L))
                .andExpect(status().isNotFound())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.message", is("User Not Found")));

        verify(userService, times(1)).findUserById(1L);
    }

    @Test
    public void should_create_new_user() throws Exception {
        UserRequestDTO newUserRequest = UserRequestDTO.builder()
                .name("newUser")
                .age(19L)
                .avatar("http://new.com")
                .description("new user")
                .build();

        when(userService.createUser(newUserRequest)).thenReturn(newUser);

        ObjectMapper objectMapper = new ObjectMapper();
        String jsonUser = objectMapper.writeValueAsString(newUser);

        mockMvc.perform(post("/users")
                .content(jsonUser).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name", is("newUser")));
    }
}