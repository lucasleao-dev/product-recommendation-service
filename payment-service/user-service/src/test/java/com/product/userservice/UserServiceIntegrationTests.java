package com.product.userservice;

import com.product.userservice.model.User;
import com.product.userservice.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class UserServiceIntegrationTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserRepository userRepository;

    @BeforeEach
    void setup() {
        userRepository.deleteAll(); // limpa DB antes de cada teste
    }

    @Test
    void testCreateUser() throws Exception {
        String userJson = """
            {
                "username": "lucas",
                "password": "123456",
                "role": "ROLE_USER"
            }
        """;

        mockMvc.perform(post("/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content(userJson))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.username").value("lucas"));
    }

    @Test
    void testGetUsers() throws Exception {
        userRepository.save(new User("lucas", "123456", "ROLE_USER"));

        mockMvc.perform(get("/users"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].username").value("lucas"));
    }

    @Test
    void testDeleteUser() throws Exception {
        User user = userRepository.save(new User("lucas", "123456", "ROLE_USER"));

        mockMvc.perform(delete("/users/" + user.getId()))
                .andExpect(status().isNoContent());
    }
}
