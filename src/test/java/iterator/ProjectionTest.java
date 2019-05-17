package iterator;

import db.Database;
import entity.Tuple;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Map;
import java.util.function.Predicate;

import static db.Database.LINKS;
import static org.junit.jupiter.api.Assertions.*;

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
        Projection p = new Projection(selection, "movieId");
        Tuple t = p.next();
        Map<String, Tuple.FieldMap> attributes = t.getAttributeMap();

        assertEquals(1, attributes.keySet().size());
        assertEquals("1", attributes.get("movieId").getAttribute());
        assertEquals("movieId", attributes.get("movieId").getField().getName());
        assertEquals(Integer.TYPE, attributes.get("movieId").getField().getType());
        assertEquals(1, t.get("movieId"));
    }

}