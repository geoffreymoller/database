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
        FileScan fs = new FileScan(RATINGS, schema);
        Average avg = new Average(RATING, fs ,schema);
        Tuple next = avg.next();
        Object actual = next.get("average");
        assertEquals(2d, actual);
    }

}