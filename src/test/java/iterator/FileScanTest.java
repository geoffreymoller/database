package iterator;

import com.google.common.collect.Lists;
import com.google.protobuf.Message;
import db.DatabaseProtos;
import db.Schema;
import entity.Tuple;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;

import static db.Schema.GENRES;
import static db.Schema.IMDB_ID;
import static db.Schema.MOVIE_ID;
import static db.Schema.RATING;
import static db.Schema.TIMESTAMP;
import static db.Schema.TITLE;
import static db.Schema.TMDB_ID;
import static db.Schema.USER_ID;
import static org.junit.jupiter.api.Assertions.assertEquals;

class FileScanTest {

    Schema schema;

    @BeforeEach
    void setUp() {
        schema = new Schema("/Users/geoffreymoller/Code/database/src/test/resources/next/");
    }

    @Test
    void testProto() throws Exception {
        String filePath = "/Users/geoffreymoller/Code/database/src/main/java/db/movies";

        int movieId = 14;
        String title = "Fandango";
        String genres = "Comedy";
        DatabaseProtos.Movie movie =
            DatabaseProtos.Movie.newBuilder()
                .setMovieId(movieId)
                .setTitle(title)
                .setGenres(genres)
                .build();

        DatabaseProtos.Movie movie2 =
            DatabaseProtos.Movie.newBuilder()
                .setMovieId(15)
                .setTitle(title)
                .setGenres(genres)
                .build();

        assertEquals(movie.getMovieId(), movieId);
        assertEquals(movie.getTitle(), title);
        assertEquals(movie.getGenres(), genres);

        File file = new File(filePath);
        FileOutputStream fop = new FileOutputStream(file);
        writeStream(Lists.newArrayList(movie, movie2), fop);

        read(filePath);
    }

    void read(String filePath) throws IOException {
        FileInputStream stream = new FileInputStream(filePath);
        while (true) {
            DatabaseProtos.Movie movie = DatabaseProtos.Movie.parseDelimitedFrom(stream);
            if (movie == null) {
                break;  // EOF
            } else {
                System.out.println(movie);
            }
        }
    }

    static <MSG extends Message> void writeStream(Iterable<MSG> messages, OutputStream output) throws Exception {
        try {
            for (Message message : messages) {
                message.writeDelimitedTo(output);
            }
        } catch (Exception e) {
            throw new Exception("Unable to write messages", e);
        }
    }

    @Test
    void testLinks() {
        FileScan fs = new FileScan("links", schema);
        Tuple t = fs.next();

        assertEquals(1, t.get(MOVIE_ID));
        assertEquals(114709, t.get(IMDB_ID));
        assertEquals(862, t.get(TMDB_ID));

        t = fs.next();
        assertEquals(2, t.get(MOVIE_ID));
        assertEquals(113497, t.get(IMDB_ID));
        assertEquals(8844, t.get(TMDB_ID));
    }

    @Test
    void testRatings() {
        FileScan fs = new FileScan("ratings", schema);
        Tuple t = fs.next();

        assertEquals(1, t.get(USER_ID));
        assertEquals(2, t.get(MOVIE_ID));
        assertEquals(1d, t.get(RATING));
        assertEquals(1112486027, t.get(TIMESTAMP));

        t = fs.next();
        assertEquals(1, t.get(USER_ID));
        assertEquals(29, t.get(MOVIE_ID));
        assertEquals(2d, t.get(RATING));
        assertEquals(1112484676, t.get(TIMESTAMP));
    }

    @Test
    void testMovies() {
        FileScan fs = new FileScan("movies", schema);
        Tuple t = fs.next();

        assertEquals(1, t.get(MOVIE_ID));
        assertEquals("Toy Story (1995)", t.get(TITLE));
        assertEquals("Adventure|Animation|Children|Comedy|Fantasy", t.get(GENRES));

        t = fs.next();
        assertEquals(2, t.get(MOVIE_ID));
        assertEquals("Jumanji (1995)", t.get(TITLE));
        assertEquals("Adventure|Children|Fantasy", t.get(GENRES));
    }

}