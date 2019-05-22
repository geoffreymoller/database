package iterator;

import db.Schema;
import entity.Tuple;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class FileScan implements Iterator {

    private final String tableName;
    private final Schema schema;
    private BufferedReader bufferedReader;
    private String line;
    private int i;

    public String getTableName() {
        return tableName;
    }

    FileScan(String tableName, Schema schema) {
        this.schema = schema;
        this.tableName = tableName;
        this.init();
    }

    //TODO - protobuf Java
    //TODO - import CSV tables to your encoding (which is the protobuf encoding)
    @Override
    public void init() {
        i = 0;
        InputStream inputstream;
        try {
            inputstream = new FileInputStream(schema.getPath() +
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

        entity = new Tuple(schema, tableName, line);
        i += 1;
        return entity;
    }

    public void close() {
        //noop?
    }
}
