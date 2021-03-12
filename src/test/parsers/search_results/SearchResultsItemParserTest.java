package test.parsers.search_results;

import main.models.GamePreview;
import main.parsers.search_results.SearchResultsItemParser;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import test.parsers.Utils;

import java.io.File;

import static org.junit.jupiter.api.Assertions.*;

class SearchResultsItemParserTest {

    private static final String DIR = "src/test/parsers/search_results/htmls/items/";
    private static final File EXAMPLE_ITEM = new File(DIR + "example_item.html");

    private static GamePreview gamePreview;

    @BeforeAll
    public static void parseItem() {
        gamePreview = SearchResultsItemParser.parse(Utils.createElement(EXAMPLE_ITEM));
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