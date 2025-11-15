package com.product.recommendation.service;

import com.product.recommendation.model.Recommendation;
import com.product.recommendation.repository.RecommendationRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecommendationService {

    private final RecommendationRepository repository;

    public RecommendationService(RecommendationRepository repository) {
        this.repository = repository;
    }

    public List<Recommendation> getAllRecommendations() {
        return repository.findAll();
    }

    public Recommendation saveRecommendation(Recommendation recommendation) {
        return repository.save(recommendation);
    }

    public List<Recommendation> getRecommendationsByProductId(String productId) {
        return repository.findByProductId(productId);
    }
}
