package com.veekhere.rentrate.controller;

import com.veekhere.rentrate.domain.model.OperationResultModel.OperationResult;
import com.veekhere.rentrate.domain.model.PlaceModel;
import com.veekhere.rentrate.domain.model.RatingModel;
import com.veekhere.rentrate.service.PlaceService;
import com.veekhere.rentrate.service.RatingService;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.Collection;
import java.util.UUID;

@Controller
@RequiredArgsConstructor
public class PlaceController {
    private final PlaceService placeService;
    private final RatingService ratingService;

    @QueryMapping
    public Collection<PlaceModel.PlaceProjection> searchPlaces(@Argument PlaceModel.PlaceFilter filter) {
        return placeService.searchPlaces(filter);
    }

    @QueryMapping
    public PlaceModel.Place getPlace(@Argument UUID id) {
        return placeService.getPlace(id);
    }

    @MutationMapping
    public OperationResult createPlace(@Argument PlaceModel.PlaceInput place) {
        return placeService.createProduct(place);
    }

    @MutationMapping
    public OperationResult updatePlace(@Argument PlaceModel.PlaceInput place) {
        return placeService.updatePlace(place);
    }

    @MutationMapping
    public OperationResult deletePlace(@Argument UUID id) {
        return placeService.deletePlace(id);
    }

    @MutationMapping
    public OperationResult ratePlace(@Argument RatingModel.RatingInput rating) {
        return ratingService.ratePlace(rating);
    }
}
