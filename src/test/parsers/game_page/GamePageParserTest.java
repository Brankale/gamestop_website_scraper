package test.parsers.game_page;

import main.models.Game;
import main.parsers.game_page.GamePageParser;
import org.jsoup.nodes.Element;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import test.parsers.Utils;

import java.io.File;

import static org.junit.jupiter.api.Assertions.*;

class GamePageParserTest {

    private static final String DIR = "src/test/parsers/game_page/htmls/";

    private static final File EXAMPLE = new File(DIR + "example.html");

    private static Game game;

    @BeforeAll
    public static void init() {
        Element element = Utils.createElement(EXAMPLE);
        game = GamePageParser.getGame(element.ownerDocument());
    }

    @Test
    public void getId() {
        assertEquals(126640, game.getId());
    }

    @Test
    public void getTitle() {
        assertEquals("Cyberpunk 2077 - Day One Edition", game.getTitle());
    }

    @Test
    public void getPlatform() {
        assertEquals("PS4", game.getPlatform());
    }

    @Test
    public void getPublisher() {
        assertEquals("CD Projekt", game.getPublisher());
    }

    @Test
    public void getDescription() {
        assertTrue(game.getDescription().startsWith("Cyberpunk 2077 è un'avventura a mondo aperto ambientata a Night City"));
        assertTrue(game.getDescription().endsWith("Sfondi per desktop e dispositivi mobile"));
    }

}