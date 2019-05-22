package iterator;

import db.Schema;
import entity.Tuple;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.function.Predicate;

import static db.Schema.LINKS;
import static db.Schema.MOVIE_ID;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class SelectionTest {

    Schema schema;

    @BeforeEach
    void setUp() {
       schema = new Schema("/Users/geoffreymoller/Code/database/src/test/resources/next/");
    }

    @Test
    void testQuery() {
        Predicate<Tuple> p = t -> (Integer) t.get(MOVIE_ID, t.getTableName()) == 2;
        Selection selection = new Selection(LINKS, p, schema);
        Tuple t = selection.next();
        assertEquals(2, t.get(MOVIE_ID, t.getTableName()));
    }

    @Test
    void testNextExists() {
        Predicate<Tuple> p = t -> (Integer) t.get(MOVIE_ID, t.getTableName()) == 2;
        Selection selection = new Selection(LINKS, p, schema);
        assertEquals(2, selection.next().get(MOVIE_ID, selection.getTableName()));
    }

    @Test
    void testNextNotExists() {
        Predicate<Tuple> p = t -> (Integer) t.get(MOVIE_ID, t.getTableName()) == Integer.MAX_VALUE;
        Selection selection = new Selection(LINKS, p, schema);
        assertNull(selection.next());
    }

}