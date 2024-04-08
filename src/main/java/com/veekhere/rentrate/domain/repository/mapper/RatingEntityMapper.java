package com.veekhere.rentrate.domain.repository.mapper;

import com.veekhere.rentrate.domain.enums.RentTypeEnum;
import com.veekhere.rentrate.domain.model.RatingModel;
import com.veekhere.rentrate.domain.projection.CommonProjection;
import com.veekhere.rentrate.domain.repository.entity.RatingEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.UUID;

@Named("RatingEntityMapper")
@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class RatingEntityMapper {
    public static final RatingEntityMapper MAPPER = Mappers.getMapper(RatingEntityMapper.class);

    @Mapping(source = "placeId", target = "place.id")
    public abstract RatingEntity map(RatingModel.RatingInput ratingInput);

    public RatingModel.Rating map(RatingEntity ratingEntity) {
        if (ratingEntity == null) {
            return null;
        }

        UUID id = ratingEntity.getId();
        Integer price = ratingEntity.getPrice();
        RentTypeEnum rentType = ratingEntity.getRentType();
        String pros = ratingEntity.getPros();
        String cons = ratingEntity.getCons();
        String comment = ratingEntity.getComment();
        Integer placeRating = ratingEntity.getPlaceRating();
        Integer landlordRating = ratingEntity.getLandlordRating();
        Integer neighborRating = ratingEntity.getNeighborRating();
        UUID placeId = ratingEntity.getPlace().getId();

        CommonProjection place = new CommonProjection(placeId);

        return new RatingModel.Rating(
                id,
                price,
                rentType,
                pros,
                cons,
                comment,
                placeRating,
                landlordRating,
                neighborRating,
                place
        );
    }
}
