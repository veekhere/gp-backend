package com.veekhere.rentrate.service;

import com.veekhere.rentrate.domain.enums.OperationStatus;
import com.veekhere.rentrate.domain.model.OperationResultModel.*;
import com.veekhere.rentrate.domain.model.ProductRatingModel.*;
import com.veekhere.rentrate.domain.repository.ProductRatingRepository;
import com.veekhere.rentrate.domain.repository.entity.ProductRatingEntity;
import com.veekhere.rentrate.domain.repository.mapper.ProductRatingEntityMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductRatingService {
    private  final ProductRatingRepository productRatingRepository;

    public OperationResult rateProduct(ProductRatingInput productRatingInput) {
        ProductRatingEntityMapper productRatingEntityMapper = ProductRatingEntityMapper.MAPPER;
        ProductRatingEntity entity = productRatingEntityMapper.map(productRatingInput);

        try {
            productRatingRepository.save(entity);
            return new OperationResult(OperationStatus.SUCCESS);
        } catch (Exception e) {
            System.out.println(e);
            return new OperationResult(OperationStatus.FAILED);
        }
    }
}
