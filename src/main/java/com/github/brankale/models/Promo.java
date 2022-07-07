package com.github.brankale.models;

public class Promo {

    private final String type;      // ex: Bundles, Promo
    private final String message;
    private String link;

    public Promo(String type, String message) {
        this.type = type;
        this.message = message;
    }

    public String getType() {
        return type;
    }

    public String getMessage() {
        return message;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

}
