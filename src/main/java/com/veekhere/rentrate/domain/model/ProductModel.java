package com.veekhere.rentrate.domain.model;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

public class ProductModel {

    public record Product(
            UUID id,
            String title,
            String description,
            BigDecimal price,
            Integer availableAmount,
            Integer totalRatings,
            Float rating,
            List<ProductRatingModel.ProductRating> ratings,
            UUID store
    ) {}

    public record ProductInput(
            UUID id,
            String title,
            String description,
            BigDecimal price,
            Integer availableAmount,
            UUID store
    ) {}

    public record ProductProjection(
            UUID id,
            String title,
            BigDecimal price,
            Integer availableAmount,
            String storeName,
            Integer totalRatings,
            Float rating
    ) {}

    public record ProductFilter(
            UUID store,
            String title,
            BigDecimal priceFrom,
            BigDecimal priceTo,
            Boolean isAvailable
    ) {}

    public record ProductProjectionFilter(
            String title,
            BigDecimal priceFrom,
            BigDecimal priceTo,
            Boolean isAvailable
    ) {}
}
