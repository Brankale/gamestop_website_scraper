package test.parsers.search_results;

import main.models.GamePreviews;
import main.parsers.search_results.SearchResultsParser;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class SearchResultsParserTest {

    private static final String DIR = "src/test/parsers/search_results/htmls/";

    private static final File FILE_SEARCH_RESULTS = new File(DIR + "search_results.html");

    private static Element createElement(File html) {
        try {
            return Jsoup.parse(html, "UTF-8").body().child(0);
        } catch (IOException e) {
            e.printStackTrace();
            return new Element("div");
        }
    }

    @Test
    public void searchResults() {
        Element searchResults = createElement(FILE_SEARCH_RESULTS);
        GamePreviews gamePreviews = SearchResultsParser.parse(searchResults.ownerDocument());

        assertEquals("Persona® 5 Strikers", gamePreviews.get(0).getTitle());
        assertEquals("Persona® 5 Strikers", gamePreviews.get(1).getTitle());
        assertEquals("Persona 5 - Steelbook Launch Edition", gamePreviews.get(2).getTitle());
        assertEquals("Funko Pop! - Queen (PlayStation) Esclusiva GameStop", gamePreviews.get(3).getTitle());
        assertEquals("Persona 5 Royal - Phantom Thieves Edition", gamePreviews.get(4).getTitle());
        assertEquals("Resident Evil 6 HD", gamePreviews.get(5).getTitle());
        assertEquals("Resident Evil 5 HD", gamePreviews.get(6).getTitle());
        assertEquals("Resident Evil 5 HD", gamePreviews.get(7).getTitle());
        assertEquals("Resident Evil 4 HD", gamePreviews.get(8).getTitle());
        assertEquals("Persona 5 Royal", gamePreviews.get(9).getTitle());
    }

}