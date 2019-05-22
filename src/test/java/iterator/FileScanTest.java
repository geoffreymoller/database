package iterator;

import com.google.common.collect.Lists;
import com.google.protobuf.Message;
import db.DatabaseProtos;
import db.Schema;
import entity.Tuple;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.ArrayList;

import static db.Schema.GENRES;
import static db.Schema.IMDB_ID;
import static db.Schema.LINKS;
import static db.Schema.MOVIES;
import static db.Schema.MOVIE_ID;
import static db.Schema.RATING;
import static db.Schema.RATINGS;
import static db.Schema.TIMESTAMP;
import static db.Schema.TITLE;
import static db.Schema.TMDB_ID;
import static db.Schema.USER_ID;
import static org.junit.jupiter.api.Assertions.assertEquals;

class FileScanTest {

    private Schema schema;

    @BeforeEach
    void setUp() {
        schema = new Schema("/Users/geoffreymoller/Code/database/ml-20m/");
    }

    @Test
    void importProtoFromCSV() {
        ArrayList<String> integers = Lists.newArrayList(MOVIES, LINKS, RATINGS);
        integers.forEach(this::doImport);
    }

    void doImport(String tableName) {
        String line;
        System.out.println(tableName);

        int i = 0;
        InputStream inputstream;
        OutputStream outputstream;
        BufferedReader bufferedReader;
        try {
            inputstream = new FileInputStream(schema.getPath() + tableName + ".csv");
            outputstream = new FileOutputStream(schema.getPath() + tableName);
            bufferedReader = new BufferedReader(new InputStreamReader(inputstream));
            while (true) {
                line = bufferedReader.readLine();
                if (line == null) {
                    break;
                }
                String[] parts = line.split(",");
                switch (tableName) {
                    case MOVIES:
                        DatabaseProtos.Movie movie =
                            DatabaseProtos.Movie.newBuilder()
                                .setMovieId(Integer.parseInt(parts[0]))
                                .setTitle(parts[1])
                                .setGenres(parts[2])
                                .build();
                        writeStream(Lists.newArrayList(movie), outputstream);
                        break;
                    case LINKS:
                        int tmdbId = parts.length == 2 ? 0 : Integer.parseInt(parts[2]);
                        DatabaseProtos.Link link =
                            DatabaseProtos.Link.newBuilder()
                                .setMovieId(Integer.parseInt(parts[0]))
                                .setImdbId(Integer.parseInt(parts[1]))
                                .setTmdbId(tmdbId)
                                .build();
                        writeStream(Lists.newArrayList(link), outputstream);
                        break;
                    case RATINGS:
                        DatabaseProtos.Rating rating =
                            DatabaseProtos.Rating.newBuilder()
                                .setUserId(Integer.parseInt(parts[0]))
                                .setMovieId(Integer.parseInt(parts[1]))
                                .setRating(Float.parseFloat(parts[2]))
                                .setTimestamp(Integer.parseInt(parts[3]))
                                .build();
                        writeStream(Lists.newArrayList(rating), outputstream);
                        break;
                    default:
                }
                i++;
                //TODO - this needs betterment; (null, throw, etc)?
                if (line.equals("")) {
                    break;
                }
                System.out.println(line);
            }
            read(tableName, schema.getPath() + tableName);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

//    @Test
//    void testProto() throws Exception {
//        String filePath = "/Users/geoffreymoller/Code/database/src/main/java/db/movies";
//
//        int movieId = 14;
//        String title = "Fandango";
//        String genres = "Comedy";
//        DatabaseProtos.Movie movie =
//            DatabaseProtos.Movie.newBuilder()
//                .setMovieId(movieId)
//                .setTitle(title)
//                .setGenres(genres)
//                .build();
//
//        DatabaseProtos.Movie movie2 =
//            DatabaseProtos.Movie.newBuilder()
//                .setMovieId(15)
//                .setTitle(title)
//                .setGenres(genres)
//                .build();
//
//        assertEquals(movie.getMovieId(), movieId);
//        assertEquals(movie.getTitle(), title);
//        assertEquals(movie.getGenres(), genres);
//
//        File file = new File(filePath);
//        FileOutputStream fop = new FileOutputStream(file);
//        writeStream(Lists.newArrayList(movie, movie2), fop);
//
//        read(MOVIES, filePath);
//    }

    void read(String type, String filePath) throws IOException {
        FileInputStream stream = new FileInputStream(filePath);
        switch (type) {
            case MOVIES:
                while(true) {
                    DatabaseProtos.Movie movie = DatabaseProtos.Movie.parseDelimitedFrom(stream);
                    if (movie == null) {
                        break;  // EOF
                    } else {
                        System.out.println(movie);
                    }
                }
            case LINKS:
                while(true) {
                    DatabaseProtos.Link link = DatabaseProtos.Link.parseDelimitedFrom(stream);
                    if (link == null) {
                        break;  // EOF
                    } else {
                        System.out.println(link);
                    }
                }
            case RATINGS:
                while(true) {
                    DatabaseProtos.Rating rating = DatabaseProtos.Rating.parseDelimitedFrom(stream);
                    if (rating == null) {
                        break;  // EOF
                    } else {
                        System.out.println(rating);
                    }
                }
            default:
                break;
        }

    }

    static <MSG extends Message> void writeStream(Iterable<MSG> messages, OutputStream output) {
        try {
            for (Message message : messages) {
                message.writeDelimitedTo(output);
            }
        } catch (Exception e) {
            try {
                throw new Exception("Unable to write messages", e);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
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