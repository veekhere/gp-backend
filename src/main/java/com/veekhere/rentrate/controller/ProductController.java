package com.veekhere.rentrate.controller;

import com.veekhere.rentrate.domain.model.OperationResultModel.OperationResult;
import com.veekhere.rentrate.domain.model.ProductModel.*;
import com.veekhere.rentrate.domain.model.ProductRatingModel;
import com.veekhere.rentrate.service.ProductRatingService;
import com.veekhere.rentrate.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.Collection;
import java.util.UUID;

@Controller
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;
    private final ProductRatingService productRatingService;

    @QueryMapping
    public Collection<Product> searchProducts(@Argument ProductFilter filter) {
        return productService.searchProducts(filter);
    }

    @QueryMapping
    public Collection<ProductProjection> searchAllProducts(@Argument ProductProjectionFilter filter) {
        return productService.searchAllProducts(filter);
    }

    @QueryMapping
    public Product getProduct(@Argument UUID id) {
        return productService.getProduct(id);
    }

    @MutationMapping
    public OperationResult createProduct(@Argument ProductInput product) {
        return productService.createProduct(product);
    }

    @MutationMapping
    public OperationResult updateProduct(@Argument ProductInput product) {
        return productService.updateProduct(product);
    }

    @MutationMapping
    public OperationResult deleteProduct(@Argument UUID id) {
        return productService.deleteProduct(id);
    }

    @MutationMapping
    public OperationResult rateProduct(@Argument ProductRatingModel.ProductRatingInput rating) {
        return productRatingService.rateProduct(rating);
    }
}
