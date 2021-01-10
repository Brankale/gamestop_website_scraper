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

    public GamePreview parse(@NotNull Element root) {
        int id = parseId(root.getElementsByClass("singleProdInfo").first().getElementsByTag("h3").first());
        String title = parseTitle(root.getElementsByClass("singleProdInfo").first().getElementsByTag("h3").first());
        String platform = parsePlatform(root.getElementsByClass("singleProdInfo").first().getElementsByTag("h4").first());
        String publisher = parsePublisher(root.getElementsByClass("singleProdInfo").first().getElementsByTag("h4").first());
        String coverUrl = parseCoverUrl(root.getElementsByClass("prodImg").first());

        GamePreview gamePreview = new GamePreview(id);
        gamePreview.setTitle(title);
        gamePreview.setPlatform(platform);
        gamePreview.setPublisher(publisher);
        try {
            gamePreview.setCoverUrl(coverUrl);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return gamePreview;
    }

    /**
     * Returns the id of the game given the h3 tag
     * @param rootTag h3 tag inside <div class="singleProdInfo">
     * @return the id of the game
     */
    private int parseId(@NotNull final Element rootTag) {
        final String id = rootTag.child(0).attr("href").split("/")[3];
        return Integer.parseInt(id);
    }

    /**
     * Returns the title of the game given the h3 tag
     * @param rootTag h3 tag inside <div class="singleProdInfo">
     * @return the title of the game
     */
    private String parseTitle(@NotNull final Element rootTag) {
        return rootTag.child(0).text().trim();
    }

    /**
     * Returns the platform of the game given the h4 tag
     * @param rootTag h4 tag inside <div class="singleProdInfo">
     * @return the platform of the game
     */
    private String parsePlatform(@NotNull final Element rootTag) {
        return rootTag.textNodes().get(0).text().trim();
    }

    /**
     * Returns the publisher of the game given the h4 tag
     * @param rootTag h4 tag inside <div class="singleProdInfo">
     * @return the publisher of the game
     */
    private String parsePublisher(@NotNull final Element rootTag) {
        return rootTag.getElementsByTag("strong").text().trim();
    }

    /**
     * Returns the cover url of the game given the "a" tag
     * @param rootTag "a" tag with class="prodImg" inside <div class="singleProduct">
     * @return the cover url of the game
     */
    private String parseCoverUrl(@NotNull final Element rootTag) {
        return rootTag.child(0).attr("data-llsrc");
    }

}
