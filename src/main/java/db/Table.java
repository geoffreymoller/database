package db;

import com.google.protobuf.GeneratedMessageV3;
import iterator.Generator;

import java.util.Map;

public class Table {

    private final int id;
    private String name;
    private Class klass;
    private Map<String, Field> fields;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Map<String, Field> getFields() {
        return fields;
    }

    public Class getKlass() {
        return klass;
    }

    public Table(String name, int id, Class klass, Map<String, Field> fields) {
        this.id = id;
        this.name = name;
        this.klass = klass;
        this.fields = fields;
    }
}
