package com.veekhere.rentrate.domain.repository.mapper;

import com.veekhere.rentrate.domain.model.RatingModel;
import com.veekhere.rentrate.domain.repository.entity.RatingEntity;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

@Named("RatingEntityMapper")
@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class RatingEntityMapper {
    public static final RatingEntityMapper MAPPER = Mappers.getMapper(RatingEntityMapper.class);

    @Mapping(source = "placeId", target = "place.id")
    public abstract RatingEntity map(RatingModel.RatingInput ratingInput);

    @Mapping(target = "place", expression = "java(new CommonProjection(ratingEntity.getPlace().getId()))")
    public abstract RatingModel.Rating map(RatingEntity ratingEntity);
}
