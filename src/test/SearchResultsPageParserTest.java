package test;

import main.models.GamePreview;
import main.models.GamePreviews;
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
            GamePreview gamePreview = gamePreviews.get(6);

            assertEquals(
                    "PS4",
                    gamePreview.getPlatform()
            );

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

}