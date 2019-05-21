package iterator;

import db.Database;
import entity.Tuple;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Map;
import java.util.function.Predicate;

import static db.Database.IMDB_ID;
import static db.Database.LINKS;
import static db.Database.MOVIE_ID;
import static org.junit.jupiter.api.Assertions.assertEquals;

class ProjectionTest {

    private Database db;

    @BeforeEach
    void setUp() {
        db = new Database("/Users/geoffreymoller/Code/database/src/test/resources/next/");
    }

    @Test
    void next() {
        Predicate<Tuple> predicate = item -> true;
        Selection selection = new Selection(LINKS, predicate, db);
        Projection p = new Projection(selection, "movieId,imdbId");
        Tuple t = p.next();
        Map<String, Map<String, Tuple.FieldMap>> attributes = t.getAttributeMap();

        assertEquals(1, attributes.keySet().size());
        assertEquals(2, attributes.get(LINKS).size());

        assertEquals("1", attributes.get(LINKS).get(MOVIE_ID).getAttribute());
        assertEquals(MOVIE_ID, attributes.get(LINKS).get(MOVIE_ID).getField().getName());
        assertEquals(Integer.TYPE, attributes.get(LINKS).get(MOVIE_ID).getField().getType());
        assertEquals(1, t.get(MOVIE_ID));

        assertEquals("0114709", attributes.get(LINKS).get(IMDB_ID).getAttribute());
        assertEquals(IMDB_ID, attributes.get(LINKS).get(IMDB_ID).getField().getName());
        assertEquals(Integer.TYPE, attributes.get(LINKS).get(IMDB_ID).getField().getType());
        assertEquals(114709, t.get(IMDB_ID));
    }

}