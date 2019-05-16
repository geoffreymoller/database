package iterator;

import db.Database;
import entity.Rating;
import entity.Tuple;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationTargetException;

import static db.Database.USER_ID;
import static org.junit.jupiter.api.Assertions.*;

class AverageTest {

    private Database db;

    @BeforeEach
    void setUp() {
        db = new Database("/Users/geoffreymoller/Code/database/src/test/resources/next/");
    }

    @Test
    void next() {
        Average avg = new Average("ratings", i -> true, "rating", db);
        assertEquals(2d, avg.next());
    }

    @Test
    void close() {
    }
}