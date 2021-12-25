package test.parsers.search_results_page.item_parser;

import main.models.GamePreview;
import main.parsers.search_results.SearchResultsItemParser;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import test.parsers.Utils;

import java.io.File;

import static org.junit.jupiter.api.Assertions.*;

class SearchResultsItemParserTest {

    private static final String DIR = "src/test/parsers/search_results_page/item_parser/html/";
    private static final File EXAMPLE_ITEM = new File(DIR + "example_item.html");

    private static GamePreview gamePreview;

    @BeforeAll
    public static void parseItem() {
        gamePreview = SearchResultsItemParser.parse(Utils.createElement(EXAMPLE_ITEM));
    }

    @Test
    public void parseId() {
        int id = 131445;
        assertEquals(id, gamePreview.getId());
    }

    @Test
    public void parseTitle() {
        String title = "Demon's Souls";
        assertEquals(title, gamePreview.getTitle());
    }

    @Test
    public void parsePlatform() {
        String platform = "PS5";
        assertEquals(platform, gamePreview.getPlatform());
    }

    @Test
    public void parseCoverUrl() {
        String coverUrl = "https://static-it.gamestop.it/images/products/298910/2med.jpg";
        assertEquals(coverUrl, gamePreview.getCoverUrl());
    }

}