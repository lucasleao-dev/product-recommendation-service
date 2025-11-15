package com.product.userservice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.product.userservice.model.User;
import com.product.userservice.repository.UserRepository;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class UserControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserRepository repository;

    @Autowired
    private ObjectMapper objectMapper;

    private User user1;
    private User user2;

    @BeforeEach
    void setup() {
        repository.deleteAll();

        user1 = new User("john", "pass123", "USER");
        user2 = new User("jane", "pass456", "ADMIN");

        repository.save(user1);
        repository.save(user2);
    }

    @Test
    @Order(1)
    void testGetAllUsers() throws Exception {
        mockMvc.perform(get("/users"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()", is(2)))
                .andExpect(jsonPath("$[0].username", is("john")))
                .andExpect(jsonPath("$[1].username", is("jane")));
    }

    @Test
    @Order(2)
    void testGetUserById() throws Exception {
        mockMvc.perform(get("/users/{id}", user1.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.username", is("john")));
    }

    @Test
    @Order(3)
    void testCreateUser() throws Exception {
        User newUser = new User("alice", "pass789", "USER");

        mockMvc.perform(post("/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(newUser)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.username", is("alice")));

        Assertions.assertEquals(3, repository.findAll().size());
    }

    @Test
    @Order(4)
    void testDeleteUser() throws Exception {
        mockMvc.perform(delete("/users/{id}", user2.getId()))
                .andExpect(status().isOk());

        Assertions.assertEquals(1, repository.findAll().size());
    }
}
