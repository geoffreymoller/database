package query;

import db.Schema;
import entity.Tuple;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.function.Predicate;

import static db.Schema.LINKS;
import static org.junit.jupiter.api.Assertions.*;

class PredicateBuilderIntTest {

    private Schema schema;

    @BeforeEach
    void setUp() {
        schema = new Schema("/Users/geoffreymoller/Code/database/src/test/resources/next/");
    }

    @Test
    void testIntEqualityTrue() {
        Tuple t = new Tuple(schema, LINKS, "1,1,1");
        Predicate<Tuple> p = new PredicateBuilder().build(t, "movieId = 1");
        assertTrue(p.test(t));
    }

    @Test
    void testIntEqualityFalse() {
        Tuple t = new Tuple(schema, LINKS, "2,1,1");
        Predicate<Tuple> p = new PredicateBuilder().build(t, "movieId = 1");
        assertFalse(p.test(t));
    }

    @Test
    void testIntInequalityTrue() {
        Tuple t = new Tuple(schema, LINKS, "1,1,1");
        Predicate<Tuple> p = new PredicateBuilder().build(t, "movieId != 2");
        assertTrue(p.test(t));
    }

    @Test
    void testIntInequalityFalse() {
        Tuple t = new Tuple(schema, LINKS, "2,1,1");
        Predicate<Tuple> p = new PredicateBuilder().build(t, "movieId != 2");
        assertFalse(p.test(t));
    }

    @Test
    void testIntLessThanTrue() {
        Tuple t = new Tuple(schema, LINKS, "1,1,1");
        Predicate<Tuple> p = new PredicateBuilder().build(t, "movieId < 2");
        assertTrue(p.test(t));
    }

    @Test
    void testIntLessThanFalse() {
        Tuple t = new Tuple(schema, LINKS, "1,1,1");
        Predicate<Tuple> p = new PredicateBuilder().build(t, "movieId < 1");
        assertFalse(p.test(t));
    }

    @Test
    void testIntGreaterThanTrue() {
        Tuple t = new Tuple(schema, LINKS, "1,1,1");
        Predicate<Tuple> p = new PredicateBuilder().build(t, "movieId > 0");
        assertTrue(p.test(t));
    }

    @Test
    void testIntGreaterThanFalse() {
        Tuple t = new Tuple(schema, LINKS, "1,1,1");
        Predicate<Tuple> p = new PredicateBuilder().build(t, "movieId > 1");
        assertFalse(p.test(t));
    }

}