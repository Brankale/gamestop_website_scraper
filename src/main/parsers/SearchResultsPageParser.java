package main.parsers;

import com.sun.istack.internal.NotNull;
import main.models.GamePreviewOld;
import main.models.GamePreviews;
import main.models.PriceOld;
import main.models.Prices;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

public class SearchResultsPageParser {

    @NotNull
    public static GamePreviews getSearchResults(@NotNull final String link) throws IOException {

        final Document root = Jsoup.connect(link).get();

        final GamePreviews gamePreviews = new GamePreviews();

        Elements items = getItems(root);
        for (Element item : items) {
            final GamePreviewOld gamePreview = parseItem(item);
            gamePreviews.add(gamePreview);
        }

        return gamePreviews;
    }

    @NotNull
    private static Elements getItems(@NotNull Document root) {
        final Element productsList = root.getElementById("productsList");
        if (productsList != null) {
            return productsList.getElementsByClass("singleProduct");
        } else {
            throw new IllegalArgumentException("productsList tag id not found!");
        }
    }

    @NotNull
    private static GamePreviewOld parseItem(@NotNull final Element item) {

        final int id = getIdFromItem(item);
        final GamePreviewOld gamePreview = new GamePreviewOld(id);

        final String title = getTitleFromItem(item);
        final String platform = getPlatformFromItem(item);
        final String publisher = getPublisherFromItem(item);
        final Prices prices = getPricesFromItem(item);

        gamePreview.setTitle(title);
        gamePreview.setPlatform(platform);
        gamePreview.setPublisher(publisher);
        gamePreview.setPrices(prices);

        // TODO: init gamepreview parameters

        return gamePreview;
    }

    private static int getIdFromItem(@NotNull final Element item) {
        final Element prodImg = item.getElementsByClass("prodImg").first();
        final String id = prodImg.attr("href").split("/")[3];
        return Integer.parseInt(id);
    }

    @NotNull
    private static String getTitleFromItem(@NotNull final Element item) {
        return item.getElementsByTag("h3").first().text();
    }

    @NotNull
    private static String getPlatformFromItem(@NotNull final Element item) {
        return item.getElementsByTag("h4").first().textNodes().get(0).text().trim();
    }

    @NotNull
    private static String getPublisherFromItem(@NotNull final Element item) {
        return item.getElementsByTag("h4").first().getElementsByTag("strong").text();
    }

    @NotNull
    private static Prices getPricesFromItem(@NotNull final Element item) {
        final Prices prices = new Prices();

        final Element pricesSection = item.getElementsByClass("prodBuy").first();
        final Elements tags = pricesSection.children();

        for (int i = 0; i < tags.size(); i += 2) {
            final Element priceType = tags.get(i);
            final Element availability = tags.get(i+1);
            final PriceOld price = getPrice(priceType, availability);
            prices.add(price);
        }

        return prices;
    }

    @NotNull
    private static PriceOld getPrice(final Element priceType, final Element availability) {

        PriceOld price = null;

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

        switch (priceType.className()) {
            case "buyNew": price = new PriceOld(PriceOld.PriceType.NEW, value, oldPrices); break;
            case "buyUsed": price = new PriceOld(PriceOld.PriceType.USED, value, oldPrices); break;
            case "buyPresell": price = new PriceOld(PriceOld.PriceType.PREORDER, value, oldPrices); break;
            case "buyDLC": price = new PriceOld(PriceOld.PriceType.DIGITAL, value, oldPrices); break;
            default: throw new IllegalArgumentException("Couldn't find price type tag");
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
