package main.parsers;

import com.sun.istack.internal.NotNull;
import main.models.GamePreview;
import org.jsoup.nodes.Element;

import java.net.MalformedURLException;

/**
 * Parses an Element with root tag: <div id="product_x" class="singleProduct">
 * (where x is a number) and returns a GamePreview.
 * HTML structure examples can be found on the GameStop website
 * or in the tests for these methods.
 */
public class SearchResultsItemParser {

    public static GamePreview parse(@NotNull final Element element) {
        GamePreview gamePreview = new GamePreview(parseId(element));
        gamePreview.setTitle(parseTitle(element));
        gamePreview.setPlatform(parsePlatform(element));
        gamePreview.setPublisher(parsePublisher(element));
        try {
            gamePreview.setCoverUrl(parseCoverUrl(element));
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return gamePreview;
    }

    /**
     * @param element with root tag <div class="singleProdInfo">
     * @return the id of the game
     */
    private static int parseId(@NotNull final Element element) {
        Element h3 = element.getElementsByClass("singleProdInfo").first()
                .getElementsByTag("h3").first();
        String id = h3.child(0).attr("href").split("/")[3];
        return Integer.parseInt(id);
    }

    /**
     * @param element with root tag <div class="singleProdInfo">
     * @return the title of the game
     */
    private static String parseTitle(@NotNull final Element element) {
        Element h3 = element.getElementsByClass("singleProdInfo").first()
                .getElementsByTag("h3").first();
        return h3.child(0).text().trim();
    }

    /**
     * @param element with root tag <div class="singleProdInfo">
     * @return the platform of the game
     */
    private static String parsePlatform(@NotNull final Element element) {
        Element h4 = element.getElementsByClass("singleProdInfo").first()
                .getElementsByTag("h4").first();
        return h4.textNodes().get(0).text().trim();
    }

    /**
     * @param element with root tag <div class="singleProdInfo">
     * @return the publisher of the game
     */
    private static String parsePublisher(@NotNull final Element element) {
        Element h4 = element.getElementsByClass("singleProdInfo").first()
                .getElementsByTag("h4").first();
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

}
