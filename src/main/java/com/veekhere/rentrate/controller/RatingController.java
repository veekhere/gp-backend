package com.veekhere.rentrate.controller;

import com.veekhere.rentrate.domain.model.RatingModel;
import com.veekhere.rentrate.service.RatingService;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.Collection;

@Controller
@RequiredArgsConstructor
public class RatingController {
    private final RatingService ratingService;

    @QueryMapping
    public Collection<RatingModel.Rating> searchRatings() {
        return ratingService.searchRatings();
    }
}
