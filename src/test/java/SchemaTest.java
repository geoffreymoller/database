import db.Schema;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class SchemaTest {

    @Test
    void init() {
        String path = "/foo/bar";
        Schema schema = new Schema(path);
        assertEquals(path, schema.getPath());
    }

}