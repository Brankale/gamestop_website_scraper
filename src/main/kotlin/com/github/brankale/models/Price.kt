package com.github.brankale.models

import java.lang.IllegalArgumentException
import java.math.BigDecimal

data class Price(
        val currency: Currency,
        val price: BigDecimal,
        val condition: ItemCondition,
        val availability: Availability
)

enum class Currency(val symbol: String) {
    EUR("€");

    companion object {
        fun fromString(string: String): Currency {
            return when (string) {
                "EUR" -> EUR
                else -> throw IllegalArgumentException("Unknown currency: $string")
            }
        }
    }
}

enum class ItemCondition {
    NEW,
    USED;

    companion object {
        fun fromString(string: String): ItemCondition {
            return when (string) {
                "https://schema.org/NewCondition" -> NEW
                "https://schema.org/UsedCondition" -> USED
                else -> throw IllegalArgumentException("Unknown item condition: $string")
            }
        }
    }
}

enum class Availability {
    PREORDER,
    IN_STOCK,
    OUT_OF_STOCK;

    companion object {
        fun fromString(string: String): Availability {
            return when (string) {
                "https://schema.org/PreOrder" -> PREORDER
                "https://schema.org/InStock" -> IN_STOCK
                "https://schema.org/OutOfStock" -> OUT_OF_STOCK
                else -> throw IllegalArgumentException("Unknown availability: $string")
            }
        }
    }
}
