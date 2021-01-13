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

    private int id;
    private String title;
    private String platform;
    private String publisher;
    private String coverUrl;

    public GamePreview parse(@NotNull Element root) {
        initAttributes(root);
        return createGamePreview();
    }

    private void initAttributes(Element element) {
        id = parseId(element);
        title = parseTitle(element);
        platform = parsePlatform(element);
        publisher = parsePublisher(element);
        coverUrl = parseCoverUrl(element);
    }

    private GamePreview createGamePreview() {
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
     * @param element with root tag <div class="singleProdInfo">
     * @return the id of the game
     */
    private int parseId(@NotNull final Element element) {
        final Element h3 = element.getElementsByClass("singleProdInfo").first()
                .getElementsByTag("h3").first();
        final String id = h3.child(0).attr("href").split("/")[3];
        return Integer.parseInt(id);
    }

    /**
     * @param element with root tag <div class="singleProdInfo">
     * @return the title of the game
     */
    private String parseTitle(@NotNull final Element element) {
        final Element h3 = element.getElementsByClass("singleProdInfo").first()
                .getElementsByTag("h3").first();
        return h3.child(0).text().trim();
    }

    /**
     * @param element with root tag <div class="singleProdInfo">
     * @return the platform of the game
     */
    private String parsePlatform(@NotNull final Element element) {
        final Element h4 = element.getElementsByClass("singleProdInfo").first()
                .getElementsByTag("h4").first();
        return h4.textNodes().get(0).text().trim();
    }

    /**
     * @param element with root tag <div class="singleProdInfo">
     * @return the publisher of the game
     */
    private String parsePublisher(@NotNull final Element element) {
        final Element h4 = element.getElementsByClass("singleProdInfo").first()
                .getElementsByTag("h4").first();
        return h4.getElementsByTag("strong").text().trim();
    }

    /**
     * @param element with root tag <div class="singleProdInfo">
     * @return the cover url of the game
     */
    private String parseCoverUrl(@NotNull final Element element) {
        final Element a = element.getElementsByClass("prodImg").first();
        return a.child(0).attr("data-llsrc");
    }

}
