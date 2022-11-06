package com.github.brankale.parsers.game_page

import com.github.brankale.models.Game
import com.github.brankale.parsers.game_page.dto.GamePageDto
import com.github.brankale.parsers.search_results.parse
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.jsoup.nodes.Element
import java.io.IOException
import java.lang.reflect.Type

fun main() {
    val results = parse(138291)
    println(results)
}


private const val SCHEME = "https://"
private const val DOMAIN_IT = "www.gamestop.it"
private const val SUB_DIRECTORY = "/Platform/Games/"

@Throws(IOException::class)
fun parse(gameId: Int): List<Game?>? {
    val url = "$SCHEME$DOMAIN_IT$SUB_DIRECTORY$gameId"
    val document = Jsoup.connect(url).get()
    val script = findScriptWithJsonInfo(document)


    val type: Type = object : TypeToken<List<GamePageDto>>() {}.type

    val html = script?.html()
    val gamePageDtos: List<GamePageDto?> = Gson().fromJson(html, type)

    println(gamePageDtos)
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