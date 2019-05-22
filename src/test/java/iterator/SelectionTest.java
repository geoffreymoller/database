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

    private Schema schema;

    @BeforeEach
    void setUp() {
       schema = new Schema("/Users/geoffreymoller/Code/database/src/test/resources/next/");
    }

    @Test
    void testQuery() {
        FileScan fs = new FileScan(LINKS, schema);
        Predicate<Tuple> p = t -> (Integer) t.get(MOVIE_ID) == 2;
        Selection selection = new Selection(p, fs, schema);
        Tuple t = selection.next();
        assertEquals(2, t.get(MOVIE_ID));
    }

    @Test
    void testNextExists() {
        FileScan fs = new FileScan(LINKS, schema);
        Predicate<Tuple> p = t -> (Integer) t.get(MOVIE_ID) == 2;
        Selection selection = new Selection(p, fs, schema);
        assertEquals(2, selection.next().get(MOVIE_ID));
    }

    @Test
    void testNextNotExists() {
        FileScan fs = new FileScan(LINKS, schema);
        Predicate<Tuple> p = t -> (Integer) t.get(MOVIE_ID) == Integer.MAX_VALUE;
        Selection selection = new Selection(p, fs, schema);
        assertNull(selection.next());
    }

}