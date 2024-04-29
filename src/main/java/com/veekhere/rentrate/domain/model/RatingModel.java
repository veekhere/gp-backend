package com.veekhere.rentrate.domain.model;

import com.veekhere.rentrate.domain.enums.RentTypeEnum;
import com.veekhere.rentrate.domain.projection.CommonProjection;

import java.time.OffsetDateTime;
import java.util.UUID;

public class RatingModel {

    public record Rating(
            UUID id,
            Integer price,
            RentTypeEnum rentType,
            String pros,
            String cons,
            String comment,
            Integer placeRating,
            Integer landlordRating,
            Integer neighborRating,
            CommonProjection place,
            OffsetDateTime createdAt
    ) {}

    public record RatingInput(
            UUID id,
            UUID placeId,
            Integer price,
            RentTypeEnum rentType,
            String pros,
            String cons,
            String comment,
            Integer placeRating,
            Integer landlordRating,
            Integer neighborRating
    ) {}
}
