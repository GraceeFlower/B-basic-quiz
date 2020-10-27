package com.example.demo.service;


import com.example.demo.controller.dto.EducationRequestDTO;
import com.example.demo.model.Education;
import com.example.demo.model.User;
import com.example.demo.repository.EducationRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.exception.InvalidUserException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class EducationServiceTest {

    private EducationService educationService;

    @MockBean
    private UserService userService;
    @Mock
    private EducationRepository educationRepository;
    @Mock
    private UserRepository userRepository;

    private User firstUser;

    private Education education;

    @BeforeEach
    void setUp() {
        userService = new UserService(userRepository);
        educationService = new EducationService(educationRepository, userService);

        firstUser = User.builder()
                .id(1L)
                .name("grace")
                .age(18L)
                .avatar("http://grace.com")
                .description("description")
                .build();

        education = Education.builder()
                .educationId(1L)
                .description("description")
                .title("education")
                .year(2001L)
                .user(firstUser)
                .build();

    }
    @Test
    public void should_return_user_educations_when_user_exists() {
        when(educationRepository.findAllByUserId(1L)).thenReturn(Collections.singletonList(education));
        List<Education> educationList = educationRepository.findAllByUserId(1L);

        assertThat(educationList).isEqualTo(Collections.singletonList(education));
    }

    @Test
    void should_throw_exception_when_user_not_exists() {
        when(userRepository.findById(2L)).thenReturn(Optional.empty());

        InvalidUserException thrownException = assertThrows(InvalidUserException.class, () ->
                educationService.findAllByUserId(2L));

        assertThat(thrownException.getMessage()).containsSequence("User Not Found");
    }

    @Test
    void should_create_education_when_user_exists() {
        when(userRepository.findById(1L)).thenReturn(Optional.of(firstUser));

        EducationRequestDTO educationRequestDTO = EducationRequestDTO.builder()
                .year(2001L)
                .title("education")
                .description("description")
                .build();

        when(educationRepository.save(education)).thenReturn(education);
        Education newEducation = educationService.createEducation(1L,educationRequestDTO);

        assertThat(newEducation).isEqualTo(education);
    }
}
