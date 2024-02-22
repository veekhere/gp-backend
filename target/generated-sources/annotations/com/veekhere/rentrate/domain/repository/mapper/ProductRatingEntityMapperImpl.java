package com.veekhere.rentrate.domain.repository.mapper;

import com.veekhere.rentrate.domain.model.ProductModel;
import com.veekhere.rentrate.domain.model.ProductRatingModel;
import com.veekhere.rentrate.domain.repository.entity.ProductEntity;
import com.veekhere.rentrate.domain.repository.entity.ProductRatingEntity;
import java.util.UUID;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-02-22T08:49:45+0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 20 (Oracle Corporation)"
)
public class ProductRatingEntityMapperImpl extends ProductRatingEntityMapper {

    @Override
    public ProductRatingModel.ProductRating mapWithoutProduct(ProductRatingEntity productRatingEntity) {
        if ( productRatingEntity == null ) {
            return null;
        }

        UUID id = null;
        Integer rating = null;
        String comment = null;

        id = productRatingEntity.getId();
        rating = productRatingEntity.getRating();
        comment = productRatingEntity.getComment();

        ProductModel.Product product = null;

        ProductRatingModel.ProductRating productRating = new ProductRatingModel.ProductRating( id, product, rating, comment );

        return productRating;
    }

    @Override
    public ProductRatingEntity map(ProductRatingModel.ProductRatingInput productRatingInput) {
        if ( productRatingInput == null ) {
            return null;
        }

        ProductRatingEntity productRatingEntity = new ProductRatingEntity();

        productRatingEntity.setProduct( productRatingInputToProductEntity( productRatingInput ) );
        productRatingEntity.setId( productRatingInput.id() );
        productRatingEntity.setRating( productRatingInput.rating() );
        productRatingEntity.setComment( productRatingInput.comment() );

        return productRatingEntity;
    }

    protected ProductEntity productRatingInputToProductEntity(ProductRatingModel.ProductRatingInput productRatingInput) {
        if ( productRatingInput == null ) {
            return null;
        }

        ProductEntity productEntity = new ProductEntity();

        productEntity.setId( productRatingInput.product() );

        return productEntity;
    }
}
