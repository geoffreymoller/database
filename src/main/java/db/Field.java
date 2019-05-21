package db;

public final class Field {
    private final String tableName;
    private int index;
    private String name;
    private Class type;
    private Boolean nullable;

    public String getTableName() {
        return tableName;
    }

    public int getIndex() {
        return index;
    }

    public String getName() {
        return name;
    }

    public Class getType() {
        return type;
    }

    public Field(String tableName, String fieldName, Class type, Boolean nullable) {
        this.tableName = tableName;
        this.name = fieldName;
        this.type = type;
        this.nullable = nullable;
    }
}
