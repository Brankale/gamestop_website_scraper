package test.parsers;

import main.models.GamePreview;
import main.models.GamePreviews;
import main.models.price.Price;
import main.models.price.Prices;
import main.parsers.SearchResultsParser;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.jupiter.api.Test;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;

class SearchResultsParserTest {

    @Test
    public void skipGiftCard() {
        final String link = "https://www.gamestop.it/SearchResult/QuickSearch?q=giftcard";

        try {
            SearchResultsParser.getSearchResults(link);
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
            GamePreviews gamePreviews = SearchResultsParser.getSearchResults(link);

            for (GamePreview gamePreview : gamePreviews) {
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