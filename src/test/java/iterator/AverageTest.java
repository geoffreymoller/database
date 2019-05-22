package iterator;

import db.Schema;
import entity.Tuple;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static db.Schema.RATING;
import static db.Schema.RATINGS;
import static org.junit.jupiter.api.Assertions.assertEquals;

class AverageTest {

    private Schema schema;

    @BeforeEach
    void setUp() {
        schema = new Schema("/Users/geoffreymoller/Code/database/src/test/resources/next/");
    }

    @Test
    void next() {
        Average avg = new Average(RATINGS, i -> true, RATING, schema);
        Tuple next = avg.next();
        Object actual = next.get("average", "aggregate");
        assertEquals(2d, actual);
    }

}