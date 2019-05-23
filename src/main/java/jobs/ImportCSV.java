package jobs;

import com.google.common.collect.Lists;
import com.google.protobuf.Message;
import db.DatabaseProtos;
import db.Schema;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.ArrayList;

import static db.Schema.LINKS;
import static db.Schema.MOVIES;
import static db.Schema.RATINGS;

public class ImportCSV {

    public static void main(String[] args) {
//        Schema schema = new Schema("/Users/geoffreymoller/Code/database/ml-20m/");
        Schema schema = new Schema("/Users/geoffreymoller/Code/database/src/test/resources/next/");
        ArrayList<String> integers = Lists.newArrayList(MOVIES, LINKS, RATINGS);
        integers.forEach(t -> {
            doImport(schema, t);
        });
    }

    static void doImport(Schema schema, String tableName) {
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

    static void read(String type, String filePath) throws IOException {
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

}
