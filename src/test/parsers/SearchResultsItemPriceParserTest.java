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

    private static Element getPriceNewElement() {
        File file = new File("src/test/parsers/htmls/search_results/prices/price_new.html");
        return createElement(file);
    }

    private static Element getPriceUsedElement() {
        File file = new File("src/test/parsers/htmls/search_results/prices/price_used.html");
        return createElement(file);
    }

    private static Element getPricePreorderElement() {
        File file = new File("src/test/parsers/htmls/search_results/prices/price_preorder.html");
        return createElement(file);
    }

    private static Element getPriceDigitalElement() {
        File file = new File("src/test/parsers/htmls/search_results/prices/price_digital.html");
        return createElement(file);
    }

    private static Element getUnknownPriceTypeElement() {
        File file = new File("src/test/parsers/htmls/search_results/prices/unknown_price_type.html");
        return createElement(file);
    }

    @BeforeAll
    public static void init() {
        SearchResultsItemPriceParser priceParser = new SearchResultsItemPriceParser();
        priceNew = priceParser.parse(getPriceNewElement());
        priceUsed = priceParser.parse(getPriceUsedElement());
        pricePreorder = priceParser.parse(getPricePreorderElement());
        priceDigital = priceParser.parse(getPriceDigitalElement());
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
            priceParser.parse(getUnknownPriceTypeElement());
        });
    }

    @Test
    public void isAvailable() {
        assertTrue(priceNew.isAvailable());
    }

}