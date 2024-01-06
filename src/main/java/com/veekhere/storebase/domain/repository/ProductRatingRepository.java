package com.veekhere.storebase.domain.repository;

import com.veekhere.storebase.domain.repository.entity.ProductRatingEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ProductRatingRepository extends JpaRepository<ProductRatingEntity, UUID> {}
