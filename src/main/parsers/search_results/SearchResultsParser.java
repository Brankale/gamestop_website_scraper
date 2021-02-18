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
        Elements items = getItems(root.body());
        GamePreviews gamePreviews = new GamePreviews();
        for (Element item : items) {
            GamePreview gamePreview = SearchResultsItemParser.parse(item);
            gamePreviews.add(gamePreview);
        }
        return gamePreviews;
    }

    @NotNull
    private static Elements getItems(@NotNull final Element root) {
        // TODO: throw custom exception if "productsList" is not present
        return root.getElementById("productsList").getElementsByClass("singleProduct");
    }

}
