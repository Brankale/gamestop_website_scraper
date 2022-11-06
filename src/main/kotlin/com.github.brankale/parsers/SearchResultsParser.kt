package com.github.brankale.parsers

import com.github.brankale.dto.DataProduct
import com.github.brankale.models.Game
import com.github.brankale.models.GamePreview
import com.github.brankale.models.price.Price
import com.github.brankale.models.price.PriceType
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.jsoup.nodes.Element
import org.jsoup.select.Elements
import java.io.IOException
import java.lang.IllegalArgumentException
import java.lang.StringBuilder
import java.lang.reflect.Type
import java.net.URLEncoder
import java.nio.charset.StandardCharsets
import java.util.TreeMap

private const val SCHEME = "https://"
private const val DOMAIN_IT = "www.gamestop.it"
private const val SUB_DIRECTORY = "/SearchResult/QuickSearchAjax"

@Throws(IOException::class)
fun parse(searchParams: SearchParams): List<GamePreview> {
    val url: String = generateUrl(searchParams)
    val document: Document = Jsoup.connect(url).get()
    val games = getGames(document)
    return games.map { GamePreview(it) }
}

private fun getGames(document: Document) : List<Game> {
    val games = mutableListOf<Game>()

    val elements: Elements = document.getElementsByClass("searchProductTile")
    for (element in elements) {
        val dataProducts: List<DataProduct>? = extractDataProducts(element)
        val gamePageUrl = extractGameId(element)
        val imageUrl = extractGameCoverUrl(element)

        val game = buildGame(dataProducts, gamePageUrl, imageUrl)
        game?.let {
            games.add(game)
        }
    }

    return games
}

private fun buildGame(
        dataProducts: List<DataProduct>?,
        gameId: Int?,
        coverUrl: String?
): Game? {
    if (gameId != null && dataProducts != null) {
        val prices: List<Price> = dataProducts.map { dataProduct ->
            Price.Builder(
                    dataProduct.price,
                    getPriceType(dataProduct.variant)
            ).build()
        }

        dataProducts[0].apply {
            return Game.Builder(gameId)
                    .setTitle(name)
                    .setPlatform(brand)
                    .setCoverUrl(coverUrl)
                    .addPrices(prices)
                    .build()
        }
    }

    return null
}

// TODO: must handle preorders
private fun getPriceType(value: String): PriceType =
        when (value) {
            "New" -> PriceType.NEW
            "Used" -> PriceType.USED
//            "Preorder" -> PriceType.PREORDER
            "Digital" -> PriceType.DIGITAL
            else -> throw IllegalArgumentException("Unknown price type: $value")
        }

// TODO: id of the DataProduct can be used to reconstruct the url
private fun extractGameCoverUrl(element: Element): String? {
    val elements = element.getElementsByClass("searchProductImage")
    if (elements.isNotEmpty()) {
        val img = elements[0].getElementsByTag("img")
        if (img.isNotEmpty()) {
            val url = img[0].attr("data-llsrc")
            if (url.isNotEmpty())
                return url.substringBeforeLast('/')
        }
    }
    return null
}

private fun extractGameId(element: Element): Int? {
    val elements = element.getElementsByClass("searchProductImage")
    if (elements.isNotEmpty()) {
        val a = elements[0].getElementsByTag("a")
        if (a.isNotEmpty()) {
            val href = a[0].attr("href")
            if (href.isNotEmpty())
                return href.split('/')[3].toInt()
        }
    }
    return null
}

private fun extractDataProducts(element: Element): List<DataProduct>? {
    val dataProductAttrValue = element.attr("data-product")
    val type: Type = object : TypeToken<List<DataProduct>>() {}.type
    val dataProducts: List<DataProduct> = Gson().fromJson(dataProductAttrValue, type)
    return dataProducts.ifEmpty { null }
}

// TODO: implement the other query-string params
private fun generateUrl(params: SearchParams): String {
    return "$SCHEME$DOMAIN_IT$SUB_DIRECTORY${params.getQueryString()}"
}

data class SearchParams(
        private val game: String,
        private val sorting: Int = 0,
        private val order: String = "ascending",
        private val startFromPos: Int = 24,
        private val numItems: Int = 24
) {
    fun getQueryString(): String {
        val stringBuilder = StringBuilder()
        val charset = StandardCharsets.UTF_8.toString()
        stringBuilder.apply {
            append("?")
            for (entry in getParams()) {
                append("${entry.key}=${URLEncoder.encode(entry.value, charset)}&")
            }
        }
        return stringBuilder.toString()
    }

    private fun getParams(): Map<String, String> {
        val map = TreeMap<String, String>()
        map.apply {
            put("q", game)
            put("typesorting", sorting.toString())
            put("direction", order)
            put("skippos", startFromPos.toString())
            put("takenum", numItems.toString())
        }
        return map
    }
}
