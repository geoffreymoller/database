package iterator;

import db.Database;
import entity.Tuple;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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

        assertEquals(1, t.get(MOVIE_ID));
        assertEquals(114709, t.get(IMDB_ID));
        assertEquals(862, t.get(TMDB_ID));

        t = fs.next();
        assertEquals(2, t.get(MOVIE_ID));
        assertEquals(113497, t.get(IMDB_ID));
        assertEquals(8844, t.get(TMDB_ID));
    }

    @Test
    void testRatings() {
        FileScan fs = new FileScan("ratings", db);
        Tuple t = fs.next();

        assertEquals(1, t.get(USER_ID));
        assertEquals(2, t.get(MOVIE_ID));
        assertEquals(1d, t.get(RATING));
        assertEquals(1112486027, t.get(TIMESTAMP));

        t = fs.next();
        assertEquals(1, t.get(USER_ID));
        assertEquals(29, t.get(MOVIE_ID));
        assertEquals(2d, t.get(RATING));
        assertEquals(1112484676, t.get(TIMESTAMP));
    }

    @Test
    void testMovies() {
        FileScan fs = new FileScan("movies", db);
        Tuple t = fs.next();

        assertEquals(1, t.get(MOVIE_ID));
        assertEquals("Toy Story (1995)", t.get(TITLE));
        assertEquals("Adventure|Animation|Children|Comedy|Fantasy", t.get("genres"));

        t = fs.next();
        assertEquals(2, t.get(MOVIE_ID));
        assertEquals("Jumanji (1995)", t.get(TITLE));
        assertEquals("Adventure|Children|Fantasy", t.get("genres"));
    }

}