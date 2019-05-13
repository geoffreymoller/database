package entity;

public final class Link implements Entity {

    private final int movieId;
    private final int imdbId;
    private final int tmdbId;

    public String getName() {
        return "link";
    }

    public int getMovieId() {
        return movieId;
    }

    public int getImdbId() {
        return imdbId;
    }

    public int getTmdbId() {
        return tmdbId;
    }

    public Link(String p) {
        String[] parts = p.split(",");
        movieId = Integer.parseInt(parts[0]);
        imdbId = Integer.parseInt(parts[1]);
        tmdbId = (parts.length == 3) ? Integer.parseInt(parts[2]) : 0;
    }

}

