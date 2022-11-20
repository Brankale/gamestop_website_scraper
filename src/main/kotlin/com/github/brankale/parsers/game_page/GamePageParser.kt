package com.github.brankale.parsers.game_page

import com.github.brankale.models.*
import com.github.brankale.parsers.game_page.dto.GamePageDto
import com.github.brankale.parsers.game_page.dto.OffersDto
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.jsoup.nodes.Element
import java.io.IOException
import java.lang.IllegalArgumentException
import java.lang.reflect.Type

// Edge cases:
// - 22408 (has numPlayers)

class GamePageParser {

    companion object {

        private const val SCHEME = "https://"
        private const val DOMAIN_IT = "www.gamestop.it"
        private const val SUB_DIRECTORY = "/Platform/Games/"

        @Throws(IOException::class)
        fun parse(gameId: Int): Game? {
            val url = "$SCHEME$DOMAIN_IT$SUB_DIRECTORY$gameId"
            val document = Jsoup.connect(url).get()
            return getGame(document, gameId)
        }

        private fun getGame(document: Document, gameId: Int): Game? {
            val script = findScriptWithJsonInfo(document)
            script?.let {
                val type: Type = object : TypeToken<List<GamePageDto>>() {}.type
                val gamePageDtos: List<GamePageDto> = Gson().fromJson(it.html(), type)
                return buildGame(gameId, gamePageDtos)
            }
            return null
        }

        private fun findScriptWithJsonInfo(document: Document): Element? {
            val scripts = document.getElementsByTag("script")
            for (script in scripts)
                if (script.attr("type") == "application/ld+json") {
                    println(script)
                    return script
                }
            return null
        }

        private fun buildGame(gameId: Int, dtos: List<GamePageDto>): Game? {
            if (dtos.isNotEmpty()) {
                val first = dtos[0]

                val offers: List<OffersDto> = mutableListOf<OffersDto>().apply {
                    dtos.map { dto ->
                        this.addAll(dto.offers)
                    }
                }

                first.let { dto ->
                    return Game.Builder(gameId)
                            .name(dto.name)
                            .type(dto.type)
                            .description(dto.description)
                            .sku(dto.sku)
                            .imageSku(getImageSku(dto.images[0]))
                            .category(dto.category)
                            .releaseDate(dto.releaseDate)
                            .publisher(dto.brand?.name)
                            .prices(buildPrice(offers))
                            .build()
                }
            }
            return null
        }

        private fun getImageSku(url: String) = url.split("/")[5].toLong()

        private fun buildPrice(offers: List<OffersDto>): List<Price> {
            return offers.map { offer ->
                Price(
                        currency = Currency.fromString(offer.priceCurrency),
                        price = offer.price,
                        condition = ItemCondition.fromString(offer.itemCondition),
                        availability = Availability.fromString(offer.availability)
                )
            }
        }

    }

}