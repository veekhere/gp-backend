package com.veekhere.storebase.service;

import com.veekhere.storebase.domain.enums.OperationStatus;
import com.veekhere.storebase.domain.model.OperationResultModel.OperationResult;
import com.veekhere.storebase.domain.model.StoreModel.Store;
import com.veekhere.storebase.domain.model.StoreModel.StoreFilter;
import com.veekhere.storebase.domain.model.StoreModel.StoreInput;
import com.veekhere.storebase.domain.repository.StoreRepository;
import com.veekhere.storebase.domain.repository.entity.StoreEntity;
import com.veekhere.storebase.domain.repository.mapper.StoreEntityMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class StoreService {
    private final StoreRepository storeRepository;

    public Collection<Store> searchStores(StoreFilter filter) {
        StoreEntityMapper storeEntityMapper = StoreEntityMapper.MAPPER;
        String filterJson = JsonService.toJson(filter);
        List<StoreEntity> allStores = storeRepository.searchStores(filterJson);
        return allStores.stream().map(storeEntityMapper::map).toList();
    }

    public Store getStore(UUID id) {
        return storeRepository
                .findById(id)
                .map(StoreEntityMapper.MAPPER::map)
                .orElse(null);
    }

    public OperationResult createStore(StoreInput storeInput) {
        StoreEntityMapper storeEntityMapper = StoreEntityMapper.MAPPER;
        StoreEntity entity = storeEntityMapper.map(storeInput);

        try {
            storeRepository.save(entity);
            return new OperationResult(OperationStatus.SUCCESS);
        } catch (Exception e) {
            System.out.println(e);
            return new OperationResult(OperationStatus.FAILED);
        }
    }

    public OperationResult updateStore(StoreInput storeInput) {
        StoreEntityMapper storeEntityMapper = StoreEntityMapper.MAPPER;
        StoreEntity entity = storeEntityMapper.map(storeInput);

        try {
            storeRepository.save(entity);
            return new OperationResult(OperationStatus.SUCCESS);
        } catch (Exception e) {
            System.out.println(e);
            return new OperationResult(OperationStatus.FAILED);
        }
    }

    public OperationResult deleteStore(UUID id) {

        Optional<StoreEntity> maybeStore = storeRepository.findById(id);

        if (maybeStore.isEmpty()) {
            return new OperationResult(OperationStatus.FAILED);
        } else {
            try {
                storeRepository.delete(maybeStore.get());
                return new OperationResult(OperationStatus.SUCCESS);
            } catch (Exception e) {
                System.out.println(e);
                return new OperationResult(OperationStatus.FAILED);
            }
        }
    }
}
