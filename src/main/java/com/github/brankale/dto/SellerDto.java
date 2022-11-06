package com.github.brankale.dto;

import com.google.gson.annotations.SerializedName;

public record SellerDto(
        @SerializedName("@type") String type,
        String name
) {
    @Override
    public String toString() {
        return "SellerDto{" +
                "type='" + type + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
