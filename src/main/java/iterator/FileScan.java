package iterator;

import com.google.common.base.Joiner;
import com.google.common.collect.Lists;
import db.DatabaseProtos;
import db.Field;
import db.Schema;
import db.Table;
import entity.Tuple;
import org.apache.commons.lang3.StringUtils;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Map;

public class FileScan implements Iterator {

    private final String tableName;
    private final Schema schema;
    private BufferedReader bufferedReader;
    private String line;
    private int i;
    private FileInputStream inputstream;

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
    //https://www.baeldung.com/google-protocol-buffer
    @Override
    public void init() {
        i = 0;
        try {
            inputstream = new FileInputStream(schema.getPath() + tableName);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public Tuple next() {
        Tuple entity;
        try {
            DatabaseProtos.Movie movie = DatabaseProtos.Movie.parseDelimitedFrom(inputstream);
            Table table = schema.getSchema().get(tableName);
            ArrayList<String> fields = Lists.newArrayList();
            table.getFields().keySet().forEach(fk -> {
                String methodName = "get" + StringUtils.capitalize(fk);
                try {
                    Method method = DatabaseProtos.Movie.class.getMethod(methodName);
                    String value = method.invoke(movie).toString();
                    fields.add(value);
                } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
                    e.printStackTrace();
                }
            });
            line = Joiner.on(",").join(fields);

            if (movie == null) {
                System.out.println("EOF");
//                    break;  // EOF
            } else {
//                System.out.println(movie);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        entity = new Tuple(schema, tableName, line);
        i += 1;
        return entity;
    }

    public void close() {
        //noop?
    }
}
