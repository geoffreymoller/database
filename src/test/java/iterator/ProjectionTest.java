package iterator;

import db.Database;
import db.FieldMap;
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

        String movieIdKey = "movieId-links";
        String imdbIdKey = "imdbId-links";

        Predicate<Tuple> predicate = item -> true;
        Selection selection = new Selection(LINKS, predicate, db);
        Projection p = new Projection(selection, movieIdKey+","+imdbIdKey);
        Tuple t = p.next();
        Map<String, FieldMap> attributes = t.getAttributeMap();

        assertEquals(2, attributes.keySet().size());

        FieldMap fieldMap = attributes.get(movieIdKey);
        assertEquals("1", fieldMap.getAttribute());
        assertEquals(MOVIE_ID, fieldMap.getField().getName());
        assertEquals(Integer.TYPE, fieldMap.getField().getType());
        assertEquals(1, t.get(MOVIE_ID, LINKS));

        FieldMap fieldMap1 = attributes.get(imdbIdKey);
        assertEquals("0114709", fieldMap1.getAttribute());
        assertEquals(IMDB_ID, fieldMap1.getField().getName());
        assertEquals(Integer.TYPE, fieldMap1.getField().getType());
        assertEquals(114709, t.get(IMDB_ID, LINKS));
    }

}