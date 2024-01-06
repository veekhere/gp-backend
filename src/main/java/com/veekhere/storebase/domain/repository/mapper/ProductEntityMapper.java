package com.veekhere.storebase.domain.repository.mapper;

import com.veekhere.storebase.domain.dto.ProductProjectionDTO;
import com.veekhere.storebase.domain.model.ProductModel.Product;
import com.veekhere.storebase.domain.model.ProductModel.ProductInput;
import com.veekhere.storebase.domain.model.ProductModel.ProductProjection;
import com.veekhere.storebase.domain.repository.entity.ProductEntity;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

@Named("ProductEntityMapper")
@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, uses = ProductRatingEntityMapper.class)
public abstract class ProductEntityMapper {
    public static final ProductEntityMapper MAPPER = Mappers.getMapper(ProductEntityMapper.class);

    @Named("mapFromEntity")
    @Mappings({
        @Mapping(target = "ratings", qualifiedByName = { "ProductRatingEntityMapper", "mapWithoutProduct" }),
        @Mapping(target = "totalRatings", expression = "java(productEntity.getTotalRatings())"),
        @Mapping(target = "store", expression = "java(productEntity.getStore().getId())")
    })
    public abstract Product map(ProductEntity productEntity);

    public abstract ProductProjection map(ProductProjectionDTO productEntity);

    @Mapping(source = "store", target = "store.id")
    public abstract ProductEntity map(ProductInput productInput);
}
