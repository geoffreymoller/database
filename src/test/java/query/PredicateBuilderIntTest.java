package query;

import entity.Link;
import org.junit.jupiter.api.Test;

import java.util.function.Predicate;

import static org.junit.jupiter.api.Assertions.*;

class PredicateBuilderIntTest {

    @Test
    void testIntEqualityTrue() {
        Link l = new Link("1,1,1");
        Predicate<Link> p = new PredicateBuilder<Link>().build(l, "movieId = 1");
        assertTrue(p.test(l));
    }

    @Test
    void testIntEqualityFalse() {
        Link l = new Link("2,1,1");
        Predicate<Link> p = new PredicateBuilder<Link>().build(l, "movieId = 1");
        assertFalse(p.test(l));
    }

    @Test
    void testIntInequalityTrue() {
        Link l = new Link("1,1,1");
        Predicate<Link> p = new PredicateBuilder<Link>().build(l, "movieId != 2");
        assertTrue(p.test(l));
    }

    @Test
    void testIntInequalityFalse() {
        Link l = new Link("2,1,1");
        Predicate<Link> p = new PredicateBuilder<Link>().build(l, "movieId != 2");
        assertFalse(p.test(l));
    }

    @Test
    void testIntLessThanTrue() {
        Link l = new Link("1,1,1");
        Predicate<Link> p = new PredicateBuilder<Link>().build(l, "movieId < 2");
        assertTrue(p.test(l));
    }

    @Test
    void testIntLessThanFalse() {
        Link l = new Link("1,1,1");
        Predicate<Link> p = new PredicateBuilder<Link>().build(l, "movieId < 1");
        assertFalse(p.test(l));
    }

    @Test
    void testIntGreaterThanTrue() {
        Link l = new Link("1,1,1");
        Predicate<Link> p = new PredicateBuilder<Link>().build(l, "movieId > 0");
        assertTrue(p.test(l));
    }

    @Test
    void testIntGreaterThanFalse() {
        Link l = new Link("1,1,1");
        Predicate<Link> p = new PredicateBuilder<Link>().build(l, "movieId > 1");
        assertFalse(p.test(l));
    }

}