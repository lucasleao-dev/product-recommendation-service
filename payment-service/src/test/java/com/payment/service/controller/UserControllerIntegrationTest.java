package com.payment.service.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.payment.service.model.User;
import com.payment.service.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Testes de integração completos para UserController.
 * Testa: CRUD, validação de dados, e endpoints GET/POST.
 * Usa H2 como banco em memória.
 */
@SpringBootTest
@AutoConfigureMockMvc
class UserControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    void setup() {
        userRepository.deleteAll(); // Limpa dados antes de cada teste
    }

    // ---------------------- TESTE POST /users ----------------------
    @Test
    void shouldCreateUser() throws Exception {
        User user = new User();
        user.setName("Lucas");
        user.setEmail("lucas@test.com");

        mockMvc.perform(post("/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(user)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.name").value("Lucas"))
                .andExpect(jsonPath("$.email").value("lucas@test.com"));
    }

    @Test
    void shouldFailWhenNameOrEmailMissing() throws Exception {
        // Nome faltando
        User user = new User();
        user.setEmail("lucas@test.com");

        mockMvc.perform(post("/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(user)))
                .andExpect(status().isBadRequest());

        // Email faltando
        user = new User();
        user.setName("Lucas");

        mockMvc.perform(post("/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(user)))
                .andExpect(status().isBadRequest());
    }

    // ---------------------- TESTE GET /users ----------------------
    @Test
    void shouldGetAllUsers() throws Exception {
        userRepository.save(new User(null, "Lucas", "lucas@test.com"));
        userRepository.save(new User(null, "Ana", "ana@test.com"));

        mockMvc.perform(get("/users"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()", is(2)))
                .andExpect(jsonPath("$[0].name", is("Lucas")))
                .andExpect(jsonPath("$[1].name", is("Ana")));
    }

    // ---------------------- TESTE GET /users/{id} ----------------------
    @Test
    void shouldGetUserById() throws Exception {
        User saved = userRepository.save(new User(null, "Lucas", "lucas@test.com"));

        mockMvc.perform(get("/users/{id}", saved.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(saved.getId()))
                .andExpect(jsonPath("$.name").value("Lucas"))
                .andExpect(jsonPath("$.email").value("lucas@test.com"));
    }

    @Test
    void shouldReturnNotFoundForInvalidId() throws Exception {
        mockMvc.perform(get("/users/{id}", 999L))
                .andExpect(status().isNotFound());
    }

    // ---------------------- EXEMPLO JWT (simulação) ----------------------
    // Caso seu controller use @PreAuthorize ou JWT, você poderia simular:
    // mockMvc.perform(get("/users")
    //     .header("Authorization", "Bearer fake-jwt-token"))
    //     .andExpect(status().isOk());
}
