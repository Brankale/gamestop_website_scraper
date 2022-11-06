package com.github.brankale.parsers.game_page.dto

import com.google.gson.annotations.SerializedName

data class GamePageDto(
        @SerializedName("@context") val context: String,
        @SerializedName("@type") val type: String,
        @SerializedName("name") val name: String,
        @SerializedName("image") val images: List<String>,
        @SerializedName("description") val description: String,
        @SerializedName("sku") val sku: Long,
        @SerializedName("category") val category: String,
        @SerializedName("releaseDate") val releaseDate: String,
        @SerializedName("gtin13") val gtin13: String,
        @SerializedName("brand") val brand: BrandDto,
        @SerializedName("offers")  val offers: List<OffersDto>,
        @SerializedName("aggregateRating")  val aggregateRating: RatingDto
)
