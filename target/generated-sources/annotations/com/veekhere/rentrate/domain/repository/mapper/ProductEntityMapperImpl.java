package com.veekhere.rentrate.domain.repository.mapper;

import com.veekhere.rentrate.domain.dto.ProductProjectionDTO;
import com.veekhere.rentrate.domain.model.ProductModel;
import com.veekhere.rentrate.domain.model.ProductRatingModel;
import com.veekhere.rentrate.domain.repository.entity.ProductEntity;
import com.veekhere.rentrate.domain.repository.entity.ProductRatingEntity;
import com.veekhere.rentrate.domain.repository.entity.StoreEntity;
import java.math.BigDecimal;
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
public class ProductEntityMapperImpl extends ProductEntityMapper {

    private final ProductRatingEntityMapper productRatingEntityMapper = Mappers.getMapper( ProductRatingEntityMapper.class );

    @Override
    public ProductModel.Product map(ProductEntity productEntity) {
        if ( productEntity == null ) {
            return null;
        }

        List<ProductRatingModel.ProductRating> ratings = null;
        UUID id = null;
        String title = null;
        String description = null;
        BigDecimal price = null;
        Integer availableAmount = null;
        Float rating = null;

        ratings = productRatingEntityListToProductRatingList( productEntity.getRatings() );
        id = productEntity.getId();
        title = productEntity.getTitle();
        description = productEntity.getDescription();
        price = productEntity.getPrice();
        availableAmount = productEntity.getAvailableAmount();
        rating = productEntity.getRating();

        Integer totalRatings = productEntity.getTotalRatings();
        UUID store = productEntity.getStore().getId();

        ProductModel.Product product = new ProductModel.Product( id, title, description, price, availableAmount, totalRatings, rating, ratings, store );

        return product;
    }

    @Override
    public ProductModel.ProductProjection map(ProductProjectionDTO productEntity) {
        if ( productEntity == null ) {
            return null;
        }

        UUID id = null;
        String title = null;
        BigDecimal price = null;
        Integer availableAmount = null;
        String storeName = null;
        Integer totalRatings = null;
        Float rating = null;

        id = productEntity.getId();
        title = productEntity.getTitle();
        price = productEntity.getPrice();
        availableAmount = productEntity.getAvailableAmount();
        storeName = productEntity.getStoreName();
        totalRatings = productEntity.getTotalRatings();
        rating = productEntity.getRating();

        ProductModel.ProductProjection productProjection = new ProductModel.ProductProjection( id, title, price, availableAmount, storeName, totalRatings, rating );

        return productProjection;
    }

    @Override
    public ProductEntity map(ProductModel.ProductInput productInput) {
        if ( productInput == null ) {
            return null;
        }

        ProductEntity productEntity = new ProductEntity();

        productEntity.setStore( productInputToStoreEntity( productInput ) );
        productEntity.setId( productInput.id() );
        productEntity.setTitle( productInput.title() );
        productEntity.setDescription( productInput.description() );
        productEntity.setPrice( productInput.price() );
        productEntity.setAvailableAmount( productInput.availableAmount() );

        return productEntity;
    }

    protected List<ProductRatingModel.ProductRating> productRatingEntityListToProductRatingList(List<ProductRatingEntity> list) {
        if ( list == null ) {
            return null;
        }

        List<ProductRatingModel.ProductRating> list1 = new ArrayList<ProductRatingModel.ProductRating>( list.size() );
        for ( ProductRatingEntity productRatingEntity : list ) {
            list1.add( productRatingEntityMapper.mapWithoutProduct( productRatingEntity ) );
        }

        return list1;
    }

    protected StoreEntity productInputToStoreEntity(ProductModel.ProductInput productInput) {
        if ( productInput == null ) {
            return null;
        }

        StoreEntity storeEntity = new StoreEntity();

        storeEntity.setId( productInput.store() );

        return storeEntity;
    }
}
