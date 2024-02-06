package com.veekhere.rentrate.domain.repository.mapper;

import com.veekhere.rentrate.domain.model.StoreModel.Store;
import com.veekhere.rentrate.domain.model.StoreModel.StoreInput;
import com.veekhere.rentrate.domain.repository.entity.StoreEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, uses = ProductEntityMapper.class)
public abstract class StoreEntityMapper {
    public static final StoreEntityMapper MAPPER = Mappers.getMapper(StoreEntityMapper.class);

    @Mappings({
        @Mapping(target = "products", qualifiedByName = { "ProductEntityMapper", "mapFromEntity" }),
        @Mapping(target = "rating", expression = "java(storeEntity.getRating())")
    })
    public abstract Store map(StoreEntity storeEntity);

    public abstract StoreEntity map(StoreInput bookInput);
}
