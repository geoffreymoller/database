package iterator;

import db.Database;
import entity.Tuple;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class FileScan implements Iterator {

    private final String tableName;
    private final Database db;
    private BufferedReader bufferedReader;
    private String line;
    private int i = 0;

    public FileScan(String tableName, Database db) {
        this.db = db;
        this.tableName = tableName;
        InputStream inputstream;
        try {
            inputstream = new FileInputStream(db.getPath() +
                tableName +
                ".csv");
            bufferedReader = new BufferedReader(new InputStreamReader(inputstream));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public Tuple next() {
        Tuple entity;
        try {
            if (i == 0) {
                line = bufferedReader.readLine();
            }
            line = bufferedReader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }

        //TODO - this needs betterment; (null, throw, etc)?
        if(line == null || line.equals("")) {
            return null;
        }

        entity = new Tuple(db, tableName, line);
        i += 1;
        return entity;
    }

    public void close() {
        //noop?
    }
}
