package com.veekhere.rentrate.service;

import com.veekhere.rentrate.domain.enums.OperationStatus;
import com.veekhere.rentrate.domain.model.OperationResultModel.*;
import com.veekhere.rentrate.domain.model.RatingModel;
import com.veekhere.rentrate.domain.repository.RatingRepository;
import com.veekhere.rentrate.domain.repository.entity.RatingEntity;
import com.veekhere.rentrate.domain.repository.mapper.RatingEntityMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RatingService {
    private final RatingRepository ratingRepository;

    public Collection<RatingModel.Rating> searchRatings() {
        RatingEntityMapper ratingEntityMapper = RatingEntityMapper.MAPPER;
        List<RatingEntity> allRatings = ratingRepository.findAllByOrderByCreatedAtDesc();
        return allRatings.stream().map(ratingEntityMapper::map).toList();
    }

    public OperationResult ratePlace(RatingModel.RatingInput placeRatingInput) {
        RatingEntityMapper placeRatingEntityMapper = RatingEntityMapper.MAPPER;
        RatingEntity entity = placeRatingEntityMapper.map(placeRatingInput);

        try {
            ratingRepository.save(entity);
            return new OperationResult(OperationStatus.SUCCESS);
        } catch (Exception e) {
            System.out.println(e); // [DEBUG]
            return new OperationResult(OperationStatus.FAILED);
        }
    }
}
