package com.github.brankale.parsers.game_page;

import com.github.brankale.models.Game;
import org.jsoup.nodes.Element;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import com.github.brankale.parsers.Utils;

import java.io.File;

import static org.junit.jupiter.api.Assertions.*;

class GamePageParserTest {

    private static final String DIR = "src/test/java/com/github/brankale/parsers/game_page/html/";

    private static final File EXAMPLE = new File(DIR + "example.html");

    private static Game game;

    @BeforeAll
    public static void init() {
        Element element = Utils.createElement(EXAMPLE);
        game = GamePageParser.getGame(element.ownerDocument());
    }

    @Test
    public void getId() {
        assertEquals(135607, game.getId());
    }

    @Test
    public void getTitle() {
        assertEquals("Halo Infinite (Compatibile con Xbox Series X|S)", game.getTitle());
    }

    @Test
    public void getPlatform() {
        assertEquals("XboxONE", game.getPlatform());
    }

    @Test
    public void getPublisher() {
        assertEquals("Microsoft", game.getPublisher());
    }

    @Test
    public void getDescription() {
        assertTrue(game.getDescription().startsWith("Il ritorno di Master Chief in Halo Infinite"));
        assertTrue(game.getDescription().endsWith("Nuovo: 305865 Usato: 305866"));
    }

}