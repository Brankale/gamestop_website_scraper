package main.parsers;

import com.sun.istack.internal.NotNull;
import main.models.GamePreview;
import main.models.GamePreviews;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

public class SearchResultsPageParser {

    @NotNull
    public static GamePreviews getSearchResults(@NotNull final String link) throws IOException {

        final Document root = Jsoup.connect(link).get();

        final GamePreviews gamePreviews = new GamePreviews();

        Elements items = getItems(root);
        for (Element item : items) {
            final GamePreview gamePreview = parseItem(item);
            gamePreviews.add(gamePreview);
        }

        return gamePreviews;
    }

    @NotNull
    private static Elements getItems(@NotNull Document root) {
        final Element productsList = root.getElementById("productsList");
        if (productsList != null) {
            return productsList.getElementsByClass("singleProduct");
        } else {
            throw new IllegalArgumentException("productsList not found!");
        }
    }

    @NotNull
    private static GamePreview parseItem(@NotNull final Element item) {

        final int id = getIdFromItem(item);
        final GamePreview gamePreview = new GamePreview(id);

        final String title = getTitleFromItem(item);
        final String platform = getPlatformFromItem(item);
        final String publisher = getPublisherFromItem(item);

        gamePreview.setTitle(title);
        gamePreview.setPlatform(platform);
        gamePreview.setPublisher(publisher);

        // TODO: init gamepreview parameters

        return gamePreview;
    }

    private static int getIdFromItem(@NotNull final Element item) {
        final Element prodImg = item.getElementsByClass("prodImg").first();
        final String id = prodImg.attr("href").split("/")[3];
        return Integer.parseInt(id);
    }

    @NotNull
    private static String getTitleFromItem(@NotNull final Element item) {
        return item.getElementsByTag("h3").first().text();
    }

    @NotNull
    private static String getPlatformFromItem(@NotNull final Element item) {
        return item.getElementsByTag("h4").first().textNodes().get(0).text().trim();
    }

    @NotNull
    private static String getPublisherFromItem(@NotNull final Element item) {
        return item.getElementsByTag("h4").first().getElementsByTag("strong").text();
    }

}
