package com.veekhere.rentrate.domain.model;

import com.veekhere.rentrate.domain.enums.RentTypeEnum;
import com.veekhere.rentrate.domain.enums.SpaceTypeEnum;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

public class PlaceModel {

    public record Place(
            UUID id,
            List<RentTypeEnum> rentType,
            SpaceTypeEnum spaceType,
            Float area,
            Integer floor,
            Float latitude,
            Float longitude,
            String country,
            String state,
            String city,
            String road,
            String houseNumber,
            List<RatingModel.Rating> ratings,
            RentPriceModel.RentPrice shortTermPrices,
            RentPriceModel.RentPrice longTermPrices,
            Float avgPlaceRating,
            Float avgLandlordRating,
            Float avgNeighborRating
    ) {}

    public record PlaceProjection(
            UUID id,
            List<RentTypeEnum> rentType,
            SpaceTypeEnum spaceType,
            Float area,
            Integer floor,
            Float latitude,
            Float longitude,
            String country,
            String state,
            String city,
            String road,
            String houseNumber,
            Float avgPrice,
            Float avgPlaceRating,
            Float avgLandlordRating,
            Float avgNeighborRating
    ) {}

    public record PlaceInput(
            UUID id,
            List<RentTypeEnum> rentType,
            SpaceTypeEnum spaceType,
            Float area,
            Integer floor,
            Float latitude,
            Float longitude,
            String country,
            String state,
            String city,
            String road,
            String houseNumber
    ) {}

    public record PlaceFilter(
            RentTypeEnum rentType,
            SpaceTypeEnum spaceType,
            Float areaFrom,
            Float areaTo,
            Integer floor,
            BigDecimal priceFrom,
            BigDecimal priceTo,
            String country,
            String state,
            String city,
            String road,
            String houseNumber,
            Float placeRatingFrom,
            Float placeRatingTo,
            Float landlordRatingFrom,
            Float landlordRatingTo,
            Float neighborRatingFrom,
            Float neighborRatingTo
    ) {}
}
