package com.veekhere.rentrate.domain.repository.entity;

import com.veekhere.rentrate.domain.enums.RentTypeEnum;
import com.veekhere.rentrate.domain.enums.SpaceTypeEnum;
import com.veekhere.rentrate.domain.model.RentPriceModel.RentPrice;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.OptionalDouble;
import java.util.UUID;
import java.util.function.Supplier;
import java.util.function.ToLongFunction;
import java.util.stream.IntStream;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "place")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PlaceEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(nullable = false, updatable = false)
    UUID id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 26)
    List<RentTypeEnum> rentType;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 10)
    SpaceTypeEnum spaceType;

    @Column(nullable = false)
    Float area;

    @Column(nullable = false)
    Integer floor;

    @Column(nullable = false)
    Float latitude;

    @Column(nullable = false)
    Float longitude;

    @Column(nullable = false)
    String country;

    String state;

    @Column(nullable = false)
    String city;

    @Column(nullable = false)
    String road;

    @Column(nullable = false)
    Integer houseNumber;

    @OneToMany(mappedBy = "place", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    List<RatingEntity> ratings;

    transient RentPrice shortTermPrices;

    transient RentPrice longTermPrices;

    transient Float avgPlaceRating = 0F;

    transient Float avgLandlordRating = 0F;

    transient Float avgNeighborRating = 0F;

    public RentPrice getShortTermPrices() {
        return getPrices(RentTypeEnum.SHORT_TERM);
    }

    public RentPrice getLongTermPrices() {
        return getPrices(RentTypeEnum.LONG_TERM);
    }

    public Float getAvgPlaceRating() {
        return getAvgRating(value -> value.getPlaceRating().longValue());
    }

    public Float getAvgLandlordRating() {
        return getAvgRating(value -> value.getLandlordRating().longValue());
    }

    public Float getAvgNeighborRating() {
        return getAvgRating(value -> value.getNeighborRating().longValue());
    }

    private RentPrice getPrices(RentTypeEnum rentType) {
        Supplier<IntStream> streamSupplier = () -> ratings
                .stream()
                .filter(value -> value.getRentType().equals(rentType))
                .mapToInt(RatingEntity::getPrice);

        Integer minPrice = streamSupplier
                .get()
                .min()
                .orElse(0);
        Integer maxPrice = streamSupplier
                .get()
                .max()
                .orElse(0);

        return new RentPrice(minPrice, maxPrice);
    }

    private Float getAvgRating(ToLongFunction<RatingEntity> entityToLongFunction) {
        OptionalDouble rating = ratings
                .stream()
                .mapToLong(entityToLongFunction)
                .average();

        BigDecimal formattedRating = BigDecimal.valueOf(rating.orElse(0))
                .setScale(2, RoundingMode.HALF_EVEN);

        return formattedRating.floatValue();
    }
}
