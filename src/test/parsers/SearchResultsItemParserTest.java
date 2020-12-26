package test.parsers;

import main.parsers.SearchResultsItemParser;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SearchResultsItemParserTest {

    private static SearchResultsItemParser parser;

    @BeforeAll
    private static void initParser() {
        parser = new SearchResultsItemParser();
    }

    private Element createElement(String html) {
        return Jsoup.parse(html).body().child(0);
    }

    @Test
    public void parseTitle() {
        String title = "Persona® 5 Strikers";
        String html =   "<h3>" +
                        "   <a href=\"/Switch/Games/133354/persona-5-strikers\">" +
                                title +
                        "   </a>" +
                        "</h3>";
        Element element = createElement(html);
        String parsedTitle = parser.parseTitle(element);
        assertEquals(title, parsedTitle);
    }

    @Test
    public void parsePlatform() {
        String platform = "Switch";
        String html =   "<h4 class=\"platLogo plat-Switch\">" +
                            platform +
                            "<span class=\"hideOnMobile\"> by <strong>Atlus</strong> </span>" +
                        "</h4>";
        Element element = createElement(html);
        String parsedPlatform = parser.parsePlatform(element);
        assertEquals(platform, parsedPlatform);
    }

    @Test
    public void parsePublisher() {
        String publisher = "Atlus";
        String html =   "<h4 class=\"platLogo plat-Switch\">Switch" +
                            "<span class=\"hideOnMobile\"> by " +
                                "<strong>" + publisher + "</strong> " +
                            "</span>" +
                        "</h4>";
        Element element = createElement(html);
        String parsedPublisher = parser.parsePublisher(element);
        assertEquals(publisher, parsedPublisher);
    }

}