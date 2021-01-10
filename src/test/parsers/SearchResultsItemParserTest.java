package test.parsers;

import main.models.GamePreview;
import main.parsers.SearchResultsItemParser;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class SearchResultsItemParserTest {

    private static GamePreview gamePreview;

    @BeforeAll
    public static void parseItem() {
        SearchResultsItemParser parser = new SearchResultsItemParser();
        File html = new File("src/test/parsers/html/search_results_item.html");
        Element root = createElement(html);
        gamePreview = parser.parse(root);
    }

    private static Element createElement(File html) {
        try {
            return Jsoup.parse(html, "UTF-8").body().child(0);
        } catch (IOException e) {
            e.printStackTrace();
            return new Element("div");
        }
    }

    @Test
    public void parseId() {
        int id = 133354;
        assertEquals(id, gamePreview.getId());
    }

    @Test
    public void parseTitle() {
        String title = "Persona® 5 Strikers";
        assertEquals(title, gamePreview.getTitle());
    }

    @Test
    public void parsePlatform() {
        String platform = "Switch";
        assertEquals(platform, gamePreview.getPlatform());
    }

    @Test
    public void parsePublisher() {
        String publisher = "Atlus";
        assertEquals(publisher, gamePreview.getPublisher());
    }

    @Test
    public void parseCoverUrl() {
        String coverUrl = "https://static-it.gamestop.it/images/products/302017/2med.jpg";
        assertEquals(coverUrl, gamePreview.getCoverUrl());
    }

}