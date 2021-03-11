package main.models;

import com.sun.istack.internal.NotNull;
import com.sun.istack.internal.Nullable;

import java.util.ArrayList;
import java.util.List;

public class Game extends GamePreview {

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

    private final List<String> gallery;
    private final List<Pegi> pegi;
    private final List<String> genres;
    private String officialSite;
    // private List<Pair<String,Integer>> productCode;  // TODO: is it really useful?
    private String players;
    private boolean validForPromotions;
    private String description;                         // TODO: should be HTML?

    public Game(int id) {
        super(id);
        gallery = new ArrayList<>();
        pegi = new ArrayList<>();
        genres = new ArrayList<>();
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

}
