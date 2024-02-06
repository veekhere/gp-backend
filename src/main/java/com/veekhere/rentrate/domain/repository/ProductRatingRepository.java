package com.veekhere.rentrate.domain.repository;

import com.veekhere.rentrate.domain.repository.entity.ProductRatingEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ProductRatingRepository extends JpaRepository<ProductRatingEntity, UUID> {}
