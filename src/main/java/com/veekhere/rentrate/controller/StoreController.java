package com.veekhere.rentrate.controller;

import com.veekhere.rentrate.domain.model.OperationResultModel.*;
import com.veekhere.rentrate.domain.model.StoreModel.*;
import com.veekhere.rentrate.service.StoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.Collection;
import java.util.UUID;

@Controller
@RequiredArgsConstructor
public class StoreController {
    private final StoreService storeService;

    @QueryMapping
    public Collection<Store> searchStores(@Argument StoreFilter filter) {
        return storeService.searchStores(filter);
    }

    @QueryMapping
    public Store getStore(@Argument UUID id) {
        return storeService.getStore(id);
    }

    @MutationMapping
    public OperationResult createStore(@Argument StoreInput store) {
        return storeService.createStore(store);
    }

    @MutationMapping
    public OperationResult updateStore(@Argument StoreInput store) {
        return storeService.updateStore(store);
    }

    @MutationMapping
    public OperationResult deleteStore(@Argument UUID id) {
        return storeService.deleteStore(id);
    }
}
