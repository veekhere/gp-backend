package com.veekhere.storebase.domain.repository;

import com.veekhere.storebase.domain.repository.entity.StoreEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface StoreRepository extends JpaRepository<StoreEntity, UUID> {

    @Query(value = "SELECT * FROM search_stores(CAST(:#{#filter} AS JSON));", nativeQuery = true)
    List<StoreEntity> searchStores(@Param("filter") String filter);
}
