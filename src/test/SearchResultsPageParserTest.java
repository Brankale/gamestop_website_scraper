package test;

import main.models.GamePreviewOld;
import main.models.GamePreviews;
import main.models.Price;
import main.models.Prices;
import main.parsers.SearchResultsPageParser;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

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

                        for (float oldPrice : price.getOldPrices()) {
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