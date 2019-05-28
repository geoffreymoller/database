package index;

import db.DatabaseProtos;
import db.Schema;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.HashMap;

import static db.Schema.MOVIES;
import static org.junit.jupiter.api.Assertions.assertEquals;

// NOTES
// An index is a copy of selected columns of data from a table that can be searched very efficiently that also includes
// a low-level disk block address
// or direct link to the complete row of data it was copied from.
// google: java read specific bytes from file

//Read a specific byte from binary file
//https://stackoverflow.com/questions/11545039/read-a-specific-byte-from-binary-file
//RandomAccessFile raf = new RandomAccessFile(file, "r");
//raf.seek(400); // Goes to 400th byte.
//then parseDelimitedFrom()?

//https://developers.google.com/protocol-buffers/docs/encoding#varints

//Scan file
//Get attribute value
//Calculate offset for item
//Write index entry
//next


public class IndexTest {

    private Schema schema;

    @BeforeEach
    void setUp() {
        schema = new Schema("/Users/geoffreymoller/Code/database/src/test/resources/next/");
    }

    @Test
    void testHashIndex() throws IOException {
        String filePath = schema.getPath() + MOVIES;
        int offset = 0;
        HashMap<String, Index> map = new HashMap<>();
        FileInputStream inputstream = new FileInputStream(filePath);
        while (true) {
            DatabaseProtos.Movie movie = DatabaseProtos.Movie.parseDelimitedFrom(inputstream);
            if (movie == null) {
                break;  // EOF
            } else {
                byte[] bytes = movie.toByteArray();
                int serializedSize = movie.getSerializedSize();
                String title = movie.getTitle();
                map.put(title, new Index(offset, serializedSize));
                offset += serializedSize + 1; //TODO - varint is not always 1 byte
                DatabaseProtos.Movie parsedMovie = DatabaseProtos.Movie.parseFrom(bytes);

                int movieOffset = map.get(movie.getTitle()).getOffset();
                RandomAccessFile file = new RandomAccessFile(filePath, "r");
                file.seek(movieOffset);
                int movieLength = file.read();
                bytes = new byte[movieLength];
                file.read(bytes, 0, movieLength);
                DatabaseProtos.Movie indexedMovie = DatabaseProtos.Movie.parseFrom(bytes);

                assertEquals(parsedMovie, indexedMovie);
            }
        }

    }

    @Test
    void testBTreeIndex() throws IOException {
        String filePath = schema.getPath() + MOVIES;
        int offset = 0;
        BTree<String, Index> btree = new BTree<>();
        FileInputStream inputstream = new FileInputStream(filePath);
        while (true) {
            DatabaseProtos.Movie movie = DatabaseProtos.Movie.parseDelimitedFrom(inputstream);
            if (movie == null) {
                break;  // EOF
            } else {
                byte[] bytes = movie.toByteArray();
                int serializedSize = movie.getSerializedSize();
                String title = movie.getTitle();
                btree.put(title, new Index(offset, serializedSize));
                offset += serializedSize + 1; //TODO - varint is not always 1 byte
                DatabaseProtos.Movie parsedMovie = DatabaseProtos.Movie.parseFrom(bytes);

                int movieOffset = btree.get(movie.getTitle()).getOffset();
                RandomAccessFile file = new RandomAccessFile(filePath, "r");
                file.seek(movieOffset);
                int movieLength = file.read();
                bytes = new byte[movieLength];
                file.read(bytes, 0, movieLength);
                DatabaseProtos.Movie indexedMovie = DatabaseProtos.Movie.parseFrom(bytes);

                assertEquals(parsedMovie, indexedMovie);
            }
        }

    }
}
