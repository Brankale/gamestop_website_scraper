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

}