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
    public static void createGameObject() {
        game = new Game.Builder(0).build();
    }

    @Test
    public void setId() {
        int id = 234;
        Game game = new Game.Builder(id).build();
        assertEquals(game.getId(), id);
    }

    @Test
    public void setTitle() {
        String title = "this is the title";
        game = new Game.Builder(0).setTitle(title).build();
        assertEquals(title, game.getTitle());
    }

    @Test
    public void titleCantBeNull() {
        assertNotNull(game.getTitle());
    }

    @Test
    public void setPlatform() {
        String platform = "this is the platform";
        game = new Game.Builder(0).setPlatform(platform).build();
        assertEquals(game.getPlatform(), platform);
    }

    @Test
    public void platformCantBeNull() {
        assertNotNull(game.getPlatform());
    }

    @Test
    public void setPublisher() {
        String publisher = "this is a publisher";
        game = new Game.Builder(0).setPublisher(publisher).build();
        assertEquals(game.getPublisher(), publisher);
    }

    @Test
    public void publisherCantBeNull() {
        assertNotNull(game.getPublisher());
    }

    @Test
    public void setReleaseDate() {
        String releaseDate = "this is a releaseDate";
        game = new Game.Builder(0).setRelease(releaseDate).build();
        assertEquals(game.getRelease(), releaseDate);
    }

    @Test
    public void releaseDateCantBeNull() {
        assertNotNull(game.getRelease());
    }

    @Test
    public void equalsIfSameId() {
        int id = 123;
        Game g1 = game = new Game.Builder(id).build();
        Game g2 = game = new Game.Builder(id).build();
        assertEquals(g1, g2);
    }

    @Test
    public void sameHashCodeIfSameId() {
        int id = 123;
        Game g1 = new Game.Builder(id).build();
        Game g2 = new Game.Builder(id).build();
        assertEquals(g2.hashCode(), g1.hashCode());
    }

    @Test
    public void setValidCoverUrl() {
        String url = "https://example.com";
        Game game = new Game.Builder(0).setCoverUrl(url).build();
        assertEquals(game.getCoverUrl(), url);
    }

    @Test
    public void coverUrlCantBeNull() {
        assertNotNull(game.getCoverUrl());
    }

    @Test
    public void setPrices() {
        ArrayList<Price> expected = new ArrayList<>();
        Price price = new Price.Builder(new BigDecimal("9.99"), PriceType.NEW).build();
        expected.add(price);

        Game game = new Game.Builder(0).addPrices(expected).build();
        assertEquals(game.getPrices(), expected);
    }

}
