package iterator;

import db.Database;
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
        assertEquals(2d, avg.next());
    }

    @Test
    void close() {
    }
}