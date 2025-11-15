package com.payment.service.controller;

import com.payment.service.PaymentServiceApplication;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = PaymentServiceApplication.class)
@AutoConfigureMockMvc
public class UserControllerPerformanceTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void testGetUserPerformance() throws Exception {
        int requests = 1000; // número de requisições para teste de carga
        long start = System.currentTimeMillis();

        for (int i = 0; i < requests; i++) {
            mockMvc.perform(get("/users/1"))
                    .andExpect(status().isOk());
        }

        long end = System.currentTimeMillis();
        System.out.println("Tempo total para " + requests + " requisições: " + (end - start) + " ms");
        System.out.println("Média por requisição: " + ((end - start) / (double)requests) + " ms");
    }
}
