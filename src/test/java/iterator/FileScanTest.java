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

    private Schema schema;
    private int length;

    @BeforeEach
    void setUp() {
        schema = new Schema("/Users/geoffreymoller/Code/database/src/test/resources/next/");
    }

    @Test
    void testLinks() {
        FileScan fs = new FileScan("links", schema);
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
        FileScan fs = new FileScan("ratings", schema);
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
        FileScan fs = new FileScan("movies", schema);
        Tuple t = fs.next();

        assertEquals(1, t.get(MOVIE_ID));
        assertEquals("Toy Story (1995)", t.get(TITLE));
        assertEquals("Adventure|Animation|Children|Comedy|Fantasy", t.get(GENRES));

        t = fs.next();
        assertEquals(2, t.get(MOVIE_ID));
        assertEquals("Jumanji (1995)", t.get(TITLE));
        assertEquals("Adventure|Children|Fantasy", t.get(GENRES));
    }

}