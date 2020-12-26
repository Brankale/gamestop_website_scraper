package main.parsers;

import org.jsoup.nodes.Element;

public class SearchResultsItemParser {

    /**
     * Returns the id of the game given the h3 tag
     * @param rootTag h3 tag inside <div class="classProdInfo">
     * @return the id of the game
     */
    public int parseId(Element rootTag) {
        final String id = rootTag.child(0).attr("href").split("/")[3];
        return Integer.parseInt(id);
    }

    /**
     * Returns the title of the game given the h3 tag
     * @param rootTag h3 tag inside <div class="classProdInfo">
     * @return the title of the game
     */
    public String parseTitle(Element rootTag) {
        return rootTag.child(0).text().trim();
    }

    /**
     * Returns the platform of the game given the h4 tag
     * @param rootTag h4 tag inside <div class="classProdInfo">
     * @return the platform of the game
     */
    public String parsePlatform(Element rootTag) {
        return rootTag.textNodes().get(0).text().trim();
    }

    /**
     * Returns the publisher of the game given the h4 tag
     * @param rootTag h4 tag inside <div class="classProdInfo">
     * @return the publisher of the game
     */
    public String parsePublisher(Element rootTag) {
        return rootTag.getElementsByTag("strong").text().trim();
    }

}
