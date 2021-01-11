package test.parsers;

import main.models.price.Price;
import main.models.price.PriceType;
import main.parsers.SearchResultsItemPriceParser;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class SearchResultsItemPriceParserTest {

    private static final String DIR = "src/test/parsers/htmls/search_results/prices/";
    private static final File FILE_PRICE_NEW = new File(DIR + "price_new.html");
    private static final File FILE_PRICE_USED = new File(DIR + "price_used.html");
    private static final File FILE_PRICE_PREORDER = new File(DIR + "price_preorder.html");
    private static final File FILE_PRICE_DIGITAL = new File(DIR + "price_digital.html");
    private static final File FILE_PRICE_UNKNOWN = new File(DIR + "unknown_price_type.html");

    private static Price priceNew;
    private static Price priceUsed;
    private static Price pricePreorder;
    private static Price priceDigital;

    private static Element createElement(File html) {
        try {
            return Jsoup.parse(html, "UTF-8").body().child(0);
        } catch (IOException e) {
            e.printStackTrace();
            return new Element("div");
        }
    }

    @BeforeAll
    public static void init() {
        SearchResultsItemPriceParser priceParser = new SearchResultsItemPriceParser();
        priceNew = priceParser.parse(createElement(FILE_PRICE_NEW));
        priceUsed = priceParser.parse(createElement(FILE_PRICE_USED));
        pricePreorder = priceParser.parse(createElement(FILE_PRICE_PREORDER));
        priceDigital = priceParser.parse(createElement(FILE_PRICE_DIGITAL));
    }

    @Test
    public void priceTypeIsNew() {
        assertEquals(priceNew.getType(), PriceType.NEW);
    }

    @Test
    public void priceTypeIsUsed() {
        assertEquals(priceUsed.getType(), PriceType.USED);
    }

    @Test
    public void priceTypeIsPreorder() {
        assertEquals(pricePreorder.getType(), PriceType.PREORDER);
    }

    @Test
    public void priceTypeIsDigital() {
        assertEquals(priceDigital.getType(), PriceType.DIGITAL);
    }

    @Test
    public void throwsExceptionOnInvalidPriceType() {
        assertThrows(SearchResultsItemPriceParser.UnknownPriceTypeException.class, () -> {
            SearchResultsItemPriceParser priceParser = new SearchResultsItemPriceParser();
            priceParser.parse(createElement(FILE_PRICE_UNKNOWN));
        });
    }

    @Test
    public void isAvailable() {
        assertTrue(priceNew.isAvailable());
    }

}