package com.product.userservice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.product.userservice.model.User;
import com.product.userservice.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class UserApiSecurityTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserRepository repository;

    @Autowired
    private ObjectMapper objectMapper;

    private User user;

    @BeforeEach
    void setup() {
        repository.deleteAll();
        user = new User("secureuser", "pass123", "USER");
        repository.save(user);
    }

    @Test
    void testUnauthorizedAccess() throws Exception {
        // Sem usuário autenticado → deve retornar 401
        mockMvc.perform(get("/users"))
                .andExpect(status().isUnauthorized());
    }

    @Test
    @WithMockUser(username = "admin", roles = {"ADMIN"})
    void testAuthorizedAccess() throws Exception {
        // Com usuário autenticado → deve retornar 200
        mockMvc.perform(get("/users"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].username").value("secureuser"));
    }

    @Test
    @WithMockUser(username = "user", roles = {"USER"})
    void testRoleBasedAccessDenied() throws Exception {
        // Exemplo: apenas ADMIN pode deletar
        mockMvc.perform(delete("/users/{id}", user.getId()))
                .andExpect(status().isForbidden());
    }

    @Test
    @WithMockUser(username = "admin", roles = {"ADMIN"})
    void testRoleBasedAccessAllowed() throws Exception {
        mockMvc.perform(delete("/users/{id}", user.getId()))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(username = "admin", roles = {"ADMIN"})
    void testCreateUserAuthorized() throws Exception {
        User newUser = new User("newuser", "pass456", "USER");

        mockMvc.perform(post("/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(newUser)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.username").value("newuser"));
    }
}
