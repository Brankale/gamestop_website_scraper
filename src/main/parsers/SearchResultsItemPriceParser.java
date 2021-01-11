package main.parsers;

import main.models.price.Price;
import main.models.price.PriceType;
import org.jsoup.nodes.Element;

import java.math.BigDecimal;

public class SearchResultsItemPriceParser {

    /**
     * Returns a price given an Element with the root tag
     * <div class="prodBuy"></div>
     * @param element div tag with class="prodBuy"
     * @return a price
     */
    public Price parse(Element element) {
        PriceType priceType = getPriceType(element.getElementsByTag("p").first());
        return new Price(BigDecimal.valueOf(-1), priceType);
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
