package query;

import entity.Rating;
import org.junit.jupiter.api.Test;

import java.util.function.Predicate;

import static org.junit.jupiter.api.Assertions.*;

class PredicateBuilderFloatTest {

    @Test
    void testFloatEqualityTrue() {
        Rating r = new Rating("1,2,3.5,1112486027");
        Predicate<Rating> p = new PredicateBuilder<Rating>().build(r, "rating = 3.5");
        assertTrue(p.test(r));
    }

    @Test
    void testFloatEqualityFalse() {
        Rating r = new Rating("1,2,3.5,1112486027");
        Predicate<Rating> p = new PredicateBuilder<Rating>().build(r, "rating = 3.4");
        assertFalse(p.test(r));
    }

    @Test
    void testFloatInequalityTrue() {
        Rating r = new Rating("1,2,3.5,1112486027");
        Predicate<Rating> p = new PredicateBuilder<Rating>().build(r, "rating != 3.4");
        assertTrue(p.test(r));
    }

    @Test
    void testFloatInequalityFalse() {
        Rating r = new Rating("1,2,3.5,1112486027");
        Predicate<Rating> p = new PredicateBuilder<Rating>().build(r, "rating != 3.5");
        assertFalse(p.test(r));
    }

    @Test
    void testFloatLessThanTrue() {
        Rating r = new Rating("1,2,3.5,1112486027");
        Predicate<Rating> p = new PredicateBuilder<Rating>().build(r, "rating < 3.6");
        assertTrue(p.test(r));
    }

    @Test
    void testFloatLessThanFalse() {
        Rating r = new Rating("1,2,3.5,1112486027");
        Predicate<Rating> p = new PredicateBuilder<Rating>().build(r, "rating < 3.5");
        assertFalse(p.test(r));
    }

    @Test
    void testFloatGreaterThanTrue() {
        Rating r = new Rating("1,2,3.5,1112486027");
        Predicate<Rating> p = new PredicateBuilder<Rating>().build(r, "rating > 3.4");
        assertTrue(p.test(r));
    }

    @Test
    void testFloatGreaterThanFalse() {
        Rating r = new Rating("1,2,3.5,1112486027");
        Predicate<Rating> p = new PredicateBuilder<Rating>().build(r, "rating > 3.5");
        assertFalse(p.test(r));
    }
}