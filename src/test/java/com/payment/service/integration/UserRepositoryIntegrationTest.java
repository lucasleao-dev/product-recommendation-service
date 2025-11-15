package com.payment.service.integration;

import com.payment.service.model.User;
import com.payment.service.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class UserRepositoryIntegrationTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    void testCreateAndFindUser() {
        // Cria um usuário
        User user = new User();
        user.setName("Lucas");
        user.setEmail("lucas@test.com");
        User saved = userRepository.save(user);

        // Busca o usuário pelo ID e valida
        User found = userRepository.findById(saved.getId()).orElseThrow();
        assertThat(found.getName()).isEqualTo("Lucas");
        assertThat(found.getEmail()).isEqualTo("lucas@test.com");
    }

    @Test
    void testFindAllUsers() {
        // Cria dois usuários
        User user1 = new User();
        user1.setName("Alice");
        user1.setEmail("alice@test.com");

        User user2 = new User();
        user2.setName("Bob");
        user2.setEmail("bob@test.com");

        userRepository.save(user1);
        userRepository.save(user2);

        // Busca todos usuários e valida
        assertThat(userRepository.findAll()).hasSizeGreaterThanOrEqualTo(2);
    }
}
