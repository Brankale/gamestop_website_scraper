package main.parsers;

import com.sun.istack.internal.NotNull;
import main.models.price.Price;
import main.models.price.PriceType;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.math.BigDecimal;
import java.util.ArrayList;

public class SearchResultsItemPriceParser {

    /**
     * Returns a price given an Element with the root tag
     * <div class="prodBuy"></div>
     * @param element div tag with class="prodBuy"
     * @return a price
     */
    public Price parse(Element element) {
        Element priceTypeTag = element.getElementsByTag("p").first();
        Elements homeDeliveryTag = element.getElementsByClass("homeDeliveryAvailable");
        Elements collectInStore = element.getElementsByClass("clickAndCollectAvailable");

        Price price = new Price(getPrice(priceTypeTag), getPriceType(priceTypeTag));

        price.addOldPrices(getOldPrices(priceTypeTag));

        price.setAvailable(isAvailable(element));

        if (!homeDeliveryTag.isEmpty()) {
            price.setHomeDeliveryAvailability(isHomeDeliveryAvailable(homeDeliveryTag.first()));
        }

        if (!collectInStore.isEmpty()) {
            price.setCollectibleInStore(isCollectibleInStore(collectInStore.first()));
        }

        return price;
    }

    /**
     * Returns the price given an Element with root tag
     * <p class="buyXXX"></p>
     * @param element p tag with class="buyXXX"
     * @return the price
     */
    private BigDecimal getPrice(Element element) {
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
     * Returns the old prices given an Element with root tag
     * <p class="buyXXX"></p>
     * @param element p tag with class="buyXXX"
     * @return an array with old prices
     */
    @NotNull
    private ArrayList<BigDecimal> getOldPrices(Element element) {
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
     * Accepted formats: "xx,xx€","xx.xx€", "x.xxx,xx€".
     * There can be any currency.
     * @param price string with the price
     * @return a BigDecimal representing the price
     */
    private BigDecimal parsePriceString(String price) {
        String parsable = price
                .replaceAll("[^0-9.,]","")  // remove all characters except for numbers, ',' and '.'
                .replace(".", "")           // handle prices over 999,99€ like 1.249,99€
                .replace(',', '.');         // convert the price in a string that can be parsed
        return new BigDecimal(parsable);
    }

    /**
     * Returns the price type given an Element with the root tag
     * <p class="buyXXX"></p>
     * @param element p tag with class="buyXXX"
     * @throws UnknownPriceTypeException if the price type is unknown
     * @return the price type
     */
    private PriceType getPriceType(Element element) {
        switch (element.className()) {
            case "buyNew": return PriceType.NEW;
            case "buyUsed": return PriceType.USED;
            case "buyPresell": return PriceType.PREORDER;
            case "buyDLC": return PriceType.DIGITAL;
            default: throw new UnknownPriceTypeException(element.className());
        }
    }

    /**
     * Returns the availability given an Element with the root tag
     * <p class="buyXXX"></p>
     * @param element p tag with class="buyXXX"
     * @return the availability of the price type
     */
    private boolean isAvailable(Element element) {
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
     * Returns the home delivery availability given an Element
     * with root tag <span class="homeDeliveryAvailable"></span>
     * @param element span tag with class="homeDeliveryAvailable"
     * @return the availability of home delivery
     */
    private boolean isHomeDeliveryAvailable(Element element) {
        // deliveryUnavailable.png if unavailable
        return element.getElementsByTag("img").attr("src")
                .equals("/Content/Images/deliveryAvailable.png");
    }

    /**
     * Returns the "collect in store" availability given an Element
     * with root tag <span class="clickAndCollectAvailable"></span>
     * @param element span tag with class="clickAndCollectAvailable"
     * @return the availability of "collect in store"
     */
    private boolean isCollectibleInStore(Element element) {
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
