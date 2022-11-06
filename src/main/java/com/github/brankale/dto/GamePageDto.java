package com.github.brankale.dto;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public record GamePageDto(
        @SerializedName("@context") String context,
        @SerializedName("@type") String type,
        @SerializedName("name") String name,
        @SerializedName("image") List<String> images,
        @SerializedName("description") String description,
        @SerializedName("sku") Long sku,
        @SerializedName("category") String category,
        @SerializedName("releaseDate") String releaseDate,
        @SerializedName("gtin13") String gtin13,
        @SerializedName("brand") BrandDto brand,
        @SerializedName("offers") List<OffersDto> offers,
        @SerializedName("aggregateRating") RatingDto aggregateRating
) {
//    @Override
//    public String toString() {
//        return "GamePageDto{" +
//                "context='" + context + '\'' +
//                ", type='" + type + '\'' +
//                ", name='" + name + '\'' +
//                ", image=" + images +
//                ", description='" + description + '\'' +
//                ", sku=" + sku +
//                ", category='" + category + '\'' +
//                ", releaseDate='" + releaseDate + '\'' +
//                ", gtin13='" + gtin13 + '\'' +
//                ", brand=" + brand +
//                ", offers=" + offers +
//                ", aggregateRating=" + aggregateRating +
//                '}';
//    }
}
