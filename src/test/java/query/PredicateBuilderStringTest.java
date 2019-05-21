package query;

import db.Database;
import entity.Movie;
import entity.Tuple;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.function.Predicate;

import static db.Database.MOVIES;
import static org.junit.jupiter.api.Assertions.*;

class PredicateBuilderStringTest {

    private Database db;

    @BeforeEach
    void setUp() {
        db = new Database("/Users/geoffreymoller/Code/database/src/test/resources/next/");
    }

    @Test
    void testStringEqualityTrue() {
        Tuple t = new Tuple(db, MOVIES, "1,Fandango,Comedy");
        Predicate<Tuple> p = new PredicateBuilder().build(t, "title = Fandango");
        assertTrue(p.test(t));
    }

    @Test
    void testStringEqualityFalse() {
        Tuple t = new Tuple(db, MOVIES, "1,Fandango,Comedy");
        Predicate<Tuple> p = new PredicateBuilder().build(t, "title = Stripes");
        assertFalse(p.test(t));
    }

    @Test
    void testStringInequalityTrue() {
        Tuple t = new Tuple(db, MOVIES, "1,Fandanga,Comedy");
        Predicate<Tuple> p = new PredicateBuilder().build(t, "title != Fandango");
        assertTrue(p.test(t));
    }

    @Test
    void testStringInequalityFalse() {
        Tuple t = new Tuple(db, MOVIES, "1,Fandango,Comedy");
        Predicate<Tuple> p = new PredicateBuilder().build(t, "title != Fandango");
        assertFalse(p.test(t));
    }

    @Test
    void testStringLessThanTrue() {
        Tuple t = new Tuple(db, MOVIES, "1,Fandango,Comedy");
        Predicate<Tuple> p = new PredicateBuilder().build(t, "title < Fandangy");
        assertTrue(p.test(t));
    }

    @Test
    void testStringLessThanFalse() {
        Tuple t = new Tuple(db, MOVIES, "1,Fandango,Comedy");
        Predicate<Tuple> p = new PredicateBuilder().build(t, "title < Fandanga");
        assertFalse(p.test(t));
    }

    @Test
    void testStringGreaterThanTrue() {
        Tuple t = new Tuple(db, MOVIES, "1,Fandango,Comedy");
        Predicate<Tuple> p = new PredicateBuilder().build(t, "title > Fandanga");
        assertTrue(p.test(t));
    }

    @Test
    void testStringGreaterThanFalse() {
        Tuple t = new Tuple(db, MOVIES, "1,Fandango,Comedy");
        Predicate<Tuple> p = new PredicateBuilder().build(t, "title > Fandangy");
        assertFalse(p.test(t));
    }

}