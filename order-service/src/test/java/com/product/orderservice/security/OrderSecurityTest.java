package com.product.orderservice.security;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class OrderSecurityTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void testUnauthorizedAccess() throws Exception {
        mockMvc.perform(get("/orders"))
                .andExpect(status().isUnauthorized()); // se houver segurança JWT configurada
    }
}
