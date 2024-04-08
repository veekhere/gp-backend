package com.veekhere.rentrate.domain.repository;

import com.veekhere.rentrate.domain.projection.PlaceProjection;
import com.veekhere.rentrate.domain.repository.entity.PlaceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface PlaceRepository extends JpaRepository<PlaceEntity, UUID> {

    @Query(value = "SELECT * FROM search_places(CAST(:#{#filter} AS JSON));", nativeQuery = true)
    List<PlaceProjection> searchPlaces(@Param("filter") String filter);
}
