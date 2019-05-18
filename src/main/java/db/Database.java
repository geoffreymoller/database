package db;

import java.util.LinkedHashMap;
import java.util.Map;

import static java.util.Map.entry;

public class Database {


    public static final String RATINGS = "ratings";
    public static final String MOVIES = "movies";
    public static final String LINKS = "links";
    public static final String TAGS = "tags";

    public static final String TIMESTAMP = "timestamp";
    public static final String USER_ID = "userId";
    public static final String MOVIE_ID = "movieId";
    public static final String RATING = "rating";
    public static final String TITLE = "title";
    public static final String GENRES = "genres";
    public static final String IMDB_ID = "imdbId";
    public static final String TMDB_ID = "tmdbId";
    public static final String TAG = "tag";

    private final String path;

    private Map<String, Table> schema;

    public Database(String path) {
        this.path = path;
        this.schema = buildSchema();
    }

    public String getPath() {
        return path;
    }

    public Map<String, Table> getSchema() {
        return schema;
    }

    private Map<String, Table> buildSchema() {

        LinkedHashMap<String, Field> ratingsFields = new LinkedHashMap<>();
        ratingsFields.put(USER_ID, new Field(1, USER_ID, Integer.TYPE, false));
        ratingsFields.put(MOVIE_ID, new Field(2, MOVIE_ID, Integer.TYPE, false));
        ratingsFields.put(RATING, new Field(3, RATING, Double.TYPE, false));
        ratingsFields.put(TIMESTAMP, new Field(4, TIMESTAMP, Integer.TYPE, false));

        Table ratings = new Table(RATINGS, 1, ratingsFields);

        LinkedHashMap<String, Field> moviesFields = new LinkedHashMap<>();
        moviesFields.put(MOVIE_ID, new Field(1, MOVIE_ID, Integer.TYPE, false));
        moviesFields.put(TITLE, new Field(2, TITLE, String.class, false));
        moviesFields.put(GENRES, new Field(3, GENRES, String.class, false));
        Table movies = new Table(MOVIES, 2, moviesFields);

        LinkedHashMap<String, Field> linksFields = new LinkedHashMap<>();
        linksFields.put(MOVIE_ID, new Field(1, MOVIE_ID, Integer.TYPE, false));
        linksFields.put(IMDB_ID, new Field(2, IMDB_ID, Integer.TYPE, false));
        linksFields.put(TMDB_ID, new Field(3, TMDB_ID, Integer.TYPE, false));
        Table links = new Table(LINKS, 3, linksFields);

        LinkedHashMap<String, Field> tagsFields = new LinkedHashMap<>();
        tagsFields.put(USER_ID, new Field(1, USER_ID, Integer.TYPE, false));
        tagsFields.put(MOVIE_ID, new Field(2, MOVIE_ID, Integer.TYPE, false));
        tagsFields.put(TAG, new Field(3, TAG, String.class, false));
        tagsFields.put(TIMESTAMP, new Field(4, TIMESTAMP, Integer.TYPE, false));
        Table tags = new Table(TAGS, 4, tagsFields);

        return Map.ofEntries(
            entry(RATINGS, ratings),
            entry(MOVIES, movies),
            entry(LINKS, links),
            entry(TAGS, tags)
        );

    }

}
