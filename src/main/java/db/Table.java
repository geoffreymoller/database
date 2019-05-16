package db;

import java.util.Map;

public class Table {

    private final int id;
    private String name;
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

    public Table(String name, int id, Map<String, Field> fields) {
        this.id = id;
        this.name = name;
        this.fields = fields;
    }
}
