package com.example.demo.repository;
import com.example.demo.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@DataJpaTest
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TestEntityManager entityManager;

    @Test
    void should_return_user_when_id_exists() {
        String name = "Grace";
        long age = 18L;
        String avatar = "http://example.com";
        String describe = "Lovely girl";

        entityManager.persistAndFlush(User.builder()
                .name(name)
                .age(age)
                .avatar(avatar)
                .description(describe)
                .build());

        Optional<User> found = userRepository.findById(1L);

        assertThat(found.isPresent()).isTrue();
        assertThat(found.get()).isEqualTo(User.builder()
                .id(1L)
                .name(name)
                .age(age)
                .avatar(avatar)
                .description(describe)
                .build());
    }
}
