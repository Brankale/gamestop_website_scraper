package com.github.brankale.parsers.game_page.dto

import com.google.gson.annotations.SerializedName

data class SellerDto(
        @SerializedName("@type") val type: String,
        @SerializedName("name") val name: String
)
