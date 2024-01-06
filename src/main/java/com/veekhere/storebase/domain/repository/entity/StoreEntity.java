package com.veekhere.storebase.domain.repository.entity;

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

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "store")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class StoreEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(nullable = false, updatable = false)
    UUID id;

    @Column(nullable = false)
    String name;

    String description;

    @OneToMany(mappedBy = "store", cascade = CascadeType.REMOVE)
    List<ProductEntity> products;

    transient Float rating = 0F;

    public Float getRating() {
        OptionalDouble rating = products
                .stream()
                .mapToDouble(value -> value.getRating())
                .filter(value -> value > 0D)
                .average();

        BigDecimal formattedRating = BigDecimal.valueOf(rating.orElse(0))
                .setScale(2, RoundingMode.HALF_EVEN);

        return formattedRating.floatValue();
    }
}
