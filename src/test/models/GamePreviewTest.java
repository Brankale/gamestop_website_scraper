package test.models;

import main.models.GamePreview;
import main.models.price.Price;
import main.models.price.PriceType;
import main.models.price.Prices;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.net.MalformedURLException;

import static org.junit.jupiter.api.Assertions.*;

public class GamePreviewTest {

    private static GamePreview gamePreview;

    @BeforeAll
    public static void createGamePreviewObject() {
        gamePreview = new GamePreview(0);
    }

    @Test
    public void setId() {
        int id = 234;
        GamePreview gamePreview = new GamePreview(id);
        assertEquals(gamePreview.getId(), id);
    }

    @Test
    public void setTitle() {
        String title = "this is the title";
        gamePreview.setTitle(title);
        assertEquals(title, gamePreview.getTitle());
    }

    @Test
    public void titleCantBeNull() {
        assertNotNull(gamePreview.getTitle());
    }

    @Test
    public void setPlatform() {
        String platform = "this is the platform";
        gamePreview.setPlatform(platform);
        assertEquals(gamePreview.getPlatform(), platform);
    }

    @Test
    public void platformCantBeNull() {
        assertNotNull(gamePreview.getPlatform());
    }

    @Test
    public void setPublisher() {
        String publisher = "this is a publisher";
        gamePreview.setPublisher(publisher);
        assertEquals(gamePreview.getPublisher(), publisher);
    }

    @Test
    public void publisherCantBeNull() {
        assertNotNull(gamePreview.getPublisher());
    }

    @Test
    public void setReleaseDate() {
        String releaseDate = "this is a releaseDate";
        gamePreview.setReleaseDate(releaseDate);
        assertEquals(gamePreview.getReleaseDate(), releaseDate);
    }

    @Test
    public void releaseDateCantBeNull() {
        assertNotNull(gamePreview.getReleaseDate());
    }

    @Test
    public void equalsIfSameId() {
        int id = 123;
        GamePreview g1 = new GamePreview(id);
        GamePreview g2 = new GamePreview(id);
        assertEquals(g1, g2);
    }

    @Test
    public void sameHashCodeIfSameId() {
        int id = 123;
        GamePreview g1 = new GamePreview(id);
        GamePreview g2 = new GamePreview(id);
        assertEquals(g2.hashCode(), g1.hashCode());
    }

    @Test
    public void setValidCoverUrl() throws MalformedURLException {
        String url = "https://example.com";
        gamePreview.setCoverUrl(url);
        assertEquals(gamePreview.getCoverUrl(), url);
    }

    @Test
    public void coverUrlCantBeNull() {
        assertNotNull(gamePreview.getCoverUrl());
    }

    @Test
    public void setPrices() {
        Prices expected = new Prices();
        Price price = new Price(new BigDecimal("9.99"), PriceType.NEW);
        expected.add(price);

        gamePreview.setPrices(expected);
        assertEquals(gamePreview.getPrices(), expected);
    }

}
