package com.veekhere.storebase.domain.repository.mapper;

import com.veekhere.storebase.domain.model.ProductRatingModel.ProductRating;
import com.veekhere.storebase.domain.model.ProductRatingModel.ProductRatingInput;
import com.veekhere.storebase.domain.repository.entity.ProductRatingEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Named("ProductRatingEntityMapper")
@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class ProductRatingEntityMapper {
    public static final ProductRatingEntityMapper MAPPER = Mappers.getMapper(ProductRatingEntityMapper.class);

    @Named("mapWithoutProduct")
    @Mapping(target = "product", ignore = true)
    public abstract ProductRating mapWithoutProduct(ProductRatingEntity productRatingEntity);

    @Mapping(source = "product", target = "product.id")
    public abstract ProductRatingEntity map(ProductRatingInput productRatingInput);
}
