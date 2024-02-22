package com.veekhere.rentrate.domain.repository.mapper;

import com.veekhere.rentrate.domain.model.ProductModel;
import com.veekhere.rentrate.domain.model.StoreModel;
import com.veekhere.rentrate.domain.repository.entity.ProductEntity;
import com.veekhere.rentrate.domain.repository.entity.StoreEntity;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import javax.annotation.processing.Generated;
import org.mapstruct.factory.Mappers;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-02-22T08:49:45+0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 20 (Oracle Corporation)"
)
public class StoreEntityMapperImpl extends StoreEntityMapper {

    private final ProductEntityMapper productEntityMapper = Mappers.getMapper( ProductEntityMapper.class );

    @Override
    public StoreModel.Store map(StoreEntity storeEntity) {
        if ( storeEntity == null ) {
            return null;
        }

        List<ProductModel.Product> products = null;
        UUID id = null;
        String name = null;
        String description = null;

        products = productEntityListToProductList( storeEntity.getProducts() );
        id = storeEntity.getId();
        name = storeEntity.getName();
        description = storeEntity.getDescription();

        Float rating = storeEntity.getRating();

        StoreModel.Store store = new StoreModel.Store( id, name, description, products, rating );

        return store;
    }

    @Override
    public StoreEntity map(StoreModel.StoreInput bookInput) {
        if ( bookInput == null ) {
            return null;
        }

        StoreEntity storeEntity = new StoreEntity();

        storeEntity.setId( bookInput.id() );
        storeEntity.setName( bookInput.name() );
        storeEntity.setDescription( bookInput.description() );

        return storeEntity;
    }

    protected List<ProductModel.Product> productEntityListToProductList(List<ProductEntity> list) {
        if ( list == null ) {
            return null;
        }

        List<ProductModel.Product> list1 = new ArrayList<ProductModel.Product>( list.size() );
        for ( ProductEntity productEntity : list ) {
            list1.add( productEntityMapper.map( productEntity ) );
        }

        return list1;
    }
}
