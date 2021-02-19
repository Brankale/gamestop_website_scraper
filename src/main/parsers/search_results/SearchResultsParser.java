package main.parsers.search_results;

import com.sun.istack.internal.NotNull;
import main.models.GamePreview;
import main.models.GamePreviews;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

// TODO: add documentation
public class SearchResultsParser {

    @NotNull
    public static GamePreviews parse(Document root) {
        Elements items = getItems(root);
        GamePreviews gamePreviews = new GamePreviews();
        for (Element item : items) {
            GamePreview gamePreview = SearchResultsItemParser.parse(item);
            gamePreviews.add(gamePreview);
        }
        return gamePreviews;
    }

    @NotNull
    private static Elements getItems(@NotNull final Document root) {
        Element productsList = root.getElementById("productsList");
        if (productsList == null) {
            throw new InvalidHtmlException(root);
        }
        return productsList.getElementsByClass("singleProduct");
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
