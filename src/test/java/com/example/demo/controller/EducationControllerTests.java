package com.example.demo.controller;

import com.example.demo.controller.dto.EducationRequestDTO;
import com.example.demo.model.Education;
import com.example.demo.model.User;
import com.example.demo.service.EducationService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class EducationControllerTests {

    @MockBean
    private EducationService educationService;

    @Autowired
    private TestRestTemplate restTemplate;

    private final List<Education> educations = new ArrayList<>();

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

        educations.add(Education.builder()
                .year(2001L)
                .title("education")
                .description("description")
                .user(firstUser)
                .build());

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

}