package com.veekhere.storebase.domain.repository;

import com.veekhere.storebase.domain.dto.ProductProjectionDTO;
import com.veekhere.storebase.domain.repository.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface ProductRepository extends JpaRepository<ProductEntity, UUID> {

    @Query(value = "SELECT * FROM search_products(CAST(:#{#filter} AS JSON));", nativeQuery = true)
    List<ProductEntity> searchProducts(@Param("filter") String filter);

    @Query(value = "SELECT * FROM search_all_products(CAST(:#{#filter} AS JSON));", nativeQuery = true)
    List<ProductProjectionDTO> searchAllProducts(@Param("filter") String filter);
}
