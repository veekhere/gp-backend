package com.veekhere.rentrate.service;

import com.veekhere.rentrate.domain.enums.OperationStatus;
import com.veekhere.rentrate.domain.model.OperationResultModel.OperationResult;
import com.veekhere.rentrate.domain.model.PlaceModel;
import com.veekhere.rentrate.domain.projection.PlaceProjection;
import com.veekhere.rentrate.domain.repository.PlaceRepository;
import com.veekhere.rentrate.domain.repository.entity.PlaceEntity;
import com.veekhere.rentrate.domain.repository.mapper.PlaceEntityMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PlaceService {
    private final PlaceRepository placeRepository;

    public Collection<PlaceModel.PlaceProjection> searchPlaces(PlaceModel.PlaceFilter filter) {
        PlaceEntityMapper placeEntityMapper = PlaceEntityMapper.MAPPER;
        String filterJson = JsonService.toJson(filter);
        List<PlaceProjection> allProducts = placeRepository.searchPlaces(filterJson);
        return allProducts.stream().map(placeEntityMapper::map).toList();
    }

    public PlaceModel.Place getPlace(UUID id) {
        return placeRepository
                .findById(id)
                .map(PlaceEntityMapper.MAPPER::map)
                .orElse(null);
    }

    public OperationResult createProduct(PlaceModel.PlaceInput placeInput) {
        PlaceEntityMapper placeEntityMapper = PlaceEntityMapper.MAPPER;
        PlaceEntity entity = placeEntityMapper.map(placeInput);

        try {
            placeRepository.save(entity);
            return new OperationResult(OperationStatus.SUCCESS);
        } catch (Exception e) {
            System.out.println(e); // [DEBUG]
            return new OperationResult(OperationStatus.FAILED);
        }
    }

    public OperationResult updatePlace(PlaceModel.PlaceInput placeInput) {
        PlaceEntityMapper placeEntityMapper = PlaceEntityMapper.MAPPER;
        PlaceEntity entity = placeEntityMapper.map(placeInput);

        try {
            placeRepository.save(entity);
            return new OperationResult(OperationStatus.SUCCESS);
        } catch (Exception e) {
            System.out.println(e); // [DEBUG]
            return new OperationResult(OperationStatus.FAILED);
        }
    }

    public OperationResult deletePlace(UUID id) {

        Optional<PlaceEntity> maybePlace = placeRepository.findById(id);

        if (maybePlace.isEmpty()) {
            return new OperationResult(OperationStatus.FAILED);
        } else {
            try {
                placeRepository.delete(maybePlace.get());
                return new OperationResult(OperationStatus.SUCCESS);
            } catch (Exception e) {
                System.out.println(e); // [DEBUG]
                return new OperationResult(OperationStatus.FAILED);
            }
        }
    }
}
