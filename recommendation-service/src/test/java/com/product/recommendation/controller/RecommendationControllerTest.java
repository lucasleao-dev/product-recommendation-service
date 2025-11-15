package com.product.recommendation.controller;

import com.product.recommendation.model.Recommendation;
import com.product.recommendation.service.RecommendationService;
import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class RecommendationControllerTest {

    @Test
    void testGetAll() {
        RecommendationService service = mock(RecommendationService.class);
        RecommendationController controller = new RecommendationController(service);

        Recommendation r1 = new Recommendation("P1", "R1");
        when(service.getAllRecommendations()).thenReturn(List.of(r1));

        ResponseEntity<List<Recommendation>> response = controller.getAll();
        assertEquals(1, response.getBody().size());
        verify(service, times(1)).getAllRecommendations();
    }

    @Test
    void testCreate() {
        RecommendationService service = mock(RecommendationService.class);
        RecommendationController controller = new RecommendationController(service);

        Recommendation r = new Recommendation("P1", "R1");
        when(service.saveRecommendation(r)).thenReturn(r);

        ResponseEntity<Recommendation> response = controller.create(r);
        assertEquals("P1", response.getBody().getProductId());
        assertEquals("R1", response.getBody().getRecommendedProductId());
        verify(service, times(1)).saveRecommendation(r);
    }
}
