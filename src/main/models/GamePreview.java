package main.models;

import java.util.ArrayList;
import java.util.Objects;

public class GamePreview {

    private final int id;
    private String title;
    private String publisher;
    private String platform;
    private Prices prices;

    public GamePreview(int id) {
        this.id = id;
    }

    public final int getId() {
        return id;
    }

    public final String getTitle() {
        return title;
    }

    public final void setTitle(String title) {
        this.title = title;
    }

    public final String getPublisher() {
        return publisher;
    }

    public final void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public final String getPlatform() {
        return platform;
    }

    public final void setPlatform(String platform) {
        this.platform = platform;
    }

    public Prices getPrices() {
        return prices;
    }

    public void setPrices(Prices prices) {
        this.prices = prices;
    }

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GamePreview that = (GamePreview) o;
        return id == that.id;
    }

    @Override
    public final int hashCode() {
        return Objects.hash(id);
    }

}
