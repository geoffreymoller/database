package iterator;

import db.Database;
import entity.Link;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationTargetException;
import java.util.function.Predicate;
import static org.junit.jupiter.api.Assertions.*;

class SelectionTest {

    Database db;

    @BeforeEach
    void setUp() {
       db = new Database("/Users/geoffreymoller/Code/database/src/test/resources/next/");
    }

    @Test
    void testQuery() {
        Predicate<Link> p = l -> l.getMovieId() == 2;

        Selection<Link> selection = new Selection<>(Link.class, p, db);
        Link next = selection.next();
        assertEquals(2, next.getMovieId());

        String sql = "movieId = 2";
        java.lang.reflect.Method method;
        try {
            method = next.getClass().getMethod("getMovieId");
            Integer invoked = (Integer) method.invoke(next);
            System.out.println(invoked);
        } catch (SecurityException | NoSuchMethodException | IllegalAccessException e) {
            //noop
        } catch (InvocationTargetException e) {
            //noop
        }

    }

    @Test
    void testNextExists() {
        Predicate<Link> p = l -> l.getMovieId() == 2;
        Selection<Link> selection = new Selection<>(Link.class, p, db);
        assertEquals(2, selection.next().getMovieId());
    }

    @Test
    void testNextNotExists() {
        Predicate<Link> p = l -> l.getMovieId() == Integer.MAX_VALUE;
        Selection<Link> selection = new Selection<>(Link.class, p, db);
        assertNull(selection.next());
    }

}