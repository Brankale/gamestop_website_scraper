package test.parsers;

import main.models.price.Price;
import main.models.price.PriceType;
import main.parsers.SearchResultsItemPriceParser;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
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
    private static final File FILE_PRICE_AVAILABLE = new File(DIR + "price_available.html");
    private static final File FILE_PRICE_NOT_AVAILABLE = new File(DIR + "price_not_available.html");
    private static final File FILE_PREORDER_AVAILABLE = new File(DIR + "preorder_available.html");
    // TODO: find an HTML example
    // private static final File FILE_PREORDER_NOT_AVAILABLE = new File(DIR + "preorder_not_available.html");

    private static Element createElement(File html) {
        try {
            return Jsoup.parse(html, "UTF-8").body().child(0);
        } catch (IOException e) {
            e.printStackTrace();
            return new Element("div");
        }
    }

    @Test
    public static void checkPriceType() {
        SearchResultsItemPriceParser priceParser = new SearchResultsItemPriceParser();

        Price priceNew = priceParser.parse(createElement(FILE_PRICE_NEW));
        Price priceUsed = priceParser.parse(createElement(FILE_PRICE_USED));
        Price pricePreorder = priceParser.parse(createElement(FILE_PRICE_PREORDER));
        Price priceDigital = priceParser.parse(createElement(FILE_PRICE_DIGITAL));

        assertEquals(priceNew.getType(), PriceType.NEW);
        assertEquals(priceUsed.getType(), PriceType.USED);
        assertEquals(pricePreorder.getType(), PriceType.PREORDER);
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
        SearchResultsItemPriceParser priceParser = new SearchResultsItemPriceParser();

        Price available = priceParser.parse(createElement(FILE_PRICE_AVAILABLE));
        Price notAvailable = priceParser.parse(createElement(FILE_PRICE_NOT_AVAILABLE));
        Price preorderAvailable = priceParser.parse(createElement(FILE_PREORDER_AVAILABLE));
        // TODO: find an HTML example
        // Price preorderNotAvailable = priceParser.parse(createElement(FILE_PREORDER_NOT_AVAILABLE));

        assertTrue(available.isAvailable());
        assertFalse(notAvailable.isAvailable());
        assertTrue(preorderAvailable.isAvailable());
        // TODO: find an HTML example
        // assertFalse(preorderNotAvailable.isAvailable());
    }

}