package com.github.brankale.dto;

import com.google.gson.annotations.SerializedName;

import java.math.BigDecimal;
import java.util.List;

public record OffersDto(
        @SerializedName("@type") String type,
        String url,
        String priceCurrency,
        BigDecimal price,
        String itemCondition,
        String availability,
        List<SellerDto> seller
) {
    @Override
    public String toString() {
        return "OffersDto{" +
                "type='" + type + '\'' +
                ", url='" + url + '\'' +
                ", priceCurrency='" + priceCurrency + '\'' +
                ", price=" + price +
                ", itemCondition='" + itemCondition + '\'' +
                ", availability='" + availability + '\'' +
                ", seller=" + seller +
                '}';
    }
}
