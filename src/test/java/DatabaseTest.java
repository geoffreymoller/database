import db.Database;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class DatabaseTest {

    @Test
    void init() {
        String path = "/foo/bar";
        Database db = new Database(path);
        assertEquals(path, db.getPath());
    }

}