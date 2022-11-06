package com.github.brankale.parsers;

import com.github.brankale.dto.GamePageDto;
import com.github.brankale.models.Game;
import com.google.gson.Gson;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GamePageParser {

    private static final String SCHEME = "https://";
    private static final String DOMAIN_IT = "www.gamestop.it";
    private static final String SUB_DIRECTORY = "/Platform/Games/";

    public static List<Game> parse(int gameId) throws IOException {
        String url = SCHEME + DOMAIN_IT + SUB_DIRECTORY + gameId;
        Document document = Jsoup.connect(url).get();

        Element script = findScriptWithJsonInfo(document);
        String html = script.html();
        List<GamePageDto> dtos = new ArrayList<>();
        dtos = new Gson().fromJson(html, dtos.getClass());
        System.out.println(dtos);


        return null;
    }

    private static Element findScriptWithJsonInfo(Document document) {
        Elements scripts = document.getElementsByTag("script");
        for (Element script : scripts)
            if (script.attr("type").equals("application/ld+json"))
                return script;
        return null;
    }

}
