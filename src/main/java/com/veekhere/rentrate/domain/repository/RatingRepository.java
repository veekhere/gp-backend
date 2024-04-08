package com.veekhere.rentrate.domain.repository;

import com.veekhere.rentrate.domain.repository.entity.RatingEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface RatingRepository extends JpaRepository<RatingEntity, UUID> {}
