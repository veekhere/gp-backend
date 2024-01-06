package com.veekhere.storebase.domain.model;

import java.util.List;
import java.util.UUID;

public class StoreModel {

    public record Store(
            UUID id,
            String name,
            String description,
            List<ProductModel.Product> products,
            Float rating
    ) {}

    public record StoreFilter(
            String name
    ) {}

    public record StoreInput(UUID id, String name, String description) {}
}
