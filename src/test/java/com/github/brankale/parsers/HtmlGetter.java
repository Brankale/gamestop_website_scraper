package com.github.brankale.parsers;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.io.PrintWriter;

/**
 * Use this utility class to get the HTML of a specified URL.
 * The HTML will be placed in the project root folder.
 */
public class HtmlGetter {

    private static final String URL = "insert URL here";

    public static void main(String[] args) {
        createHtml();
    }

    private static void createHtml() {
        try {
            PrintWriter out = new PrintWriter("example.html");
            out.println(getHtml());
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * @return HTML without: <script>, <style> tags
     */
    private static String getHtml() {
        try {
            Document document = Jsoup.connect(URL).get();
            document.getElementsByTag("script").remove();
            document.getElementsByTag("style").remove();
            return document.html();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "Cannot retrieve html";
    }

}
