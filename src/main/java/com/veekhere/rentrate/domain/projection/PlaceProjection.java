package com.veekhere.rentrate.domain.projection;

import java.util.List;
import java.util.UUID;

public interface PlaceProjection {
    UUID getId();
    List<String> getRentType();
    String getSpaceType();
    Float getArea();
    Integer getFloor();
    Float getLatitude();
    Float getLongitude();
    String getCountry();
    String getState();
    String getCity();
    String getRoad();
    Integer getHouseNumber();
    Float getAvgPrice();
    Float getAvgPlaceRating();
    Float getAvgLandlordRating();
    Float getAvgNeighborRating();
}
