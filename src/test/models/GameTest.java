package test.models;

import main.models.Game;
import main.models.price.Price;
import main.models.price.PriceType;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class GameTest {

    private static Game game;

    @BeforeAll
    public static void createGamePreviewObject() {
        game = new Game(0);
    }

    @Test
    public void setId() {
        int id = 234;
        Game game = new Game(id);
        assertEquals(game.getId(), id);
    }

    @Test
    public void setTitle() {
        String title = "this is the title";
        game.setTitle(title);
        assertEquals(title, game.getTitle());
    }

    @Test
    public void titleCantBeNull() {
        assertNotNull(game.getTitle());
    }

    @Test
    public void setPlatform() {
        String platform = "this is the platform";
        game.setPlatform(platform);
        assertEquals(game.getPlatform(), platform);
    }

    @Test
    public void platformCantBeNull() {
        assertNotNull(game.getPlatform());
    }

    @Test
    public void setPublisher() {
        String publisher = "this is a publisher";
        game.setPublisher(publisher);
        assertEquals(game.getPublisher(), publisher);
    }

    @Test
    public void publisherCantBeNull() {
        assertNotNull(game.getPublisher());
    }

    @Test
    public void setReleaseDate() {
        String releaseDate = "this is a releaseDate";
        game.setReleaseDate(releaseDate);
        assertEquals(game.getReleaseDate(), releaseDate);
    }

    @Test
    public void releaseDateCantBeNull() {
        assertNotNull(game.getReleaseDate());
    }

    @Test
    public void equalsIfSameId() {
        int id = 123;
        Game g1 = new Game(id);
        Game g2 = new Game(id);
        assertEquals(g1, g2);
    }

    @Test
    public void sameHashCodeIfSameId() {
        int id = 123;
        Game g1 = new Game(id);
        Game g2 = new Game(id);
        assertEquals(g2.hashCode(), g1.hashCode());
    }

    @Test
    public void setValidCoverUrl() {
        String url = "https://example.com";
        game.setCoverUrl(url);
        assertEquals(game.getCoverUrl(), url);
    }

    @Test
    public void coverUrlCantBeNull() {
        assertNotNull(game.getCoverUrl());
    }

    @Test
    public void setPrices() {
        ArrayList<Price> expected = new ArrayList<>();
        Price price = new Price(new BigDecimal("9.99"), PriceType.NEW);
        expected.add(price);

        game.setPrices(expected);
        assertEquals(game.getPrices(), expected);
    }

}
