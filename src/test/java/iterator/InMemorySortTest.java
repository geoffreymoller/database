package iterator;

import com.google.common.collect.Lists;
import com.google.common.collect.Ordering;
import db.Schema;
import entity.Tuple;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static db.Schema.IMDB_ID;
import static db.Schema.LINKS;
import static org.junit.jupiter.api.Assertions.*;

class InMemorySortTest {

    private Schema schema;

    @BeforeEach
    void setUp() {
        schema = new Schema("/Users/geoffreymoller/Code/database/src/test/resources/next/");
    }

    @Test
    void init() {
        ArrayList<Object> all = Lists.newArrayList();
        FileScan fs = new FileScan(LINKS, schema);
        Tuple next = fs.next();
        while (next != null) {
            all.add(next);
            next = fs.next();
        }
        List<Integer> imdbIDs = all.stream().map(
            link -> (Integer) ((Tuple) link).get(IMDB_ID)
        ).collect(Collectors.toList());
        boolean sorted = Ordering.natural().isOrdered(imdbIDs);
        assertFalse(sorted);

        all.clear();

        InMemorySort sort = new InMemorySort(IMDB_ID, fs, schema);
        next = sort.next();
        while (next != null) {
            all.add(next);
            next = sort.next();
        }
        imdbIDs = all.stream().map(
            link -> (Integer) ((Tuple) link).get(IMDB_ID)
        ).collect(Collectors.toList());
        sorted = Ordering.natural().isOrdered(imdbIDs);
        assertTrue(sorted);
    }

    @Test
    void next() {
    }
}