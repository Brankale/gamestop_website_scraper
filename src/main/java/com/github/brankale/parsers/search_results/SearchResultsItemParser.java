package com.github.brankale.parsers.search_results;

import com.github.brankale.models.Game;
import com.github.brankale.models.GamePreview;
import org.jsoup.nodes.Element;

public final class SearchResultsItemParser {

    private SearchResultsItemParser() {
        // prevent instantiation
    }

    /**
     * Parses an item in a search results html page and returns
     * a GamePreview representing the item
     *
     * @param element with root tag <div id="product_x" class="searchProductTile searchTileLayout">
     * @return a GamePreview object
     */
    public static GamePreview parse(final Element element) {
        Element mobileSearchProductInfo = element.getElementsByClass("mobileSearchProductInfo").first();
        Element searchProductImage = element.getElementsByClass("searchProductImage").first();
        Element searchTilePriceDesktop = element.getElementsByClass("searchTilePriceDesktop").first();

        Game game = new Game.Builder(parseId(mobileSearchProductInfo))
                .setTitle(parseTitle(mobileSearchProductInfo))
                .setPlatform(parsePlatform(mobileSearchProductInfo))
                .setCoverUrl(parseCoverUrl(searchProductImage))
                .addPrices(SearchResultsPriceParser.parse(searchTilePriceDesktop))
                .build();

        return new GamePreview(game);
    }

    /**
     * @param element with root tag <div class="mobileSearchProductInfo">
     * @return the id of the game
     */
    private static int parseId(final Element element) {
        Element a = element.getElementsByTag("a").first();
        String id = a.attr("href").split("/")[3];
        return Integer.parseInt(id);
    }

    /**
     * @param element with root tag <div class="mobileSearchProductInfo">
     * @return the title of the game
     */
    private static String parseTitle(final Element element) {
        return element.getElementsByTag("h3").first().text().trim();
    }

    /**
     * @param element with root tag <div class="mobileSearchProductInfo">
     * @return the platform of the game
     */
    private static String parsePlatform(final Element element) {
        return element.getElementsByTag("h4").first().text().trim();
    }

    /**
     * @param element with root tag <div class="searchProductImage">
     * @return the cover url of the game
     */
    private static String parseCoverUrl(final Element element) {
        return element.getElementsByTag("img").attr("data-llsrc").trim();
    }

}
