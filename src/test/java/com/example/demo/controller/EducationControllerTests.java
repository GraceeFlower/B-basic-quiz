package com.example.demo.controller;

import com.example.demo.controller.dto.EducationRequestDTO;
import com.example.demo.controller.dto.EducationResponseDTO;
import com.example.demo.controller.dto.EducationsResponseDTO;
import com.example.demo.exception.ErrorResult;
import com.example.demo.exception.InvalidUserException;
import com.example.demo.model.Education;
import com.example.demo.model.User;
import com.example.demo.service.EducationService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class EducationControllerTests {

    @MockBean
    private EducationService educationService;

    @Autowired
    private TestRestTemplate restTemplate;

    private EducationRequestDTO educationRequestDTO;

    private Education education;

    @BeforeEach
    public void beforeEach() {
        User firstUser = User.builder()
                .id(1L)
                .name("Grace")
                .age(18L)
                .avatar("http://grace.com")
                .description("Lovely")
                .build();

        educationRequestDTO = EducationRequestDTO.builder()
                .year(2001L)
                .title("education")
                .description("description")
                .build();

        education = Education.builder()
                .educationId(1L)
                .year(2001L)
                .title("education")
                .description("description")
                .user(firstUser)
                .build();
    }

    @AfterEach
    public void afterEach() {
        Mockito.reset(educationService);
    }

    @Test
    void should_return_educations_when_user_exists() {
        when(educationService.findAllByUserId(1L)).thenReturn(Collections.singletonList(education));

        ResponseEntity<EducationRequestDTO[]> responseEntity = restTemplate.getForEntity("/users/{id}/educations"
                , EducationRequestDTO[].class, 1L);

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getHeaders().getContentType()).isEqualTo(MediaType.APPLICATION_JSON);
        assertThat(Objects.requireNonNull(responseEntity.getBody())[0].getYear()).isEqualTo(2001L);

        verify(educationService, times(1)).findAllByUserId(1L);
    }

    @Test
    void should_return_404_when_user_not_exist() {
        when(educationService.findAllByUserId(2L)).thenThrow(new InvalidUserException("User Not Found!"));

        ResponseEntity<ErrorResult> responseEntity = restTemplate.getForEntity("/users/{id}/educations"
                , ErrorResult.class, 2L);

        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(404);
        assertThat(responseEntity.getHeaders().getContentType()).isEqualTo(MediaType.APPLICATION_JSON);
        assertThat(responseEntity.getBody()).isInstanceOf(ErrorResult.class);

        verify(educationService, times(1)).findAllByUserId(2L);
    }

    @Test
    void should_create_new_education_for_existing_user() {
        when(educationService.createEducation(1L, educationRequestDTO)).thenReturn(education);

        ResponseEntity<EducationResponseDTO> responseEntity = restTemplate.postForEntity("/users/{id}/educations",
                educationRequestDTO, EducationResponseDTO.class, 1L);

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(responseEntity.getHeaders().getContentType()).isEqualTo(MediaType.APPLICATION_JSON);
        assertThat(Objects.requireNonNull(responseEntity.getBody()).getEducationId()).isEqualTo(1L);

        verify(educationService, times(1)).createEducation(1L, educationRequestDTO);
    }
}