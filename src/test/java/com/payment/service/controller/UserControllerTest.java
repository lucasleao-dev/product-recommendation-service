package com.payment.service.controller;

import com.payment.service.model.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Testes de integração usando MockMvc.
 * Aqui subimos o contexto Spring, mas sem subir servidor real.
 * Testamos: Controller -> Service -> Repository -> Banco (H2 em memória).
 */
@SpringBootTest
@AutoConfigureMockMvc
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;   // Permite executar requisições HTTP fake

    @Autowired
    private ObjectMapper mapper; // Converte objetos Java para JSON automaticamente

    /**
     * Teste do endpoint POST /users
     * Verifica se cria um usuário com sucesso.
     */
    @Test
    void shouldCreateUser() throws Exception {
        User user = new User();
        user.setName("Lucas");

        // Executa POST com body JSON
        mockMvc.perform(post("/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(user)))
                // Verifica se a resposta foi 200 OK
                .andExpect(status().isOk());
    }

    /**
     * Teste do endpoint GET /users
     * Verifica se retorna lista de usuários sem erro.
     */
    @Test
    void shouldGetAllUsers() throws Exception {
        mockMvc.perform(get("/users"))
                .andExpect(status().isOk());
    }
}
