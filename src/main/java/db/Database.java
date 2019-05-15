package db;

import java.util.HashMap;
import java.util.Map;

import static java.util.Map.entry;

public class Database {

    private final String path;

    public Database(String path) {
        this.path = path;
        buildSchema();
    }

    public String getPath() {
        return path;
    }

    private void buildSchema() {

        Field field = new Field(1, "userId", Integer.TYPE, false);
        Field field2 = new Field(1, "movieId", Integer.TYPE, false);
        Field field3 = new Field(1, "rating", Float.TYPE, false);
        Field field4 = new Field(1, "timestamp", Integer.TYPE, false);
        Map<String, Field> ratingsFields = Map.ofEntries(
            entry("userId", field),
            entry("movieId", field2),
            entry("rating", field3),
            entry("timestamp", field4)
        );
        Table ratings = new Table("ratings", 1, ratingsFields);

        Field field5 = new Field(2, "movieId", Integer.TYPE, false);
        Field field6 = new Field(2, "title", String.class, false);
        Field field7 = new Field(2, "genres", String.class, false);
        Map<String, Field> moviesFields = Map.ofEntries(
            entry("movieId", field5),
            entry("title", field6),
            entry("genres", field7)
        );
        Table movies = new Table("movies", 1, moviesFields);

        Field field8 = new Field(3, "movieId", Integer.TYPE, false);
        Field field9 = new Field(3, "imdbId", Integer.TYPE, false);
        Field field10 = new Field(3, "tmdbId", Integer.TYPE, false);
        Map<String, Field> linksFields = Map.ofEntries(
            entry("movieId", field8),
            entry("imbdId", field9),
            entry("tmdbId", field10)
        );
        Table links = new Table("links", 1, linksFields);

        Field field11 = new Field(4, "userId", Integer.TYPE, false);
        Field field12 = new Field(4, "movieId", Integer.TYPE, false);
        Field field13 = new Field(4, "tag", String.class, false);
        Field field14 = new Field(4, "timestamp", Integer.TYPE, false);
        Map<String, Field> tagsFields = Map.ofEntries(
            entry("userId", field11),
            entry("movieId", field12),
            entry("tag", field13),
            entry("timestamp", field14)
        );
        Table tags = new Table("tags", 1, tagsFields);

        Map<String, Table> schema = Map.ofEntries(
            entry("ratings", ratings),
            entry("movies", movies),
            entry("links", links),
            entry("tags", tags)
        );

    }

}
