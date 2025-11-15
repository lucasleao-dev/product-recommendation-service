package com.product.recommendation.dto;

public class RecommendationResponse {
    private String productId;
    private String recommendedProductId;

    public RecommendationResponse(String productId, String recommendedProductId) {
        this.productId = productId;
        this.recommendedProductId = recommendedProductId;
    }

    public String getProductId() { return productId; }
    public String getRecommendedProductId() { return recommendedProductId; }
}
