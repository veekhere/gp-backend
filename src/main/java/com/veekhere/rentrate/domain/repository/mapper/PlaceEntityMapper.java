package com.veekhere.rentrate.domain.repository.mapper;

import com.veekhere.rentrate.domain.model.PlaceModel;
import com.veekhere.rentrate.domain.projection.PlaceProjection;
import com.veekhere.rentrate.domain.repository.entity.PlaceEntity;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

@Named("PlaceEntityMapper")
@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class PlaceEntityMapper {
    public static final PlaceEntityMapper MAPPER = Mappers.getMapper(PlaceEntityMapper.class);

    public abstract PlaceModel.Place map(PlaceEntity placeEntity);

    @Mappings({
            @Mapping(target = "spaceType", expression = "java(SpaceTypeEnum.valueOf(placeProjection.getSpaceType()))"),
            @Mapping(target = "rentType", expression = "java(placeProjection.getRentType().stream().map(value -> RentTypeEnum.valueOf(value)).toList())"),
    })
    public abstract PlaceModel.PlaceProjection map(PlaceProjection placeProjection);

    public abstract PlaceEntity map(PlaceModel.PlaceInput placeInput);
}
