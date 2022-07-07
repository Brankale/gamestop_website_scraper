package com.github.brankale;

import com.github.brankale.models.GamePreview;
import com.github.brankale.parsers.search_results.SearchResultsParser;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.util.ArrayList;

public class Gamestop {

    private String website = "www.gamestop";

    public enum Country {
        AUSTRIA,
        ITALY,
        SWITZERLAND,
        GERMANY,
        IRELAND
    }

    public Gamestop(Country region) {
        switch (region) {
            case AUSTRIA:       website += ".at"; break;
            case ITALY:         website += ".it"; break;
            case SWITZERLAND:   website += ".ch"; break;
            case GERMANY:       website += ".de"; break;
            case IRELAND:       website += ".ie"; break;
        }
    }

    public ArrayList<GamePreview> search(String searched) {
        // TODO: throw a custom exception on IOException
        try {
            Document html = Jsoup.connect(getSearchQuery(searched)).get();
            return SearchResultsParser.parse(html);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    private String getSearchQuery(String searched) {
        return website + "?q=" + searched;
    }

    public void findById() {
        // TODO ("to implement")
    }

}
