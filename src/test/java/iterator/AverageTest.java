package iterator;

import entity.Rating;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AverageTest {

    @Test
    void next() {
      Average<Rating> avg = new Average<>(Rating.class, i -> true, "rating", )
    }

    @Test
    void close() {
    }
}