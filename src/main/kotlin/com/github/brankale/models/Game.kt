package com.github.brankale.models

// TODO - list of missing params:
//        platform, shippingDetails, officialSite, numPlayers, pegi, validForPromo, available Promotions, gallery images
class Game private constructor(
        val id: Int,
        val name: String?,
        val description: String?,
        private val sku: Long?,
        // for items with used condition is not always true that imageSku = sku - 1
        private val imageSku: Long?,
        val category: String?,
        val releaseDate: String?,
        val publisher: String?,
        val prices: List<Price>?
) {
    companion object {
        private const val imageUrlPrefix = "https://static-it.gamestop.it/images/products"
    }

    data class Builder(val id: Int) {

        private var type: String? = null
        private var name: String? = null
        private var description: String? = null
        private var sku: Long? = null
        private var imageSku: Long? = null
        private var category: String? = null
        private var releaseDate: String? = null
        private var publisher: String? = null
        private var prices: MutableList<Price>? = null

        fun type(type: String?) = apply { this.type = type }
        fun name(name: String?) = apply { this.name = name }
        fun description(description: String?) = apply { this.description = description }
        fun sku(sku: Long?) = apply { this.sku = sku }
        fun imageSku(imageSku: Long?) = apply { this.imageSku = imageSku }
        fun category(category: String?) = apply { this.category = category }
        fun releaseDate(releaseDate: String?) = apply { this.releaseDate = releaseDate }
        fun publisher(publisher: String?) = apply { this.publisher = publisher }
        fun prices(prices: List<Price>) = apply { this.prices = mutableListOf<Price>().apply { addAll(prices) } }
        fun addPrice(price: Price) = apply {
            if (prices == null)
                prices = mutableListOf()
            prices?.add(price)
        }

        fun build() = Game(
                id = id,
                name = name,
                description = description,
                sku = sku,
                imageSku = imageSku,
                category = category,
                releaseDate = releaseDate,
                publisher = publisher,
                prices = prices
        )
    }

    fun getImageUrl(quality: ImageQuality = ImageQuality.MAX) =
            "$imageUrlPrefix/$imageSku/${quality.suffix}"

    override fun toString(): String {
        return "Game(id=$id, name=$name, sku=$sku, category=$category, releaseDate=$releaseDate, publisher=$publisher, prices=$prices, description=$description,)"
    }
}

enum class ImageQuality(val suffix: String) {
    MAX("3max.jpg"),
    MED("2med.jpg"),
    MIN("1min.jpg")
}