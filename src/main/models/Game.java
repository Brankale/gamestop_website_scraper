package main.models;

import com.sun.istack.internal.NotNull;
import com.sun.istack.internal.Nullable;
import main.models.price.Price;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Game {

    public enum Pegi {
        AGE_3, AGE_7, AGE_12, AGE_16, AGE_18,
        BAD_LANGUAGE,
        VIOLENCE,
        ONLINE,
        SEX,
        FEAR,
        DRUGS,
        DISCRIMINATION,
        GAMBLING
    }

    private final int id;
    private String title;
    private ArrayList<Price> prices;
    private String platform;    // do not use enum because if a new console is released it must be added
    private String publisher;
    private String releaseDate;
    private String coverUrl;

    private final List<String> gallery;
    private final List<Pegi> pegi;
    private final List<String> genres;
    private String officialSite;
    // private List<Pair<String,Integer>> productCode;  // TODO: is it really useful?
    private String players;
    private boolean validForPromotions;
    private String description;                         // TODO: should be HTML?

    /**
     * @param id of the game
     */
    public Game(int id) {
        this.id = id;
        prices = new ArrayList<>();
        gallery = new ArrayList<>();
        pegi = new ArrayList<>();
        genres = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public void setTitle(@Nullable String title) {
        this.title = title;
    }

    @NotNull
    public String getTitle() {
        if (title == null)
            return "";
        return title;
    }

    public void setPrices(@Nullable ArrayList<Price> prices) {
        this.prices = prices;
    }

    @NotNull
    public ArrayList<Price> getPrices() {
        if (prices == null)
            return new ArrayList<>();
        return prices;
    }

    public void setPlatform(@Nullable String platform) {
        this.platform = platform;
    }

    @NotNull
    public String getPlatform() {
        if (platform == null)
            return "";
        return platform;
    }

    public void setPublisher(@Nullable String publisher) {
        this.publisher = publisher;
    }

    @NotNull
    public String getPublisher() {
        if (publisher == null)
            return "";
        return publisher;
    }

    public void setReleaseDate(@Nullable String releaseDate) {
        this.releaseDate = releaseDate;
    }

    @NotNull
    public String getReleaseDate() {
        if (releaseDate == null)
            return "";
        return releaseDate;
    }

    public void setCoverUrl(@Nullable String url) {
        coverUrl = url;
    }

    @NotNull
    public String getCoverUrl() {
        if (coverUrl == null)
            return "";
        return coverUrl;
    }

    @NotNull
    public List<String> getGallery() {
        return gallery;
    }
    
    public void addGalleryImage(@Nullable String url) {
        if (url != null && !url.isEmpty())
            gallery.add(url);
    }

    public void addGalleryImages(@NotNull List<String> urls) {
        for (String url : urls)
            addGalleryImage(url);
    }

    @NotNull
    public List<Pegi> getPegi() {
        return pegi;
    }

    public void addPegi(@NotNull Pegi pegi) {
        if (pegi != null)
            this.pegi.add(pegi);
    }

    public void addPegi(@NotNull List<Pegi> pegis) {
        for (Pegi pegi : pegis) {
            addPegi(pegi);
        }
    }

    @NotNull
    public List<String> getGenres() {
        return genres;
    }

    public void addGenre(@Nullable String genre) {
        if (genre != null && !genre.isEmpty())
            genres.add(genre);
    }

    public void addGenres(@NotNull List<String> genres) {
        for (String genre : genres)
            addGenre(genre);
    }

    @NotNull
    public String getOfficialSite() {
        if (officialSite == null)
            return "";
        return officialSite;
    }

    public void setOfficialSite(@Nullable String officialSite) {
        this.officialSite = officialSite;
    }

    @NotNull
    public String getPlayers() {
        if (players == null)
            return "";
        return players;
    }

    public void setPlayers(@Nullable String players) {
        this.players = players;
    }

    public boolean isValidForPromotions() {
        return validForPromotions;
    }

    public void setValidForPromotions(boolean validForPromotions) {
        this.validForPromotions = validForPromotions;
    }

    @NotNull
    public String getDescription() {
        if (description == null)
            return "";
        return description;
    }

    public void setDescription(@Nullable String description) {
        this.description = description;
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
