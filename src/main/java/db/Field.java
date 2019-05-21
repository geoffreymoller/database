package db;

public final class Field {
    private final String tableName;
    private String name;
    private Class type;
    private Boolean nullable;

    public String getTableName() {
        return tableName;
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

    @Override
    public String toString() {
        return "Field{" +
            "tableName='" + tableName + '\'' +
            ", name='" + name + '\'' +
            ", type=" + type +
            ", nullable=" + nullable +
            '}';
    }
}
