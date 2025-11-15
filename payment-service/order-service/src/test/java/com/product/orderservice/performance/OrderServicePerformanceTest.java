package com.product.orderservice.performance;

import com.product.orderservice.model.Order;
import com.product.orderservice.repository.OrderRepository;
import com.product.orderservice.service.OrderService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.stream.IntStream;

import static org.mockito.Mockito.*;

class OrderServicePerformanceTest {

    private OrderRepository orderRepository;
    private OrderService orderService;

    @BeforeEach
    void setUp() {
        orderRepository = mock(OrderRepository.class);
        orderService = new OrderService(orderRepository);
    }

    @Test
    void measureFindAllPerformance() {
        // Simula 1000 pedidos
        IntStream.range(0, 1000).forEach(i -> {
            Order order = new Order();
            order.setId((long) i);
            order.setStatus("NEW");
            order.setTotal(100.0 + i);
            order.setUserId(1L);
            when(orderRepository.findAll()).thenReturn(java.util.Collections.nCopies(1000, order));
        });

        long startTime = System.nanoTime();

        // Executa o método a ser testado
        orderService.findAll();

        long endTime = System.nanoTime();

        long duration = (endTime - startTime) / 1_000_000; // converte para milissegundos
        System.out.println("Tempo de execução do findAll(): " + duration + " ms");
    }

    @Test
    void measureCreateOrderPerformance() {
        Order order = new Order();
        order.setStatus("NEW");
        order.setTotal(200.0);
        order.setUserId(1L);

        when(orderRepository.save(order)).thenReturn(order);

        long startTime = System.nanoTime();

        orderService.save(order);

        long endTime = System.nanoTime();

        long duration = (endTime - startTime) / 1_000_000;
        System.out.println("Tempo de execução do save(): " + duration + " ms");
    }
}
