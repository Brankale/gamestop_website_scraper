package com.github.brankale.parsers;

import com.github.brankale.dto.DataProduct;
import com.github.brankale.models.Game;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

public class SearchResultsParserNew {

    private static final String SCHEME = "https://";
    private static final String DOMAIN_IT = "www.gamestop.it";
    private static final String SUB_DIRECTORY = "/SearchResult/QuickSearchAjax";

    public static void main(String[] args) throws IOException {
        parse("gta" );
    }

    public static List<Game> parse(String game) throws IOException {
        String url = SCHEME + DOMAIN_IT + SUB_DIRECTORY + "?q=" + game; // + "&typesorting=0&direction=ascending&skippos=24&takenum=24";
//        System.out.println("URL: " + url);
        Document doc = Jsoup.connect(url).get();

        Elements elements = extractElements(doc);

        for (Element element : elements) {
            String node = element.attr("data-product");
            Type dataProductDtoType = new TypeToken<List<DataProduct>>(){}.getType();
            List<DataProduct> dtos = new Gson().fromJson(node, dataProductDtoType);
            System.out.println(dtos);

            String gamePageUrl = SCHEME + DOMAIN_IT +
                    element.getElementsByClass("searchProductImage")
                    .get(0)
                    .getElementsByTag("a")
                    .get(0)
                    .attr("href");

            System.out.println("game page url: " + gamePageUrl);

            String imageUrl = element.getElementsByClass("searchProductImage")
                    .get(0)
                    .getElementsByTag("img")
                    .get(0)
                    .attr("data-llsrc");

            imageUrl = imageUrl.substring(0, imageUrl.lastIndexOf('/'));
            System.out.println("image url: " + imageUrl);
        }



        return null;
    }

    private static Elements extractElements(Document document) {
        final String clazz = "searchProductTile";
        return document.getElementsByClass(clazz);
    }





}
