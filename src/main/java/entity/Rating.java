package entity;

public final class Rating implements Entity {

    private final int userId;
    private final int movieId;
    private final float rating;
    private final int timeStamp;

    public int getUserId() {
        return userId;
    }

    public int getMovieId() {
        return movieId;
    }

    public float getRating() {
        return rating;
    }

    public int getTimeStamp() {
        return timeStamp;
    }

    public Rating(String p) {
        String[] parts = p.split(",");
        userId = Integer.parseInt(parts[0]);
        movieId = Integer.parseInt(parts[1]);
        rating = Float.parseFloat(parts[2]);
        timeStamp = Integer.parseInt(parts[3]);
    }

    @Override
    public String getName() {
        return "movie   ";
    }
}

