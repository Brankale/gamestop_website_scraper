package main.parsers.search_results;

import com.sun.istack.internal.NotNull;
import main.models.price.Price;
import main.models.price.PriceType;
import main.models.price.Prices;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.math.BigDecimal;
import java.util.ArrayList;

public final class SearchResultsPriceParser {

    private SearchResultsPriceParser() {
        // prevent instantiation
    }

    /**
     * @param element <div class="prodBuy">
     * @return the Prices of the product
     */
    @NotNull
    public static Prices parse(Element element) {
        Elements buyXXXs = element.getElementsByTag("p");
        Elements productsAvailability = element.getElementsByTag("div");

        Prices prices = new Prices();
        for (int i = 0; i < buyXXXs.size(); ++i) {
            if (buyXXXs.get(i).className().startsWith("buy")) {
                prices.add(parse(buyXXXs.get(i), productsAvailability.get(i)));
            }
        }
        return prices;
    }

    /**
     * @param buyXXX <p class="buyXXX"> tag
     * @param productAvailability <div class="productAvailability"> tag
     * @return the Price of the product
     */
    @NotNull
    private static Price parse(@NotNull Element buyXXX, @NotNull Element productAvailability) {
        Price price = new Price(parsePrice(buyXXX), parsePriceType(buyXXX));
        price.addOldPrices(parseOldPrices(buyXXX));
        price.setAvailable(parseAvailability(buyXXX));
        price.setHomeDeliveryAvailability(parseHomeDeliveryAvailability(
                productAvailability.getElementsByClass("homeDeliveryAvailable").first()
        ));
        price.setCollectibleInStore(parseCollectibleInStore(
                productAvailability.getElementsByClass("clickAndCollectAvailable").first()
        ));
        return price;
    }

    /**
     * @param element <p class="buyXXX"> tag
     * @return the price
     */
    @NotNull
    private static BigDecimal parsePrice(@NotNull Element element) {
        // em tags are present only if the game is discounted
        Elements em = element.getElementsByTag("em");
        if (em.isEmpty()) {
            Element tmp = element.getElementsByTag("span").first();
            tmp.child(0).remove();  // remove <strong></strong>
            return parsePriceString(tmp.text());
        } else {
            return parsePriceString(em.first().text());
        }
    }

    /**
     * @param element <p class="buyXXX"> tag
     * @return an array with old prices
     */
    @NotNull
    private static ArrayList<BigDecimal> parseOldPrices(@NotNull Element element) {
        Elements em = element.getElementsByTag("em");
        if (!em.isEmpty()) {
            // em.size() = current price + # old prices
            ArrayList<BigDecimal> oldPrices = new ArrayList<>(em.size() - 2);
            for (int i = 1; i < em.size(); ++i) {
                oldPrices.add(parsePriceString(em.get(i).text()));
            }
            return oldPrices;
        }
        return new ArrayList<>();
    }

    /**
     * Accepted format: "xxx,xx€" where 'x' is a digit.
     * There can be any currency.
     * @param price string with the price
     * @return a BigDecimal representing the price
     */
    @NotNull
    private static BigDecimal parsePriceString(@NotNull String price) {
        // TODO: xxx.xx€ is the format of other currencies.
        // TODO: x.xxx,xx€ is a special case but it's extremely rare. Find an HTML example.

        StringBuilder parsable = new StringBuilder();
        char currentChar;
        for (int i = 0; i < price.length() - 1; ++i) {  // length - 1 skips the currency
            currentChar = price.charAt(i);
            if (currentChar == ',') {
                parsable.append('.');                   // replace ',' with '.'
            } else {
                parsable.append(currentChar);
            }
        }

        return new BigDecimal(parsable.toString());
    }

    /**
     * @param element <p class="buyXXX"> tag
     * @return the price type
     *
     * @throws UnknownPriceTypeException if the price type is unknown
     */
    @NotNull
    private static PriceType parsePriceType(@NotNull Element element) {
        switch (element.className()) {
            case "buyNew": return PriceType.NEW;
            case "buyUsed": return PriceType.USED;
            case "buyPresell": return PriceType.PREORDER;
            case "buyDLC": return PriceType.DIGITAL;
            default: throw new UnknownPriceTypeException(element.className());
        }
    }

    /**
     * @param element <p class="buyXXX"> tag
     * @return the availability of the price type
     */
    private static boolean parseAvailability(@NotNull Element element) {
        // if you can buy the product:
        //   - class "megaButton buyTier3 cartAddNoRadio" (NEW, USED prices)
        //   - class "megaButton cartAddNoRadio"          (PREORDER prices)
        // if you can't buy the product:
        //   - class "megaButton buyTier3 buyDisabled"    (NEW, USED prices)
        //   - class "megaButton buyDisabled"             (PREORDER prices)

        Element a = element.getElementsByTag("a").first();
        return a.className().equals("megaButton buyTier3 cartAddNoRadio") ||
                a.className().equals("megaButton cartAddNoRadio");
    }

    /**
     * @param element <span class="homeDeliveryAvailable"> tag
     * @return the availability of home delivery
     */
    private static boolean parseHomeDeliveryAvailability(@NotNull Element element) {
        // deliveryUnavailable.png if unavailable
        return element.getElementsByTag("img").attr("src")
                .equals("/Content/Images/deliveryAvailable.png");
    }

    /**
     * @param element <span class="clickAndCollectAvailable"> tag
     * @return the availability of "collect in store"
     */
    private static boolean parseCollectibleInStore(@NotNull Element element) {
        // deliveryUnavailable.png if unavailable
        return element.getElementsByTag("img").attr("src")
                .equals("/Content/Images/deliveryAvailable.png");
    }

    public static class UnknownPriceTypeException extends RuntimeException {
        private final String unknownPriceType;

        public UnknownPriceTypeException(String unknownPriceType) {
            this.unknownPriceType = unknownPriceType;
        }

        @Override
        public String toString() {
            return "Given price type: " + unknownPriceType;
        }
    }

}
