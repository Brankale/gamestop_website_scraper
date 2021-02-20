package main.parsers.search_results;

import com.sun.istack.internal.NotNull;
import main.models.GamePreview;
import org.jsoup.nodes.Element;

public final class SearchResultsItemParser {

    private SearchResultsItemParser() {
        // prevent instantiation
    }

    /**
     * Parses an item in a search results html page and returns
     * a GamePreview representing the item
     *
     * @param element with root tag <div id="product_x" class="singleProduct">.
     *                (product_x: x is a number)
     * @return a GamePreview object
     */
    public static GamePreview parse(@NotNull final Element element) {
        try {
            return initGamePreview(element);
        } catch (Exception e) {
            throw new ItemParsingException(e);
        }
    }

    /**
     * HTML can change so instead of throwing different RuntimeExceptions
     * for every information parsed, I just throw Exception
     *
     * @param element with root tag <div class="singleProduct">
     * @return a GamePreview object
     * @throws Exception if an error occurred while parsing
     */
    private static GamePreview initGamePreview(@NotNull final Element element) throws Exception {
        Element singleProdInfo = element.getElementsByClass("singleProdInfo").first();

        GamePreview gamePreview = new GamePreview(parseId(singleProdInfo));
        gamePreview.setTitle(parseTitle(singleProdInfo));
        gamePreview.setPlatform(parsePlatform(singleProdInfo));
        gamePreview.setPublisher(parsePublisher(singleProdInfo));
        gamePreview.setCoverUrl(parseCoverUrl(element));
        return gamePreview;
    }

    /**
     * @param element with root tag <div class="singleProdInfo">
     * @return the id of the game
     */
    private static int parseId(@NotNull final Element element) {
        Element h3 = element.getElementsByTag("h3").first();
        String id = h3.child(0).attr("href").split("/")[3];
        return Integer.parseInt(id);
    }

    /**
     * @param element with root tag <div class="singleProdInfo">
     * @return the title of the game
     */
    private static String parseTitle(@NotNull final Element element) {
        Element h3 = element.getElementsByTag("h3").first();
        return h3.child(0).text().trim();
    }

    /**
     * @param element with root tag <div class="singleProdInfo">
     * @return the platform of the game
     */
    private static String parsePlatform(@NotNull final Element element) {
        Element h4 = element.getElementsByTag("h4").first();
        return h4.textNodes().get(0).text().trim();
    }

    /**
     * @param element with root tag <div class="singleProdInfo">
     * @return the publisher of the game
     */
    private static String parsePublisher(@NotNull final Element element) {
        Element h4 = element.getElementsByTag("h4").first();
        return h4.getElementsByTag("strong").text().trim();
    }

    /**
     * @param element with root tag <div class="singleProdInfo">
     * @return the cover url of the game
     */
    private static String parseCoverUrl(@NotNull final Element element) {
        Element a = element.getElementsByClass("prodImg").first();
        return a.child(0).attr("data-llsrc");
    }

    public static class ItemParsingException extends RuntimeException {
        private final Exception exception;

        public ItemParsingException(Exception e) {
            exception = e;
        }

        @Override
        public String toString() {
            return "CannotParsingException {" +
                    "exception = " + exception.getMessage() +
                    '}';
        }
    }

}
