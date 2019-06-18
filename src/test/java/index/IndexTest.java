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

//TODO - on-disk Hash
  //penalties for multiple IO for collisions
//TODO - get size from varint, not save to hash
//TODO - multiple hash entries per index item
//TODO - on-disk BTree
//TODO - record ID / block address
//TODO - robust varint implementation

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
        FileInputStream inputstream = new FileInputStream(filePath);
        HashIndex<String, Index> hashIndex = new HashIndex<>();
        while (true) {
            DatabaseProtos.Movie movie = DatabaseProtos.Movie.parseDelimitedFrom(inputstream);
            if (movie == null) {
                break;  // EOF
            } else {
                byte[] bytes = movie.toByteArray();
                int serializedSize = movie.getSerializedSize();
                String title = movie.getTitle();
                hashIndex.put(title, new Index(offset));
                offset += serializedSize + 1; //TODO - varint is not always 1 byte
                DatabaseProtos.Movie parsedMovie = DatabaseProtos.Movie.parseFrom(bytes);

                int movieOffset = hashIndex.get(movie.getTitle()).getOffset();
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
    void testOnDiskHashIndex() throws IOException {
        String filePath = schema.getPath() + MOVIES;
        int offset = 0;
        FileInputStream inputstream = new FileInputStream(filePath);
        OnDiskHashIndex<String, Index> onDiskHashIndex = new OnDiskHashIndex<>(MOVIES);
        while (true) {
            DatabaseProtos.Movie movie = DatabaseProtos.Movie.parseDelimitedFrom(inputstream);
            if (movie == null) {
                break;  // EOF
            } else {
                byte[] bytes = movie.toByteArray();
                int serializedSize = movie.getSerializedSize();
                String title = movie.getTitle();
                onDiskHashIndex.put(title, new Index(offset));
                offset += serializedSize + 1; //TODO - varint is not always 1 byte
                DatabaseProtos.Movie parsedMovie = DatabaseProtos.Movie.parseFrom(bytes);

                int movieOffset = onDiskHashIndex.get(movie.getTitle()).getOffset();
                RandomAccessFile file = new RandomAccessFile(filePath, "r");
                file.seek(movieOffset); //no penalty for seek unless we go to disk
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
        //TODO - to create BTree from sorted data
            //fill first leaf node (half full)
            //fill second leaf node (half full)
            //go bottom up to recursively create parent nodes as necessary
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
                btree.put(title, new Index(offset));
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
