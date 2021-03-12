package test.parsers.search_results;

import main.models.price.PriceType;
import main.models.price.Prices;
import main.parsers.search_results.SearchResultsPriceParser;
import org.junit.jupiter.api.Test;
import test.parsers.Utils;

import java.io.File;
import java.math.BigDecimal;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class SearchResultsPriceParserTest {

    private static final String DIR = "src/test/parsers/search_results/htmls/prices/";

    private static final File FILE_PRICE = new File(DIR + "price_new_used_digital.html");
    private static final File FILE_PRICE_PREORDER = new File(DIR + "price_preorder.html");
    private static final File FILE_OLD_PRICES = new File(DIR + "old_prices.html");
    private static final File FILE_PRICE_WITH_PROMO = new File(DIR + "price_with_promo.html");

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

    @Test
    public void checkPrice() {
        Prices price = SearchResultsPriceParser.parse(Utils.createElement(FILE_PRICE));
        Prices pricePreorder = SearchResultsPriceParser.parse(Utils.createElement(FILE_PRICE_PREORDER));
        // when games are discounted, html is different
        Prices olderPrices = SearchResultsPriceParser.parse(Utils.createElement(FILE_OLD_PRICES));

        assertEquals(new BigDecimal("90.98"), price.get(0).getPrice());
        assertEquals(new BigDecimal("60.98"), pricePreorder.get(0).getPrice());
        assertEquals(new BigDecimal("329.98"), olderPrices.get(0).getPrice());
    }

    @Test
    public void checkOldPrices() {
        ArrayList<BigDecimal> oldPrices = new ArrayList<>();

        Prices oldPricesNew = SearchResultsPriceParser.parse(Utils.createElement(FILE_OLD_PRICES));
        oldPrices.add(new BigDecimal("349.98"));
        assertEquals(oldPrices,oldPricesNew.get(0).getOldPrices());
    }

    @Test
    public void checkPriceWithPromo() {
        Prices parsed = SearchResultsPriceParser.parse(Utils.createElement(FILE_PRICE_WITH_PROMO));
        assertEquals(new BigDecimal("49.98"), parsed.get(0).getPrice());
        assertEquals(new BigDecimal("48.98"), parsed.get(1).getPrice());
    }

    @Test
    public void checkHomeDeliveryWithPromo() {
        Prices parsed = SearchResultsPriceParser.parse(Utils.createElement(FILE_PRICE_WITH_PROMO));
        assertTrue(parsed.get(0).isHomeDeliveryAvailable());
        assertTrue(parsed.get(1).isHomeDeliveryAvailable());
    }

    @Test
    public void checkCollectInStoreWithPromo() {
        Prices parsed = SearchResultsPriceParser.parse(Utils.createElement(FILE_PRICE_WITH_PROMO));
        assertTrue(parsed.get(0).isCollectibleInStore());
        assertTrue(parsed.get(1).isCollectibleInStore());
    }

    @Test
    public void checkPriceType() {
        Prices priceNew = SearchResultsPriceParser.parse(Utils.createElement(FILE_PRICE_TYPE_NEW));
        Prices priceUsed = SearchResultsPriceParser.parse(Utils.createElement(FILE_PRICE_TYPE_USED));
        Prices pricePreorder = SearchResultsPriceParser.parse(Utils.createElement(FILE_PRICE_TYPE_PREORDER));
        Prices priceDigital = SearchResultsPriceParser.parse(Utils.createElement(FILE_PRICE_TYPE_DIGITAL));

        assertEquals(priceNew.get(0).getType(), PriceType.NEW);
        assertEquals(priceUsed.get(0).getType(), PriceType.USED);
        assertEquals(pricePreorder.get(0).getType(), PriceType.PREORDER);
        assertEquals(priceDigital.get(0).getType(), PriceType.DIGITAL);
    }

    @Test
    public void throwsExceptionOnInvalidPriceType() {
        assertThrows(SearchResultsPriceParser.UnknownPriceTypeException.class, () ->
                SearchResultsPriceParser.parse(Utils.createElement(FILE_PRICE_TYPE_UNKNOWN))
        );
    }

    @Test
    public void checkAvailability() {
        Prices available = SearchResultsPriceParser.parse(Utils.createElement(FILE_PRICE_AVAILABLE));
        Prices notAvailable = SearchResultsPriceParser.parse(Utils.createElement(FILE_PRICE_UNAVAILABLE));
        Prices preorderAvailable = SearchResultsPriceParser.parse(Utils.createElement(FILE_PREORDER_AVAILABLE));
        // TODO: find an HTML example
        // Price preorderNotAvailable = priceParser.parse(createElement(FILE_PREORDER_UNAVAILABLE));

        assertTrue(available.get(0).isAvailable());
        assertFalse(notAvailable.get(0).isAvailable());
        assertTrue(preorderAvailable.get(0).isAvailable());
        // TODO: find an HTML example
        // assertFalse(preorderNotAvailable.isAvailable());
    }

    @Test
    public void isHomeDeliveryAvailable() {
        Prices available = SearchResultsPriceParser.parse(Utils.createElement(FILE_HOME_DELIVERY_AVAILABLE));
        Prices unavailable = SearchResultsPriceParser.parse(Utils.createElement(FILE_HOME_DELIVERY_UNAVAILABLE));
        assertTrue(available.get(0).isHomeDeliveryAvailable());
        assertFalse(unavailable.get(0).isHomeDeliveryAvailable());
    }

    @Test
    public void isCollectInStoreAvailable() {
        Prices available = SearchResultsPriceParser.parse(Utils.createElement(FILE_COLLECT_IN_STORE_AVAILABLE));
        Prices unavailable = SearchResultsPriceParser.parse(Utils.createElement(FILE_COLLECT_IN_STORE_UNAVAILABLE));
        assertTrue(available.get(0).isCollectibleInStore());
        assertFalse(unavailable.get(0).isCollectibleInStore());
    }

}