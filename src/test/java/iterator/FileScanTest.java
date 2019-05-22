package iterator;

import db.Schema;
import entity.Tuple;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static db.Schema.GENRES;
import static db.Schema.IMDB_ID;
import static db.Schema.MOVIE_ID;
import static db.Schema.RATING;
import static db.Schema.TIMESTAMP;
import static db.Schema.TITLE;
import static db.Schema.TMDB_ID;
import static db.Schema.USER_ID;
import static org.junit.jupiter.api.Assertions.assertEquals;

class FileScanTest {

    Schema schema;

    @BeforeEach
    void setUp() {
        schema = new Schema("/Users/geoffreymoller/Code/database/src/test/resources/next/");
    }

    @Test
    void testLinks() {
        FileScan fs = new FileScan(new Selection("links", p -> true, schema));
        Tuple t = fs.next();

        assertEquals(1, t.get(MOVIE_ID, t.getTableName()));
        assertEquals(114709, t.get(IMDB_ID, t.getTableName()));
        assertEquals(862, t.get(TMDB_ID, t.getTableName()));

        t = fs.next();
        assertEquals(2, t.get(MOVIE_ID, t.getTableName()));
        assertEquals(113497, t.get(IMDB_ID, t.getTableName()));
        assertEquals(8844, t.get(TMDB_ID, t.getTableName()));
    }

    @Test
    void testRatings() {
        FileScan fs = new FileScan(new Selection("ratings", p -> true, schema));
        Tuple t = fs.next();

        assertEquals(1, t.get(USER_ID, t.getTableName()));
        assertEquals(2, t.get(MOVIE_ID, t.getTableName()));
        assertEquals(1d, t.get(RATING, t.getTableName()));
        assertEquals(1112486027, t.get(TIMESTAMP, t.getTableName()));

        t = fs.next();
        assertEquals(1, t.get(USER_ID, t.getTableName()));
        assertEquals(29, t.get(MOVIE_ID, t.getTableName()));
        assertEquals(2d, t.get(RATING, t.getTableName()));
        assertEquals(1112484676, t.get(TIMESTAMP, t.getTableName()));
    }

    @Test
    void testMovies() {
        FileScan fs = new FileScan(new Selection("movies", p -> true, schema));
        Tuple t = fs.next();

        assertEquals(1, t.get(MOVIE_ID, t.getTableName()));
        assertEquals("Toy Story (1995)", t.get(TITLE, t.getTableName()));
        assertEquals("Adventure|Animation|Children|Comedy|Fantasy", t.get(GENRES, t.getTableName()));

        t = fs.next();
        assertEquals(2, t.get(MOVIE_ID, t.getTableName()));
        assertEquals("Jumanji (1995)", t.get(TITLE, t.getTableName()));
        assertEquals("Adventure|Children|Fantasy", t.get(GENRES, t.getTableName()));
    }

}