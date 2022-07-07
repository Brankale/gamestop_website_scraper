package com.github.brankale.models;

import com.github.brankale.models.price.Price;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Game {

    private final int id;

    // optional attributes
    private final String title;
    private final String publisher;
    private final String platform;
    private final String coverUrl;
    private final List<Price> prices;

    private final String shippingDetails;
    private final List<String> genres;
    private final String officialSite;
    private final String players;
    private final String release;
    private final List<Pegi> pegi;
    private final boolean validForPromotions;

    // TODO: add bonus/promos
    private final String description;
    private final List<String> media;

    public static class Builder {

        private final int id;

        // optional attributes
        private String title;
        private String publisher;
        private String platform;
        private String coverUrl;
        private List<Price> prices;

        private String shippingDetails;
        private List<String> genres;
        private String officialSite;
        private String players;
        private String release;
        private List<Pegi> pegi;
        private boolean validForPromotions;

        // TODO: add bonus/promos
        private String description;
        private List<String> media;

        public Builder(int id) {
            this.id = id;
        }

        public Game build() {
            return new Game(this);
        }

        public Builder setTitle(String title) {
            this.title = title;
            return this;
        }

        public Builder setPublisher(String publisher) {
            this.publisher = publisher;
            return this;
        }

        public Builder setPlatform(String platform) {
            this.platform = platform;
            return this;
        }

        public Builder setCoverUrl(String coverUrl) {
            this.coverUrl = coverUrl;
            return this;
        }

        public Builder addPrices(List<Price> prices) {
            for (Price price : prices)
                addPrice(price);
            return this;
        }

        public Builder addPrice(Price price) {
            if (prices == null)
                prices = new ArrayList<>();
            prices.add(price);
            return this;
        }

        public Builder setShippingDetails(String shippingDetails) {
            this.shippingDetails = shippingDetails;
            return this;
        }

        public Builder addGenre(String genre) {
            if (genres == null)
                genres = new ArrayList<>();
            genres.add(genre);
            return this;
        }

        public Builder setOfficialSite(String officialSite) {
            this.officialSite = officialSite;
            return this;
        }

        public Builder setPlayers(String players) {
            this.players = players;
            return this;
        }

        public Builder setRelease(String release) {
            this.release = release;
            return this;
        }

        public Builder addPegi(Pegi pegi) {
            if (this.pegi == null)
                this.pegi = new ArrayList<>();
            this.pegi.add(pegi);
            return this;
        }

        public Builder isValidForPromotions(boolean validForPromotions) {
            this.validForPromotions = validForPromotions;
            return this;
        }

        public Builder setDescription(String description) {
            this.description = description;
            return this;
        }

        public Builder addMedia(String media) {
            if (this.media == null)
                this.media = new ArrayList<>();
            this.media.add(media);
            return this;
        }

    }

    private Game(Builder builder) {
        id = builder.id;

        title = builder.title;
        publisher = builder.publisher;
        platform = builder.platform;
        coverUrl = builder.coverUrl;
        prices = builder.prices;

        shippingDetails = builder.shippingDetails;
        genres = builder.genres;
        officialSite = builder.officialSite;
        players = builder.players;
        release = builder.release;
        pegi = builder.pegi;
        validForPromotions = builder.validForPromotions;

        // TODO: add bonus/promos
        description = builder.description;
        media = builder.media;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        if (title == null)
            return "";
        return title;
    }

    public String getPublisher() {
        if (publisher == null)
            return "";
        return publisher;
    }

    public String getPlatform() {
        if (platform == null)
            return "";
        return platform;
    }

    public String getCoverUrl() {
        if (coverUrl == null)
            return "";
        return coverUrl;
    }

    public List<Price> getPrices() {
        return prices;
    }

    public String getShippingDetails() {
        if (shippingDetails == null)
            return "";
        return shippingDetails;
    }

    public List<String> getGenres() {
        return genres;
    }

    public String getOfficialSite() {
        if (officialSite == null)
            return "";
        return officialSite;
    }

    public String getPlayers() {
        if (players == null)
            return "";
        return players;
    }

    public String getRelease() {
        if (release == null)
            return "";
        return release;
    }

    public List<Pegi> getPegi() {
        return pegi;
    }

    public boolean isValidForPromotions() {
        return validForPromotions;
    }

    public String getDescription() {
        if (description == null)
            return "";
        return description;
    }

    public List<String> getMedia() {
        return media;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Game game = (Game) o;
        return id == game.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}
