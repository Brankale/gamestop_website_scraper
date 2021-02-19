package test.parsers.search_results;

import main.models.price.Price;
import main.models.price.PriceType;
import main.parsers.search_results.SearchResultsItemPriceParser;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.math.BigDecimal;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class SearchResultsItemPriceParserTest {

    private static final String DIR = "src/test/parsers/search_results/htmls/prices/";

    private static final File FILE_PRICE = new File(DIR + "price_new_used_digital.html");
    private static final File FILE_PRICE_PREORDER = new File(DIR + "price_preorder.html");
    private static final File FILE_OLD_PRICES = new File(DIR + "old_prices.html");

    private static final File FILE_PRICE_TYPE_NEW = new File(DIR + "price_type_new.html");
    private static final File FILE_PRICE_TYPE_USED = new File(DIR + "price_type_used.html");
    private static final File FILE_PRICE_TYPE_PREORDER = new File(DIR + "price_type_preorder.html");
    private static final File FILE_PRICE_TYPE_DIGITAL = new File(DIR + "price_type_digital.html");
    private static final File FILE_PRICE_TYPE_UNKNOWN = new File(DIR + "price_type_unknown.html");

    private static final File FILE_PRICE_AVAILABLE = new File(DIR + "price_available.html");
    private static final File FILE_PRICE_UNAVAILABLE = new File(DIR + "price_unavailable.html");
    private static final File FILE_PREORDER_AVAILABLE = new File(DIR + "preorder_available.html");
    // TODO: find an HTML example
    // private static final File FILE_PREORDER_UNAVAILABLE = new File(DIR + "preorder_unavailable.html");

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

    @Test
    public void checkPrice() {
        Price price = priceParser.parse(Utils.createElement(FILE_PRICE));
        Price pricePreorder = priceParser.parse(Utils.createElement(FILE_PRICE_PREORDER));
        // when games are discounted, html is different
        Price olderPrices = priceParser.parse(Utils.createElement(FILE_OLD_PRICES));

        assertEquals(new BigDecimal("90.98"), price.getPrice());
        assertEquals(new BigDecimal("60.98"), pricePreorder.getPrice());
        assertEquals(new BigDecimal("329.98"), olderPrices.getPrice());
    }

    @Test
    public void checkOldPrices() {
        ArrayList<BigDecimal> oldPrices = new ArrayList<>();

        Price oldPricesNew = priceParser.parse(Utils.createElement(FILE_OLD_PRICES));
        oldPrices.add(new BigDecimal("349.98"));
        assertEquals(oldPrices,oldPricesNew.getOldPrices());
    }

    @Test
    public void checkPriceType() {
        Price priceNew = priceParser.parse(Utils.createElement(FILE_PRICE_TYPE_NEW));
        Price priceUsed = priceParser.parse(Utils.createElement(FILE_PRICE_TYPE_USED));
        Price pricePreorder = priceParser.parse(Utils.createElement(FILE_PRICE_TYPE_PREORDER));
        Price priceDigital = priceParser.parse(Utils.createElement(FILE_PRICE_TYPE_DIGITAL));

        assertEquals(priceNew.getType(), PriceType.NEW);
        assertEquals(priceUsed.getType(), PriceType.USED);
        assertEquals(pricePreorder.getType(), PriceType.PREORDER);
        assertEquals(priceDigital.getType(), PriceType.DIGITAL);
    }

    @Test
    public void throwsExceptionOnInvalidPriceType() {
        assertThrows(SearchResultsItemPriceParser.UnknownPriceTypeException.class, () ->
            priceParser.parse(Utils.createElement(FILE_PRICE_TYPE_UNKNOWN))
        );
    }

    @Test
    public void checkAvailability() {
        Price available = priceParser.parse(Utils.createElement(FILE_PRICE_AVAILABLE));
        Price notAvailable = priceParser.parse(Utils.createElement(FILE_PRICE_UNAVAILABLE));
        Price preorderAvailable = priceParser.parse(Utils.createElement(FILE_PREORDER_AVAILABLE));
        // TODO: find an HTML example
        // Price preorderNotAvailable = priceParser.parse(createElement(FILE_PREORDER_UNAVAILABLE));

        assertTrue(available.isAvailable());
        assertFalse(notAvailable.isAvailable());
        assertTrue(preorderAvailable.isAvailable());
        // TODO: find an HTML example
        // assertFalse(preorderNotAvailable.isAvailable());
    }

    @Test
    public void isHomeDeliveryAvailable() {
        Price available = priceParser.parse(Utils.createElement(FILE_HOME_DELIVERY_AVAILABLE));
        Price unavailable = priceParser.parse(Utils.createElement(FILE_HOME_DELIVERY_UNAVAILABLE));
        assertTrue(available.isHomeDeliveryAvailable());
        assertFalse(unavailable.isHomeDeliveryAvailable());
    }

    @Test
    public void isCollectInStoreAvailable() {
        Price available = priceParser.parse(Utils.createElement(FILE_COLLECT_IN_STORE_AVAILABLE));
        Price unavailable = priceParser.parse(Utils.createElement(FILE_COLLECT_IN_STORE_UNAVAILABLE));
        assertTrue(available.isCollectibleInStore());
        assertFalse(unavailable.isCollectibleInStore());
    }

}