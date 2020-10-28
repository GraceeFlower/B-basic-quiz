package com.example.demo.repository;

import com.example.demo.model.Education;
import com.example.demo.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@DataJpaTest
public class EducationRepositoryTests {

    @Autowired
    private EducationRepository educationRepository;

    @Autowired
    private TestEntityManager entityManager;

    @Test
    void should_return_educations_when_user_exists() {
        User user = User.builder()
                .id(1L)
                .name("grace")
                .age(18L)
                .avatar("http://grace.com")
                .description("description")
                .build();

        Education education = Education.builder()
                .educationId(1L)
                .year(2001L)
                .title("education")
                .description("description")
                .user(user)
                .build();

//        entityManager.persistAndFlush(user);
        entityManager.persistAndFlush(education);

        List<Education> educations = educationRepository.findAllByUserId(1L);

        assertThat(educations.size()).isNotZero();
        assertThat(educations.get(0)).isEqualTo(education);
    }
}
