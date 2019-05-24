package db;

import java.util.LinkedHashMap;
import java.util.Map;

public class Schema {


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

    public Schema(String path) {
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
        ratingsFields.put(USER_ID, new Field(RATINGS, USER_ID, Integer.TYPE, false));
        ratingsFields.put(MOVIE_ID, new Field(RATINGS, MOVIE_ID, Integer.TYPE, false));
        ratingsFields.put(RATING, new Field(RATINGS, RATING, Double.TYPE, false));
        ratingsFields.put(TIMESTAMP, new Field(RATINGS, TIMESTAMP, Integer.TYPE, false));
        Table ratings = new Table(RATINGS, 1, DatabaseProtos.Rating.class, ratingsFields);

        LinkedHashMap<String, Field> moviesFields = new LinkedHashMap<>();
        moviesFields.put(MOVIE_ID, new Field(MOVIES, MOVIE_ID, Integer.TYPE, false));
        moviesFields.put(TITLE, new Field(MOVIES, TITLE, String.class, false));
        moviesFields.put(GENRES, new Field(MOVIES, GENRES, String.class, false));
        Table movies = new Table(MOVIES, 2, DatabaseProtos.Movie.class, moviesFields);

        LinkedHashMap<String, Field> linksFields = new LinkedHashMap<>();
        linksFields.put(MOVIE_ID, new Field(LINKS, MOVIE_ID, Integer.TYPE, false));
        linksFields.put(IMDB_ID, new Field(LINKS, IMDB_ID, Integer.TYPE, false));
        linksFields.put(TMDB_ID, new Field(LINKS, TMDB_ID, Integer.TYPE, false));
        Table links = new Table(LINKS, 3, DatabaseProtos.Link.class, linksFields);

        LinkedHashMap<String, Field> tagsFields = new LinkedHashMap<>();
        tagsFields.put(USER_ID, new Field(TAGS, USER_ID, Integer.TYPE, false));
        tagsFields.put(MOVIE_ID, new Field(TAGS, MOVIE_ID, Integer.TYPE, false));
        tagsFields.put(TAG, new Field(TAGS, TAG, String.class, false));
        tagsFields.put(TIMESTAMP, new Field(TAGS, TIMESTAMP, Integer.TYPE, false));
//        Table tags = new Table(TAGS, 4, DatabaseProtos.Tag.class, tagsFields);

        LinkedHashMap<String, Table> m = new LinkedHashMap<>();
        m.put(RATINGS, ratings);
        m.put(MOVIES, movies);
        m.put(LINKS, links);
//        m.put(TAGS, tags);

        return m;

    }

}
