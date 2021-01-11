package main.parsers;

import com.sun.istack.internal.NotNull;
import main.models.price.Price;
import main.models.price.PriceType;
import main.models.price.Prices;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.math.BigDecimal;
import java.util.ArrayList;

public class SearchResultsParser {

    @NotNull
    private static Elements getItems(@NotNull final Element root) {
        final Element productsList = root.getElementById("productsList");
        if (productsList != null) {
            return productsList.getElementsByClass("singleProduct");
        } else {
            throw new IllegalArgumentException("productsList tag id not found!");
        }
    }

    // TODO: price parsing should be done in a separate class
    @NotNull
    private static Prices getPricesFromItem(@NotNull final Element item) {
        final Prices prices = new Prices();

        final Element pricesSection = item.getElementsByClass("prodBuy").first();
        final Elements tags = pricesSection.children();

        for (int i = 0; i < tags.size(); i += 2) {
            final Element priceType = tags.get(i);
            final Element availability = tags.get(i+1);
            final Price price = getPrice(priceType, availability);
            prices.add(price);
        }

        return prices;
    }

    // TODO: price parsing should be done in a separate class
    @NotNull
    private static Price getPrice(final Element priceType, final Element availability) {

        Price price = null;

        float value;
        final ArrayList<Float> oldPrices = new ArrayList<>();

        // FIND PRICE

        // <em> tag is present only if there are multiple prices
        final Elements em = priceType.getElementsByTag("em");

        if (em.isEmpty()) {
            // if there's just one price
            value = stringToPrice(priceType.text());
        } else {
            // if more than one price is present
            value = stringToPrice(em.get(0).text());

            for (int i = 1; i < em.size(); ++i) {
                final float oldValue = stringToPrice(em.get(i).text());
                oldPrices.add(oldValue);
            }
        }

        // FIND PRICE TYPE & INIT

        PriceType type;

        switch (priceType.className()) {
            case "buyNew": type = PriceType.NEW; break;
            case "buyUsed": type = PriceType.USED; break;
            case "buyPresell": type = PriceType.PREORDER; break;
            case "buyDLC": type = PriceType.DIGITAL; break;
            default: throw new IllegalArgumentException("Couldn't find price type tag");
        }

        price = new Price(BigDecimal.valueOf(value), type);
        for (float oldPrice : oldPrices) {
            price.addOldPrice(BigDecimal.valueOf(oldPrice));
        }

        // CHECK AVAILABILITY

        // if you can buy the product:
        //   - class "megaButton buyTier3 cartAddNoRadio" (NEW, USED prices)
        //   - class "megaButton cartAddNoRadio"          (PREORDER prices)
        // if you can't buy the product:
        //   - class "megaButton buyTier3 buyDisabled"    (NEW, USED prices)
        //   - class "megaButton buyDisabled"             (PREORDER prices)
        final boolean available =
                priceType.getElementsByClass("megaButton buyTier3 cartAddNoRadio").size() == 1 ||
                priceType.getElementsByClass("megaButton cartAddNoRadio").size() == 1;

        price.setAvailable(available);

        // TODO: init the other price parameters

        return price;
    }

    // TODO: price parsing should be done in a separate class
    private static float stringToPrice(@NotNull final String price) {

        String str = price;

        // remove all the characters except for numbers, ',' and '.'
        str = str.replaceAll("[^0-9.,]","");
        // to handle prices over 999,99€ like 1.249,99€
        str = str.replace(".", "");
        // to convert the price in a string that can be parsed
        str = str.replace(',', '.');

        return Float.parseFloat(str);
    }

}