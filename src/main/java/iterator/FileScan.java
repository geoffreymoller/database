package iterator;

import com.google.common.base.Joiner;
import com.google.common.collect.Lists;
import db.DatabaseProtos;
import db.Schema;
import db.Table;
import entity.Tuple;
import org.apache.commons.lang3.StringUtils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;

import static db.Schema.LINKS;
import static db.Schema.MOVIES;
import static db.Schema.RATINGS;

public class FileScan implements Iterator {

    private final String tableName;
    private final Schema schema;
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

    @Override
    public void init() {
        try {
            inputstream = new FileInputStream(schema.getPath() + tableName);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public Tuple next() {
        try {
            Class klass = getEntityClass(tableName);
            Method method = Arrays.stream(klass.getDeclaredMethods())
                .filter(t -> t.getName().contains("parseDelimitedFrom")
                    && t.getParameterCount() == 1).collect(Collectors.toList()).get(0);
            Object o = method.invoke(new Object(), inputstream);
            if (o == null) {
                return null;
            }

            ArrayList<String> fields = Lists.newArrayList();
            Table table = schema.getSchema().get(tableName);
            for (String fk : table.getFields().keySet()) {
                String methodName = "get" + StringUtils.capitalize(fk);
                try {
                    fields.add(klass.getMethod(methodName).invoke(o).toString());
                } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
                    e.printStackTrace();
                }
            }
            line = Joiner.on(",").join(fields);
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }

        return new Tuple(schema, tableName, line);
    }

    private Class getEntityClass(String tableName) {
        Class klass;
        switch (this.tableName) {
            case MOVIES:
                klass = DatabaseProtos.Movie.class;
                break;
            case LINKS:
                klass = DatabaseProtos.Link.class;
                break;
            case RATINGS:
                klass = DatabaseProtos.Rating.class;
                break;
            default:
                throw new UnsupportedOperationException();
        }
        return klass;
    }

    public void close() {
        //noop?
    }
}
