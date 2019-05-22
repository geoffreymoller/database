package iterator;

import db.Database;
import entity.Tuple;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static db.Database.RATING;
import static db.Database.RATINGS;
import static org.junit.jupiter.api.Assertions.assertEquals;

class AverageTest {

    private Database db;

    @BeforeEach
    void setUp() {
        db = new Database("/Users/geoffreymoller/Code/database/src/test/resources/next/");
    }

    @Test
    void next() {
        Average avg = new Average(RATINGS, i -> true, RATING, db);
        Tuple next = avg.next();
        Object actual = next.get("average", "aggregate");
        assertEquals(2d, actual);
    }

}