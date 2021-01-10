package test.parsers.search_results_item_parser;

import main.models.GamePreview;
import main.parsers.SearchResultsItemParser;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class SearchResultsItemParserTest {

    private static SearchResultsItemParser parser;
    private static GamePreview gamePreview;

    @BeforeAll
    private static void initParser() {
        parser = new SearchResultsItemParser();
    }

    @BeforeAll
    public static void parseItem() {
        SearchResultsItemParser parser = new SearchResultsItemParser();
        File html = new File("src/test/parsers/search_results_item_parser/search_results_item.html");
        Element root = createElement(html);
        gamePreview = parser.parse(root);
    }

    private static Element createElement(File html) {
        try {
            return Jsoup.parse(html, "UTF-8").body().child(0);
        } catch (IOException e) {
            e.printStackTrace();
            return new Element("div");
        }
    }

    private Element createElement(String html) {
        return Jsoup.parse(html).body().child(0);
    }

    @Test
    public void parseId() {
        int id = 133354;
        assertEquals(id, gamePreview.getId());
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

    @Test
    public void parseCoverUrl() {
        String coverUrl = "https://static-it.gamestop.it/images/products/302017/2med.jpg";
        String html =   "<a class=\"prodImg\" href=\"/Switch/Games/133354/persona-5-strikers\">" +
                            "<img   data-llsrc=\"https://static-it.gamestop.it/images/products/302017/2med.jpg\" " +
                                    "alt=\"2med image\"" +
                                    "src=\"" + coverUrl + "\"" +
                                    "class=\"\"" +
                                    "onerror=\"this.src = '/Views/Locale/Content/Images/medDefault.jpg';\"" +
                                    "style=\"display: inline;\">" +
                        "</a>";
        Element element = createElement(html);
        String parsedCoverUrl = parser.parseCoverUrl(element);
        assertEquals(coverUrl, parsedCoverUrl);
    }

}