package com.github.brankale.parsers.game_page.dto

import com.google.gson.annotations.SerializedName
import java.math.BigDecimal

data class OffersDto(
        @SerializedName("@type") val type: String,
        @SerializedName("url") val url: String,
        @SerializedName("priceCurrency") val priceCurrency: String,
        @SerializedName("price") val price: BigDecimal,
        @SerializedName("itemCondition") val itemCondition: String,
        @SerializedName("availability") val availability: String,
        @SerializedName("seller") val seller: SellerDto
)
