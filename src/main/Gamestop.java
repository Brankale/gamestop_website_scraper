package main;

import main.models.GamePreviews;
import main.parsers.search_results.SearchResultsParser;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

public class Gamestop {

    private String website = "www.gamestop";

    public enum Country {
        AUSTRIA,
        ITALY,
        SWITZERLAND,
        DENMARK,
        GERMANY,
        NORWAY,
        SWEDEN,
        FINLAND,
        IRELAND
    }

    public Gamestop(Country region) {
        switch (region) {
            case AUSTRIA:       website += ".at"; break;
            case ITALY:         website += ".it"; break;
            case SWITZERLAND:   website += ".ch"; break;
            case DENMARK:       website += ".dk"; break;
            case GERMANY:       website += ".de"; break;
            case NORWAY:        website += ".no"; break;
            case SWEDEN:        website += ".se"; break;
            case FINLAND:       website += ".fi"; break;
            case IRELAND:       website += ".ie"; break;
        }
    }

    public GamePreviews search(String searched) {
        // TODO: throw a custom exception on IOException
        try {
            Document html = Jsoup.connect(getSearchQuery(searched)).get();
            return SearchResultsParser.parse(html);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new GamePreviews();
    }

    private String getSearchQuery(String searched) {
        return website + "?q=" + searched;
    }

    public void findById() {
        // TODO ("to implement")
    }

}
