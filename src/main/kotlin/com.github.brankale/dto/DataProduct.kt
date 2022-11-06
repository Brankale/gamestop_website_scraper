package com.github.brankale.dto

import com.google.gson.annotations.SerializedName
import java.math.BigDecimal

data class DataProduct(
        @SerializedName("id") val id: String,
        @SerializedName("name") val name: String,
        @SerializedName("price") val price: BigDecimal,
        @SerializedName("brand") val brand: String,
        @SerializedName("category") val category: String,
        @SerializedName("variant") val variant: String
)