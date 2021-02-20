package main.models;

import com.sun.istack.internal.NotNull;
import com.sun.istack.internal.Nullable;
import main.models.price.Price;
import main.models.price.Prices;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Objects;

public class GamePreview {

    private final int id;
    private String title;
    private Prices prices;
    private String platform;    // do not use enum because if a new console is released it must be added
    private String publisher;
    private String releaseDate;
    private String coverUrl;

    /**
     * @param id of the game.
     */
    public GamePreview(int id) {
        this.id = id;
        prices = new Prices();
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

    public void setPrices(@Nullable Prices prices) {
        this.prices = prices;
    }

    @NotNull
    public Prices getPrices() {
        if (prices == null)
            return new Prices();
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

    /**
     * @param o a GamePreview object
     * @return true if the GamePreview objects have the same ID, false otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GamePreview that = (GamePreview) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}