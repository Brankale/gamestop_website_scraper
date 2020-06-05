package main;

public class Gamestop {

    private String website = "www.gamestop";

    public enum Country {
        AUSTRIA,
        ITALY,
        SWITZERLAND,
        DENMARK,
        GERMANY,
        NORWAY,
        SWEDEN,
        FINLAND,
        IRELAND
    }

    public Gamestop(Country region) {
        switch (region) {
            case AUSTRIA:       website += ".at"; break;
            case ITALY:         website += ".it"; break;
            case SWITZERLAND:   website += ".ch"; break;
            case DENMARK:       website += ".dk"; break;
            case GERMANY:       website += ".de"; break;
            case NORWAY:        website += ".no"; break;
            case SWEDEN:        website += ".se"; break;
            case FINLAND:       website += ".fi"; break;
            case IRELAND:       website += ".ie"; break;
        }
    }

    public void search() {
        // TODO ("to implement")
    }

    public void findById() {
        // TODO ("to implement")
    }

}
