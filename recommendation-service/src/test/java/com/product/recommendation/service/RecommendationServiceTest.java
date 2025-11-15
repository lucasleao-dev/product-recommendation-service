package com.product.recommendation.service;

import com.product.recommendation.model.Recommendation;
import com.product.recommendation.repository.RecommendationRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class RecommendationServiceTest {

    @Test
    void testGetAllRecommendations() {
        RecommendationRepository repo = mock(RecommendationRepository.class);
        RecommendationService service = new RecommendationService(repo);

        Recommendation r1 = new Recommendation("P1", "R1");
        Recommendation r2 = new Recommendation("P2", "R2");

        when(repo.findAll()).thenReturn(List.of(r1, r2));

        List<Recommendation> recommendations = service.getAllRecommendations();
        assertEquals(2, recommendations.size());
        verify(repo, times(1)).findAll();
    }

    @Test
    void testSaveRecommendation() {
        RecommendationRepository repo = mock(RecommendationRepository.class);
        RecommendationService service = new RecommendationService(repo);

        Recommendation r = new Recommendation("P1", "R1");
        when(repo.save(r)).thenReturn(r);

        Recommendation saved = service.saveRecommendation(r);
        assertEquals("P1", saved.getProductId());
        assertEquals("R1", saved.getRecommendedProductId());
        verify(repo, times(1)).save(r);
    }

    @Test
    void testGetRecommendationsByProductId() {
        RecommendationRepository repo = mock(RecommendationRepository.class);
        RecommendationService service = new RecommendationService(repo);

        Recommendation r = new Recommendation("P1", "R1");
        when(repo.findByProductId("P1")).thenReturn(List.of(r));

        List<Recommendation> result = service.getRecommendationsByProductId("P1");
        assertEquals(1, result.size());
        assertEquals("R1", result.get(0).getRecommendedProductId());
        verify(repo, times(1)).findByProductId("P1");
    }
}
