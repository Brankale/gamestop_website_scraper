package com.github.brankale.parsers.search_results_page.search_results_parser;

import com.github.brankale.models.GamePreview;
import com.github.brankale.parsers.Utils;
import com.github.brankale.parsers.search_results.SearchResultsParser;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class SearchResultsParserTest {

    private static final String DIR = "src/test/java/com/github/brankale/parsers/search_results_page/search_results_parser/html/";

    private static final File FILE_SEARCH_RESULTS = new File(DIR + "search_results.html");

    @Test
    public void searchResults() {
        Element searchResults = Utils.createElement(FILE_SEARCH_RESULTS);
        ArrayList<GamePreview> gamePreviews = SearchResultsParser.parse(searchResults.ownerDocument());

        assertEquals("Demon's Souls", gamePreviews.get(0).getTitle());
    }

    // TODO: this test should be moved to GameStop class tests
    /**
     * Enable this test to perform lots of searches on real website.
     * It can help to find malformed HTMLs.
     */
    @Disabled
    public void randomSearchOnWebSite() {
        for (int i = 0; i < ('z'-'a'); ++i) {
            for (int j = 0; j < ('z'-'a'); ++j) {
                String game = "" + (char)('a' + i) + (char)('a' + j);
                search(game);
            }
        }
    }

    /**
     * Perform individual searches. It can be useful if some errors are found
     * with randomSearchOnWebSite().
     */
    @Disabled
    public void searchSingleGame() {
        String gameToSearch = "ai";
        search(gameToSearch);
    }

    /**
     * TODO: this method must be replaced with GameStop.search() once it's finished
     */
    private void search(String game) {
        String webPage = "https://www.gamestop.it/SearchResult/QuickSearch?q=" + game;

        try {
            System.out.println("Search: " + game);
            ArrayList<GamePreview> gamePreviews = SearchResultsParser.parse(Jsoup.connect(webPage).get());
            for (GamePreview gamePreview : gamePreviews) {
                System.out.println(gamePreview.getTitle());
            }
            System.out.println();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}