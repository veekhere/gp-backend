package com.veekhere.storebase.domain.dto;

import java.math.BigDecimal;
import java.util.UUID;

public interface ProductProjectionDTO {
    UUID getId();
    String getTitle();
    BigDecimal getPrice();
    Integer getAvailableAmount();
    String getStoreName();
    Float getRating();
    Integer getTotalRatings();
}
