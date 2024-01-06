package com.veekhere.storebase.domain.model;

import java.util.UUID;

public class ProductRatingModel {

    public record ProductRating(
            UUID id,
            ProductModel.Product product,
            Integer rating,
            String comment
    ) {}

    public record ProductRatingInput(
            UUID id,
            UUID product,
            Integer rating,
            String comment
    ) {}
}
