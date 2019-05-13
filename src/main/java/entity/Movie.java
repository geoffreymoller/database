package entity;

public final class Movie implements Entity {

    private final int movieId;
    private final String title;
    private final String genres;

    public int getMovieId() {
        return movieId;
    }

    public String getTitle() {
        return title;
    }

    public String getGenres() {
        return genres;
    }

    public Movie(String p) {
        String[] parts = p.split(",");
        movieId = Integer.parseInt(parts[0]);
        title = parts[1];
        genres = parts[2];
    }

    @Override
    public String getName() {
        return "movie   ";
    }
}

