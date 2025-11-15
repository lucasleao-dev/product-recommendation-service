package com.product.userservice.performance;

import org.junit.jupiter.api.Test;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

class UserServicePerformanceTest {

    private static final String BASE_URL = "http://localhost:8080/users";
    private static final int NUM_REQUESTS = 50; // número de requisições simultâneas
    private static final int THREAD_POOL_SIZE = 10;

    @Test
    void testConcurrentRequests() throws InterruptedException, ExecutionException {
        ExecutorService executor = Executors.newFixedThreadPool(THREAD_POOL_SIZE);
        List<Callable<Long>> tasks = new ArrayList<>();

        // Cria tarefas para medir tempo de cada requisição GET
        for (int i = 0; i < NUM_REQUESTS; i++) {
            tasks.add(() -> {
                long start = System.currentTimeMillis();
                HttpURLConnection connection = null;
                try {
                    URL url = new URL(BASE_URL);
                    connection = (HttpURLConnection) url.openConnection();
                    connection.setRequestMethod("GET");
                    int responseCode = connection.getResponseCode();
                    if (responseCode != 200) {
                        System.out.println("Erro na requisição: " + responseCode);
                    }
                } finally {
                    if (connection != null) {
                        connection.disconnect();
                    }
                }
                long end = System.currentTimeMillis();
                return end - start; // tempo em ms
            });
        }

        // Executa todas as tarefas simultaneamente
        List<Future<Long>> results = executor.invokeAll(tasks);

        // Calcula tempo médio
        long totalTime = 0;
        for (Future<Long> future : results) {
            totalTime += future.get();
        }
        double averageTime = totalTime / (double) NUM_REQUESTS;
        System.out.println("Tempo médio por requisição: " + averageTime + " ms");

        executor.shutdown();
    }
}
