package main.parsers;

import main.models.price.Price;
import main.models.price.PriceType;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.math.BigDecimal;

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

        Price price = new Price(BigDecimal.valueOf(-1), getPriceType(priceTypeTag));

        // always present
        price.setAvailable(isAvailable(element));

        //
        if (!homeDeliveryTag.isEmpty()) {
            price.setHomeDeliveryAvailability(isHomeDeliveryAvailable(homeDeliveryTag.first()));
        }


        return price;
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
