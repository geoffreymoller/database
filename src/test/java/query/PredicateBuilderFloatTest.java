package query;

import db.Schema;
import entity.Tuple;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.function.Predicate;

import static db.Schema.RATINGS;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class PredicateBuilderFloatTest {

    private Schema schema;

    @BeforeEach
    void setUp() {
        schema = new Schema("/Users/geoffreymoller/Code/database/src/test/resources/next/");
    }

    @Test
    void testFloatEqualityTrue() {
        Tuple t = new Tuple(schema, RATINGS, "1,2,3.5,1112486027");
        Predicate<Tuple> p = new PredicateBuilder().build(t, "rating = 3.5");
        assertTrue(p.test(t));
    }

    @Test
    void testFloatEqualityFalse() {
        Tuple t = new Tuple(schema, RATINGS, "1,2,3.5,1112486027");
        Predicate<Tuple> p = new PredicateBuilder().build(t, "rating = 3.4");
        assertFalse(p.test(t));
    }

    @Test
    void testFloatInequalityTrue() {
        Tuple t = new Tuple(schema, RATINGS, "1,2,3.5,1112486027");
        Predicate<Tuple> p = new PredicateBuilder().build(t, "rating != 3.4");
        assertTrue(p.test(t));
    }

    @Test
    void testFloatInequalityFalse() {
        Tuple t = new Tuple(schema, RATINGS, "1,2,3.5,1112486027");
        Predicate<Tuple> p = new PredicateBuilder().build(t, "rating != 3.5");
        assertFalse(p.test(t));
    }

    @Test
    void testFloatLessThanTrue() {
        Tuple t = new Tuple(schema, RATINGS, "1,2,3.5,1112486027");
        Predicate<Tuple> p = new PredicateBuilder().build(t, "rating < 3.6");
        assertTrue(p.test(t));
    }

    @Test
    void testFloatLessThanFalse() {
        Tuple t = new Tuple(schema, RATINGS, "1,2,3.5,1112486027");
        Predicate<Tuple> p = new PredicateBuilder().build(t, "rating < 3.5");
        assertFalse(p.test(t));
    }

    @Test
    void testFloatGreaterThanTrue() {
        Tuple t = new Tuple(schema, RATINGS, "1,2,3.5,1112486027");
        Predicate<Tuple> p = new PredicateBuilder().build(t, "rating > 3.4");
        assertTrue(p.test(t));
    }

    @Test
    void testFloatGreaterThanFalse() {
        Tuple t = new Tuple(schema, RATINGS, "1,2,3.5,1112486027");
        Predicate<Tuple> p = new PredicateBuilder().build(t, "rating > 3.5");
        assertFalse(p.test(t));
    }
}