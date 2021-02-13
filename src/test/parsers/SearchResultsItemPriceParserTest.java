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
    private static final File FILE_PRICE_TYPE_NEW = new File(DIR + "price_type_new.html");
    private static final File FILE_PRICE_TYPE_USED = new File(DIR + "price_type_used.html");
    private static final File FILE_PRICE_TYPE_PREORDER = new File(DIR + "price_type_preorder.html");
    private static final File FILE_PRICE_TYPE_DIGITAL = new File(DIR + "price_type_digital.html");
    private static final File FILE_PRICE_TYPE_UNKNOWN = new File(DIR + "price_type_unknown.html");
    private static final File FILE_PRICE_AVAILABLE = new File(DIR + "price_available.html");
    private static final File FILE_PRICE_NOT_AVAILABLE = new File(DIR + "price_unavailable.html");
    private static final File FILE_PREORDER_AVAILABLE = new File(DIR + "preorder_available.html");
    // TODO: find an HTML example
    // private static final File FILE_PREORDER_NOT_AVAILABLE = new File(DIR + "preorder_unavailable.html");
    private static final File FILE_HOME_DELIVERY_AVAILABLE =
            new File(DIR + "home_delivery_available.html");
    private static final File FILE_HOME_DELIVERY_UNAVAILABLE =
            new File(DIR + "home_delivery_unavailable.html");
    private static final File FILE_COLLECT_IN_STORE_AVAILABLE =
            new File(DIR + "collect_in_store_available.html");
    private static final File FILE_COLLECT_IN_STORE_UNAVAILABLE =
            new File(DIR + "collect_in_store_unavailable.html");

    private static SearchResultsItemPriceParser priceParser;

    @BeforeAll
    public static void init() {
        priceParser = new SearchResultsItemPriceParser();
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
    public void checkPriceType() {
        Price priceNew = priceParser.parse(createElement(FILE_PRICE_TYPE_NEW));
        Price priceUsed = priceParser.parse(createElement(FILE_PRICE_TYPE_USED));
        Price pricePreorder = priceParser.parse(createElement(FILE_PRICE_TYPE_PREORDER));
        Price priceDigital = priceParser.parse(createElement(FILE_PRICE_TYPE_DIGITAL));

        assertEquals(priceNew.getType(), PriceType.NEW);
        assertEquals(priceUsed.getType(), PriceType.USED);
        assertEquals(pricePreorder.getType(), PriceType.PREORDER);
        assertEquals(priceDigital.getType(), PriceType.DIGITAL);
    }

    @Test
    public void throwsExceptionOnInvalidPriceType() {
        assertThrows(SearchResultsItemPriceParser.UnknownPriceTypeException.class, () ->
            priceParser.parse(createElement(FILE_PRICE_TYPE_UNKNOWN))
        );
    }

    @Test
    public void checkAvailability() {
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

    @Test
    public void isHomeDeliveryAvailable() {
        Price available = priceParser.parse(createElement(FILE_HOME_DELIVERY_AVAILABLE));
        Price unavailable = priceParser.parse(createElement(FILE_HOME_DELIVERY_UNAVAILABLE));
        assertTrue(available.isHomeDeliveryAvailable());
        assertFalse(unavailable.isHomeDeliveryAvailable());
    }

    @Test
    public void isCollectInStoreAvailable() {
        Price available = priceParser.parse(createElement(FILE_COLLECT_IN_STORE_AVAILABLE));
        Price unavailable = priceParser.parse(createElement(FILE_COLLECT_IN_STORE_UNAVAILABLE));
        assertTrue(available.isCollectibleInStore());
        assertFalse(unavailable.isCollectibleInStore());
    }

}