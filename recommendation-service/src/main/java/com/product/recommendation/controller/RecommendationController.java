package com.product.recommendation.controller;

import com.product.recommendation.model.Recommendation;
import com.product.recommendation.service.RecommendationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class RecommendationController {

    private final RecommendationService service;

    public RecommendationController(RecommendationService service) {
        this.service = service;
    }

    @GetMapping("/recommendations")
    public ResponseEntity<List<Recommendation>> getAll() {
        return ResponseEntity.ok(service.getAllRecommendations());
    }

    @PostMapping("/recommendations")
    public ResponseEntity<Recommendation> create(@RequestBody Recommendation recommendation) {
        return ResponseEntity.ok(service.saveRecommendation(recommendation));
    }
}
