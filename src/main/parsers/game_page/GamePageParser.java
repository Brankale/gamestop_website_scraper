package main.parsers.game_page;

import main.models.Game;
import main.models.price.Prices;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.util.List;

public class GamePageParser {

    private GamePageParser() {
        // prevent instantiation
    }

    public static Game getGame(Document gamePage) {
        Game game = new Game(getId(gamePage));

        Element prodTitle = gamePage.getElementsByClass("prodTitle").first();
        game.setTitle(getTitle(prodTitle));

        return game;
    }

    /**
     * @param element <html> tag
     * @return the ID of the game
     */
    private static int getId(Element element) {
        String url = findUrlWithId(element);
        return getGameIdFromUrl(url);
    }

    /**
     * @param element <html> tag
     * @return the url containing the ID of the game
     */
    private static String findUrlWithId(Element element) {
        for (Element el : element.getElementsByTag("link")){
            if (el.attr("rel").equals("canonical")) {
                return el.attr("href");
            }
        }
        return "";
    }

    private static int getGameIdFromUrl(String url) {
        String id = url.substring(findIdStringStartIndex(url));
        return Integer.parseInt(id);
    }

    private static int findIdStringStartIndex(String url) {
        int i = url.length() - 1;
        while (url.charAt(i) != '/') {
            --i;
        }
        return i+1;
    }

    /**
     * @param element <div class="prodTitle"> tag
     * @return the title of the game
     */
    private static String getTitle(Element element) {
        return element.getElementsByTag("span").first().text();
    }

    private static Prices getPrices(Element element) {
        // TODO: to implement
        return null;
    }

    private static String getPlatform(Element element) {
        // TODO: to implement
        return null;
    }

    private static String getPublisher(Element element) {
        // TODO: to implement
        return null;
    }

    private static String getReleaseDate(Element element) {
        // TODO: to implement
        return null;
    }

    private static String getCoverUrl(Element element) {
        // TODO: to implement
        return null;
    }

    private static List<String> getGallery(Element element) {
        // TODO: to implement
        return null;
    }

    private static List<Game.Pegi> getPegi(Element element) {
        // TODO: to implement
        return null;
    }

    private static List<String> getGenres(Element element) {
        // TODO: to implement
        return null;
    }

    private static String getOfficialSite(Element element) {
        // TODO: to implement
        return null;
    }

    private static String getPlayers(Element element) {
        // TODO: to implement
        return null;
    }

    private static boolean isValidForPromotions(Element element) {
        // TODO: to implement
        return false;
    }

    private static String getDescription(Element element) {
        // TODO: to implement
        return null;
    }

}