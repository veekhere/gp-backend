package com.veekhere.rentrate.domain.repository.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.util.Date;
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

    @Column(nullable = false)
    String rentType;

    @Column(nullable = false)
    Date rentDateFrom;

    @Column(nullable = false)
    Date rentDateTo;

    @Column(nullable = false)
    String pros;

    @Column(nullable = false)
    String cons;

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
