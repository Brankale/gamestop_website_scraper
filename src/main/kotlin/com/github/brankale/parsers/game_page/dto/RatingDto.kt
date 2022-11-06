package com.github.brankale.parsers.game_page.dto

import com.google.gson.annotations.SerializedName

data class RatingDto(
        @SerializedName("@type") val type: String,
        @SerializedName("ratingValue") val ratingValue: Float,
        @SerializedName("reviewCount") val reviewCount: Int
)
