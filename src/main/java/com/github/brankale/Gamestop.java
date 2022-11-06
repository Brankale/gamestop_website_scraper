package com.github.brankale;


public class Gamestop {

    private String website = "www.gamestop";

    public enum Country {
        AUSTRIA,
        ITALY,
        SWITZERLAND,
        GERMANY,
        IRELAND
    }

    public Gamestop(Country region) {
        switch (region) {
            case AUSTRIA:       website += ".at"; break;
            case ITALY:         website += ".it"; break;
            case SWITZERLAND:   website += ".ch"; break;
            case GERMANY:       website += ".de"; break;
            case IRELAND:       website += ".ie"; break;
        }
    }


    private String getSearchQuery(String searched) {
        return website + "?q=" + searched;
    }

    public void findById() {
        // TODO ("to implement")
    }

}
