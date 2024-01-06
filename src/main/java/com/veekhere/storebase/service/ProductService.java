package com.veekhere.storebase.service;

import com.veekhere.storebase.domain.enums.OperationStatus;
import com.veekhere.storebase.domain.model.OperationResultModel.OperationResult;
import com.veekhere.storebase.domain.model.ProductModel.*;
import com.veekhere.storebase.domain.repository.ProductRepository;
import com.veekhere.storebase.domain.repository.entity.ProductEntity;
import com.veekhere.storebase.domain.dto.ProductProjectionDTO;
import com.veekhere.storebase.domain.repository.mapper.ProductEntityMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;

    public Collection<Product> searchProducts(ProductFilter filter) {
        ProductEntityMapper productEntityMapper = ProductEntityMapper.MAPPER;
        String filterJson = JsonService.toJson(filter);
        List<ProductEntity> allProducts = productRepository.searchProducts(filterJson);
        return allProducts.stream().map(productEntityMapper::map).toList();
    }

    public Collection<ProductProjection> searchAllProducts(ProductProjectionFilter filter) {
        ProductEntityMapper productEntityMapper = ProductEntityMapper.MAPPER;
        String filterJson = JsonService.toJson(filter);
        List<ProductProjectionDTO> productProjections = productRepository.searchAllProducts(filterJson);
        return productProjections.stream().map(productEntityMapper::map).toList();
    }

    public Product getProduct(UUID id) {
        return productRepository
                .findById(id)
                .map(ProductEntityMapper.MAPPER::map)
                .orElse(null);
    }

    public OperationResult createProduct(ProductInput productInput) {
        ProductEntityMapper productEntityMapper = ProductEntityMapper.MAPPER;
        ProductEntity entity = productEntityMapper.map(productInput);

        try {
            productRepository.save(entity);
            return new OperationResult(OperationStatus.SUCCESS);
        } catch (Exception e) {
            System.out.println(e);
            return new OperationResult(OperationStatus.FAILED);
        }
    }

    public OperationResult updateProduct(ProductInput productInput) {
        ProductEntityMapper productEntityMapper = ProductEntityMapper.MAPPER;
        ProductEntity entity = productEntityMapper.map(productInput);

        try {
            productRepository.save(entity);
            return new OperationResult(OperationStatus.SUCCESS);
        } catch (Exception e) {
            System.out.println(e);
            return new OperationResult(OperationStatus.FAILED);
        }
    }

    public OperationResult deleteProduct(UUID id) {

        Optional<ProductEntity> maybeProduct = productRepository.findById(id);

        if (maybeProduct.isEmpty()) {
            return new OperationResult(OperationStatus.FAILED);
        } else {
            try {
                productRepository.delete(maybeProduct.get());
                return new OperationResult(OperationStatus.SUCCESS);
            } catch (Exception e) {
                System.out.println(e);
                return new OperationResult(OperationStatus.FAILED);
            }
        }
    }
}
