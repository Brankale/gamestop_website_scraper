package main.models;

import main.models.price.Price;

import java.util.ArrayList;

public class GamePreview {

    private final int id;
    private final String title;
    private final ArrayList<Price> prices;
    private final String platform;    // do not use enum because if a new console is released it must be added
    private final String coverUrl;

    public GamePreview(Game game) {
        id = game.getId();
        title = game.getTitle();
        prices = game.getPrices();
        platform = game.getPlatform();
        coverUrl = game.getCoverUrl();
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public ArrayList<Price> getPrices() {
        return prices;
    }

    public String getPlatform() {
        return platform;
    }

    public String getCoverUrl() {
        return coverUrl;
    }

}