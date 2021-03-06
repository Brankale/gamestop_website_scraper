package test.parsers;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;

import java.io.File;
import java.io.IOException;

public class Utils {

    public static Element createElement(File html) {
        try {
            return Jsoup.parse(html, "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
            return new Element("div");
        }
    }

}
