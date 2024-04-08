package com.veekhere.rentrate.domain.repository.entity;

import com.veekhere.rentrate.domain.enums.RentTypeEnum;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.util.UUID;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "place_rating")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RatingEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(nullable = false, updatable = false)
    UUID id;

    Integer price;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 10)
    RentTypeEnum rentType;

    @Column(nullable = false, length = 4000)
    String pros;

    @Column(nullable = false, length = 4000)
    String cons;

    @Column(length = 4000)
    String comment;

    @Column(nullable = false)
    Integer placeRating;

    @Column(nullable = false)
    Integer landlordRating;

    Integer neighborRating;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "place_id", nullable = false)
    PlaceEntity place;
}
