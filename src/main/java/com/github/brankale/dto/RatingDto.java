package com.github.brankale.dto;

public record RatingDto(
        String type,
        float ratingValue,
        int reviewCount
) {
    @Override
    public String toString() {
        return "RatingDto{" +
                "type='" + type + '\'' +
                ", ratingValue=" + ratingValue +
                ", reviewCount=" + reviewCount +
                '}';
    }
}
