package iterator;

import db.Database;
import entity.Link;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;

import static org.junit.jupiter.api.Assertions.*;

class FileScanTest {

    Database db;

    @BeforeEach
    void setUp() {
        db = new Database("/Users/geoffreymoller/Code/database/src/test/resources/next/");
    }

    @Test
    void next() {
        FileScan<Link> fs = new FileScan<>(Link.class, db);

        Link l = fs.next();

        Class<?> clazz = l.getClass();
        for(Field field : clazz.getDeclaredFields()) {
            //you can also use .toGenericString() instead of .getName(). This will
            //give you the type information as well.
            System.out.println(field.getName());
            System.out.println(field.toGenericString());
            System.out.println(field.getAnnotatedType());
            System.out.println("");
        }

        assertEquals(1, l.getMovieId());
        assertEquals(114709, l.getImdbId());
        assertEquals(862, l.getTmdbId());

        l = fs.next();
        assertEquals(2, l.getMovieId());
        assertEquals(113497, l.getImdbId());
        assertEquals(8844, l.getTmdbId());
    }

}