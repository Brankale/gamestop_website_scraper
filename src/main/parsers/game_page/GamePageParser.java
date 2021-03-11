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
        return new Game(getId(gamePage));
    }

    private static int getId(Element element) {
        // TODO: to implement
        return -1;
    }

    private static String getTitle(Element element) {
        // TODO: to implement
        return null;
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