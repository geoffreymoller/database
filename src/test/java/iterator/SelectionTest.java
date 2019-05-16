package iterator;

import db.Database;
import entity.Link;
import entity.Tuple;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.function.Predicate;

import static db.Database.LINKS;
import static db.Database.MOVIE_ID;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class SelectionTest {

    Database db;

    @BeforeEach
    void setUp() {
       db = new Database("/Users/geoffreymoller/Code/database/src/test/resources/next/");
    }

    @Test
    void testQuery() {
        Predicate<Tuple> p = t -> (Integer) t.get(MOVIE_ID) == 2;
        Selection selection = new Selection(LINKS, p, db);
        Tuple t = selection.next();
        assertEquals(2, t.get(MOVIE_ID));
    }

    @Test
    void testNextExists() {
        Predicate<Tuple> p = t -> (Integer) t.get(MOVIE_ID) == 2;
        Selection selection = new Selection(LINKS, p, db);
        assertEquals(2, selection.next().get(MOVIE_ID));
    }

    @Test
    void testNextNotExists() {
        Predicate<Tuple> p = t -> (Integer) t.get(MOVIE_ID) == Integer.MAX_VALUE;
        Selection selection = new Selection(LINKS, p, db);
        assertNull(selection.next());
    }

}