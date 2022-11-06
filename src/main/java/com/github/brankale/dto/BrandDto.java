package com.github.brankale.dto;

import com.google.gson.annotations.SerializedName;

public record BrandDto(
        @SerializedName("@type") String type,
        String name
) {
    @Override
    public String toString() {
        return "BrandDto{" +
                "type='" + type + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
