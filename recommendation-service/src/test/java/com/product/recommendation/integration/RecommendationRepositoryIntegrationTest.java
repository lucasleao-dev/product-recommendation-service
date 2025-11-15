package com.product.recommendation.integration;

import com.product.recommendation.model.Recommendation;
import com.product.recommendation.repository.RecommendationRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class RecommendationRepositoryIntegrationTest {

    @Autowired
    private RecommendationRepository repository;

    @Test
    void testSaveAndFind() {
        Recommendation recommendation = new Recommendation();
        recommendation.setProductId("Product A");
        recommendation.setRecommendedProductId("Product B");

        repository.save(recommendation);

        var found = repository.findById(recommendation.getId());
        assertThat(found).isPresent();
        assertThat(found.get().getProductId()).isEqualTo("Product A");
        assertThat(found.get().getRecommendedProductId()).isEqualTo("Product B");
    }
}
