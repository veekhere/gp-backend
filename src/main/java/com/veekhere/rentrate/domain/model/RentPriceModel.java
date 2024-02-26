package com.veekhere.rentrate.domain.model;

public class RentPriceModel {
    public record RentPrice(
            Integer min,
            Integer max
    ) {}
}
