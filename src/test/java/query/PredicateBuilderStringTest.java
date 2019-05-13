package query;

import entity.Movie;
import org.junit.jupiter.api.Test;

import java.util.function.Predicate;

import static org.junit.jupiter.api.Assertions.*;

class PredicateBuilderStringTest {

    @Test
    void testStringEqualityTrue() {
        Movie m = new Movie("1,Fandango,Comedy");
        Predicate<Movie> p = new PredicateBuilder<Movie>().build(m, "title = Fandango");
        assertTrue(p.test(m));
    }

    @Test
    void testStringEqualityFalse() {
        Movie m = new Movie("1,Fandango,Comedy");
        Predicate<Movie> p = new PredicateBuilder<Movie>().build(m, "title = Stripes");
        assertFalse(p.test(m));
    }

    @Test
    void testStringInequalityTrue() {
        Movie m = new Movie("1,Fandanga,Comedy");
        Predicate<Movie> p = new PredicateBuilder<Movie>().build(m, "title != Fandango");
        assertTrue(p.test(m));
    }

    @Test
    void testStringInequalityFalse() {
        Movie m = new Movie("1,Fandango,Comedy");
        Predicate<Movie> p = new PredicateBuilder<Movie>().build(m, "title != Fandango");
        assertFalse(p.test(m));
    }

    @Test
    void testStringLessThanTrue() {
        Movie m = new Movie("1,Fandango,Comedy");
        Predicate<Movie> p = new PredicateBuilder<Movie>().build(m, "title < Fandangy");
        assertTrue(p.test(m));
    }

    @Test
    void testStringLessThanFalse() {
        Movie m = new Movie("1,Fandango,Comedy");
        Predicate<Movie> p = new PredicateBuilder<Movie>().build(m, "title < Fandanga");
        assertFalse(p.test(m));
    }

    @Test
    void testStringGreaterThanTrue() {
        Movie m = new Movie("1,Fandango,Comedy");
        Predicate<Movie> p = new PredicateBuilder<Movie>().build(m, "title > Fandanga");
        assertTrue(p.test(m));
    }

    @Test
    void testStringGreaterThanFalse() {
        Movie m = new Movie("1,Fandango,Comedy");
        Predicate<Movie> p = new PredicateBuilder<Movie>().build(m, "title > Fandangy");
        assertFalse(p.test(m));
    }

}