package main.models;

import com.sun.istack.internal.NotNull;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Objects;

public class GamePreview {

    private final int id;
    private String title;
    private String platform;    // do not use enum because if a new console is released it must be added
    private String publisher;
    private String releaseDate;
    private String coverUrl;

    /**
     * @param id of the game. ID cannot be negative.
     */
    public GamePreview(int id) {
        this.id = id;
        if (id < 0)
            throw new InvalidGamePreviewIdException(id);
    }

    public int getId() {
        return id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @NotNull
    public String getTitle() {
        if (title == null)
            return "";
        return title;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    @NotNull
    public String getPlatform() {
        if (platform == null)
            return "";
        return platform;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    @NotNull
    public String getPublisher() {
        if (publisher == null)
            return "";
        return publisher;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    @NotNull
    public String getReleaseDate() {
        if (releaseDate == null)
            return "";
        return releaseDate;
    }

    public boolean setCoverUrl(String url) {
        if (isValidUrl(url)) {
            coverUrl = url;
            return true;
        } else {
            return false;
        }
    }

    @NotNull
    public String getCoverUrl() {
        if (coverUrl == null)
            return "";
        return coverUrl;
    }

    private boolean isValidUrl(String url) {
        try {
            new URL(url);
            return true;
        } catch (MalformedURLException ex) {
            return false;
        }
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

    public static class InvalidGamePreviewIdException extends RuntimeException {
        private final int id;

        public InvalidGamePreviewIdException(int id) {
            this.id = id;
        }

        @Override
        public String toString() {
            return "GamePreview ID can't be negative. Given value = " + id;
        }
    }

}

