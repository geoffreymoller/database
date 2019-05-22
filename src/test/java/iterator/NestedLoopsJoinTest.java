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

class NestedLoopsJoinTest {

    private Schema schema;

    @BeforeEach
    void setUp() {
        schema = new Schema("/Users/geoffreymoller/Code/database/src/test/resources/next/");
    }

    @Test
    void next() {
        Predicate<Tuple> p = t -> true;
        FileScan fs = new FileScan(LINKS, schema);
        FileScan fs2 = new FileScan(MOVIES, schema);
        Selection selectionLinks = new Selection(p, fs, schema);
        Selection selectionMovies = new Selection(p, fs2, schema);

        BiFunction<Tuple, Tuple, Boolean> joinPredicate = (tuple, tuple2) ->
            tuple.get(MOVIE_ID) == tuple2.get(MOVIE_ID);

        NestedLoopsJoin j = new NestedLoopsJoin(selectionLinks, selectionMovies, joinPredicate, schema);
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