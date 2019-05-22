package iterator;

import db.Schema;
import entity.Tuple;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.function.BiFunction;
import java.util.function.Predicate;

import static db.Schema.LINKS;
import static db.Schema.MOVIES;
import static db.Schema.MOVIE_ID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class JoinTest {

    private Schema schema;

    @BeforeEach
    void setUp() {
        schema = new Schema("/Users/geoffreymoller/Code/database/src/test/resources/next/");
    }

    @Test
    void next() {
        Predicate<Tuple> p = t -> true;
        Selection selectionLinks = new Selection(LINKS, p, schema);
        Selection selectionMovies = new Selection(MOVIES, p, schema);

        BiFunction<Tuple, Tuple, Boolean> joinPredicate = (tuple, tuple2) ->
            tuple.get(MOVIE_ID, LINKS) == tuple2.get(MOVIE_ID, MOVIES);

        Join j = new Join(selectionLinks, selectionMovies, joinPredicate, schema);
        Tuple t = j.next();
        int i = 0;
        while (t != null) {
            i++;
            t = j.next();
        }
        assertEquals(9, i);
        assertNull(j.next());
    }

}