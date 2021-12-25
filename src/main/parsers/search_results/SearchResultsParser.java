package main.parsers.search_results;

import com.sun.istack.internal.NotNull;
import main.models.GamePreview;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;

/**
 * Given a valid HTML representing a GameStop search
 * results page, it returns the results found.
 * A valid HTML has one tag with id = "productsList".
 */
public final class SearchResultsParser {

    private SearchResultsParser() {
        // prevent instantiation
    }

    /**
     * @param root org.jsoup.nodes.Document representing
     *             the HTML search results page
     * @return GamePreviews
     */
    @NotNull
    public static ArrayList<GamePreview> parse(@NotNull Document root) {
        Elements items = getItems(root);
        ArrayList<GamePreview> gamePreviews = new ArrayList<>();
        for (Element item : items) {
            GamePreview gamePreview = SearchResultsItemParser.parse(item);
            gamePreviews.add(gamePreview);
        }
        return gamePreviews;
    }

    /**
     * @param root org.jsoup.nodes.Document representing
     *             the HTML search results page
     * @return Elements representing search results
     * @throws InvalidHtmlException if tag with id = "productsList" is missing
     */
    @NotNull
    private static Elements getItems(@NotNull Document root) {
        Element productsList = root.getElementById("productsList");
        if (productsList == null) {
            throw new InvalidHtmlException(root);
        }
        return productsList.getElementsByClass("searchProductTile searchTileLayout");
    }

    public static class InvalidHtmlException extends RuntimeException {
        private final Document invalidHtml;

        public InvalidHtmlException(Document invalidHtml) {
            this.invalidHtml = invalidHtml;
        }

        @Override
        public String toString() {
            return "Invalid html:\n" + invalidHtml;
        }
    }

}
