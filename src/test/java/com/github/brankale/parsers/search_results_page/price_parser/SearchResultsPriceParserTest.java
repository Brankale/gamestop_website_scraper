package com.github.brankale.parsers.search_results_page.price_parser;

import com.github.brankale.models.price.Price;
import com.github.brankale.parsers.search_results.SearchResultsPriceParser;
import org.junit.jupiter.api.Test;
import com.github.brankale.parsers.Utils;

import java.io.File;
import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class SearchResultsPriceParserTest {

    /**
     * TODO: things to test:
     * - availability
     * - home delivery (not) available
     * - collect in store (not) available
     */

    private static final String DIR = "src/test/java/com/github/brankale/parsers/search_results_page/price_parser/html/";

    private static final File NEW_DISCOUNTED = new File(DIR + "new_discounted.html");
    private static final File USED_DISCOUNTED = new File(DIR + "used_discounted.html");

    @Test
    public void parseNewPrice() {
        Price price = SearchResultsPriceParser.parse(Utils.createElement(NEW_DISCOUNTED)).get(0);
        assertEquals(new BigDecimal("49.98"), price.getPrice());
    }

    @Test
    public void parseDiscountedNewPrice() {
        Price price = SearchResultsPriceParser.parse(Utils.createElement(NEW_DISCOUNTED)).get(0);
        assertEquals(new BigDecimal("80.98"), price.getDiscountedPrice());
    }

    @Test
    public void parseUsedPrice() {
        Price price = SearchResultsPriceParser.parse(Utils.createElement(USED_DISCOUNTED)).get(0);
        assertEquals(new BigDecimal("39.98"), price.getPrice());
    }

    @Test
    public void parseDiscountedUsedPrice() {
        Price price = SearchResultsPriceParser.parse(Utils.createElement(USED_DISCOUNTED)).get(0);
        assertEquals(new BigDecimal("54.98"), price.getDiscountedPrice());
    }

}