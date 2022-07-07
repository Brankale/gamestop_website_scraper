package com.github.brankale.parsers.search_results;

import com.github.brankale.models.price.Price;
import com.github.brankale.models.price.PriceType;
import org.jsoup.nodes.Element;

import java.math.BigDecimal;
import java.util.ArrayList;

public final class SearchResultsPriceParser {

    private SearchResultsPriceParser() {
        // prevent instantiation
    }

    /**
     * @param element <div class="searchTilePriceDesktop">
     * @return the Prices of the product
     */
    public static ArrayList<Price> parse(Element element) {
        ArrayList<Price> prices = new ArrayList<>();
        for (int i = 0; i < PriceType.values().length; ++i) {
            Price price = parse(element, PriceType.values()[i]);
            if (price != null)
                prices.add(price);
        }
        return prices;
    }

    private static Price parse(Element element, PriceType priceType) {
        BigDecimal price = null;
        BigDecimal discountedPrice = null;

        switch (priceType) {
            case NEW:
                price = parsePrice(element, "price-new");
                discountedPrice = parseDiscountedPrice(element, "discounted-new");
                break;
            case USED:
                price = parsePrice(element, "price-used");
                discountedPrice = parseDiscountedPrice(element, "discounted-used");
                break;
            case PREORDER:
                price = parsePrice(element, "price-presell");
                // TODO: don't know if discounted-presell exists
                discountedPrice = parseDiscountedPrice(element, "discounted-presell");
                break;
            case DIGITAL:
                price = parsePrice(element, "price-dlc");
                // TODO: don't know if discounted-dlc exists
                discountedPrice = parseDiscountedPrice(element, "discounted-dlc");
                break;
        }

        if (price != null) {
            return new Price.Builder(price, priceType)
                    .setDiscountedPrice(discountedPrice)
                    .build();
        }

        return null;
    }

    private static BigDecimal parsePrice(Element element, String divClassName) {
        Element div = element.getElementsByClass(divClassName).first();
        if (div == null)
            return null;
        return parsePriceString(div.text());
    }

    private static BigDecimal parsePriceString(String priceString) {
        priceString = priceString.substring(priceString.indexOf("-") + 1)
                .replace("€", "")
                .replace(",", ".")
                .trim();
        return new BigDecimal(priceString);
    }

    private static BigDecimal parseDiscountedPrice(Element element, String divClassName) {
        Element div = element.getElementsByClass(divClassName).first();
        if (div == null)
            return null;
        return parseDiscountedPriceString(div.text());
    }

    private static BigDecimal parseDiscountedPriceString(String discountedPriceString) {
        discountedPriceString = discountedPriceString.replace("€", "")
                .replace(",", ".")
                .trim();
        return new BigDecimal(discountedPriceString);
    }

}
