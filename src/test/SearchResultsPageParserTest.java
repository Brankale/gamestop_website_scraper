package test;

import main.models.old.GamePreviewOld;
import main.models.old.GamePreviews;
import main.models.price.Price;
import main.models.price.Prices;
import main.parsers.SearchResultsPageParser;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.math.BigDecimal;

class SearchResultsPageParserTest {

    @Test
    public void skipGiftCard() {
        final String link = "https://www.gamestop.it/SearchResult/QuickSearch?q=giftcard";

        try {
            SearchResultsPageParser.getSearchResults(link);
        } catch (IOException ex) {
            assert true;
        } catch (ArrayIndexOutOfBoundsException ex) {
            assert false;
        }

        assert true;
    }

    // Use this to test new features
    @Test
    public void newFeature() {
        final String link = "https://www.gamestop.it/SearchResult/QuickSearch?q=persona";

        try {
            GamePreviews gamePreviews = SearchResultsPageParser.getSearchResults(link);

            for (GamePreviewOld gamePreview : gamePreviews) {
                System.out.println("\ntitle: " + gamePreview.getTitle());
                Prices prices = gamePreview.getPrices();
                if (!prices.isEmpty()) {
                    for (Price price : prices) {
                        System.out.println("type: " + price.getType().toString() );
                        System.out.println("price: " + price.getPrice());

                        for (BigDecimal oldPrice : price.getOldPrices()) {
                            System.out.println("oldPrice: " + oldPrice);
                        }
                    }
                }
            }

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

}