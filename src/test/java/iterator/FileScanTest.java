package iterator;

import db.Database;
import entity.Tuple;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static db.Database.GENRES;
import static db.Database.IMDB_ID;
import static db.Database.MOVIE_ID;
import static db.Database.RATING;
import static db.Database.TIMESTAMP;
import static db.Database.TITLE;
import static db.Database.TMDB_ID;
import static db.Database.USER_ID;
import static org.junit.jupiter.api.Assertions.assertEquals;

class FileScanTest {

    Database db;

    @BeforeEach
    void setUp() {
        db = new Database("/Users/geoffreymoller/Code/database/src/test/resources/next/");
    }

    @Test
    void testLinks() {
        FileScan fs = new FileScan("links", db);
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
        FileScan fs = new FileScan("ratings", db);
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
        FileScan fs = new FileScan("movies", db);
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